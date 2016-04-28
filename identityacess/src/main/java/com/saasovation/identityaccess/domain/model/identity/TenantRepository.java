package com.saasovation.identityaccess.domain.model.identity;

public interface TenantRepository {

	public Tenant tenantOfId(TenantId aTenantId);

	public User userWithName(TenantId tenantId, String aUserName);

}
