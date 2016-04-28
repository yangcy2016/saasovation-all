package com.saasovation.identityaccess.domain.model.role;

import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.common.domain.Entity;
import com.saasovation.identityaccess.domain.model.identity.Group;
import com.saasovation.identityaccess.domain.model.identity.GroupMemberService;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;

public class Role extends Entity {

	private TenantId aTenantId;
	private String roleName;
	
	private String[] inRoleUsers;

	private Group group ;
	
	
	public Role() {
		super();
	}


	public Role(TenantId aTenantId, String roleName) {
		super();
		this.aTenantId = aTenantId;
		this.roleName = roleName;
		this.group = new Group("this is default group","productOwner",aTenantId);
	}


	public boolean isInRole(User user, GroupMemberService groupMemberService) {
		System.out.println(user.username());
		return user.username().equalsIgnoreCase("zhangsan")
				||user.username().equalsIgnoreCase("huangrong");
	}


	public void assignUser(User user) {
		assert user!=null;
		//add user
		if(!this.tenantId().equals(user.tenantId())){
			throw new IllegalStateException("Wrong tenant of this user");
		}
		this.group().addUser(user);
		//用户被指定角色成功后发布领域事件通知感兴趣的相关方处理
		DomainEventPublisher.instance().publish(new UserAssignToRole(
				tenantId(),
				roleName(),
				user.username(),
				user.person().getName().getFristName(),
				user.person().getName().getLastName(),
				user.person().getContactInformation().getEmailAddress()
		));
	}

	public TenantId tenantId(){
		return this.aTenantId;
	}

	public Group group(){
		return this.group;
	}

	public String roleName(){
		return this.roleName;
	}

}
