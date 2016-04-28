package com.saasovation.identityaccess.domain.model.identity;


import com.saasovation.identityaccess.domain.model.IdentifiedValueObject;

public class GroupMember extends IdentifiedValueObject {
	private TenantId tenantId;
	private String name;
	private GroupMemberType memberType;
	
	public GroupMember(TenantId tenantId, String name,
			GroupMemberType memberType) {
		super();
		this.tenantId = tenantId;
		this.name = name;
		this.memberType = memberType;
		this.initialize();
	}
	 
	
	private void initialize(){
		
	}
	public static void main(String[] args) {
		System.out.println(GroupMemberType.GROUP.isGroup());
		System.out.println(GroupMemberType.GROUP.isUser());
	}
}
