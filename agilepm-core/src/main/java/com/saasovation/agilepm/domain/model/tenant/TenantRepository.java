package com.saasovation.agilepm.domain.model.tenant;

public interface TenantRepository {

	Tenant tenantOfId(TenantId aTenantId);
	
}
