package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.common.domain.Entity;

import java.util.HashSet;
import java.util.Set;


public class Group extends Entity {
	private String description;
	private Set<GroupMember> groupMembers;
	private String name;
	private TenantId tenantId;
	
	public Group(String description, String name, TenantId tenantId) {
		super();
		this.setDescription(description);
		this.setName(name);
		this.setTenantId(tenantId);
		this.initalize();
		this.setGroupMember(new HashSet<GroupMember>());
	}
	
	protected Group(){
		this.setGroupMember(new HashSet<GroupMember>());
	}
	
	private void initalize(){
		
	}

	private void setDescription(String aDescription){
		if(aDescription==null){
			throw new IllegalArgumentException("description can't be a null value");
		}
		this.description = aDescription;
	}
	
	private void setName(String aName){
		this.name = aName;
	}
	
	private void setTenantId(TenantId aTenantId){
		this.tenantId = aTenantId;
	}
	
	private void setGroupMember(Set<GroupMember> groupMembers){
		this.groupMembers = groupMembers;
	}

	public void addUser(User user){
		this.groupMembers().add(new GroupMember(user.tenantId(),user.username(), GroupMemberType.USER));
	}

	public Set<GroupMember> groupMembers(){
		return this.groupMembers;
	}
	
}
