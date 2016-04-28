package com.saasovation.identityaccess.application;


import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.GroupMemberService;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.identityaccess.domain.model.role.Role;
import com.saasovation.identityaccess.domain.model.role.UserAssignToRoleEventSubscriber;
import org.springframework.stereotype.Service;


@Service(value="com.sand.identityacess.application.AccessService")
public class AccessService {
	
	public User userInRole(String aTenantId,String aUserName,String aRoleName){
		User userInRole = null;
		TenantId tenantId = new TenantId(aTenantId);
		User user = DomainRegistry.tenantRepository()
					.userWithName(tenantId,aUserName);
		if(user!=null){
			Role role = DomainRegistry.roleRepository()
						.roleNamed(tenantId,aRoleName);
			if(role!=null){
				GroupMemberService groupMemberService = DomainRegistry.groupMemberService();
				if(role.isInRole(user,groupMemberService)){
					userInRole = user;
				}
			}
		}
		return userInRole;
	}

	/**
	 * 为订阅用户指定角色
	 * @param aTenantId
	 * @param userName
	 * @param aRoleName
	 */
	public void assignUserToRole(String aTenantId,String userName,String aRoleName){
		TenantId tenantId = new TenantId(aTenantId);
		User user = DomainRegistry.userRepository().userWithUserName(tenantId,userName);
		if(user != null){
			Role role = DomainRegistry.roleRepository().roleNamed(tenantId, aRoleName);
			if(role != null){
				DomainEventPublisher.instance().subscribe(new UserAssignToRoleEventSubscriber());
				role.assignUser(user);
			}
		}
	}
}
