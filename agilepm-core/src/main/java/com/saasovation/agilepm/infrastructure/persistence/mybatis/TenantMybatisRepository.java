package com.saasovation.agilepm.infrastructure.persistence.mybatis;


import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.agilepm.domain.model.tenant.TenantRepository;

public class TenantMybatisRepository implements TenantRepository {

	public Tenant tenantOfId(TenantId aTenantId) {
		// TODO Auto-generated method stub
		return new Tenant();
	}

}
