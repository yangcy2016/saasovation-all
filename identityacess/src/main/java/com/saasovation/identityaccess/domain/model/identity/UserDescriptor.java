package com.saasovation.identityaccess.domain.model.identity;

public class UserDescriptor {
	private String emailAddress;
	private TenantId tenantId;
	private String username;
	public UserDescriptor(String emailAddress, TenantId tenantId,
			String username) {
		super();
		this.emailAddress = emailAddress;
		this.tenantId = tenantId;
		this.username = username;
	}
	
}
