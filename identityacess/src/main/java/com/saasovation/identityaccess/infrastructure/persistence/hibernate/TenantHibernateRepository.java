package com.saasovation.identityaccess.infrastructure.persistence.hibernate;

import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.TenantRepository;
import com.saasovation.identityaccess.domain.model.identity.User;
import org.springframework.stereotype.Repository;


@Repository(value="com.sand.identityacess.infrastructure.persistence.hibernate.TenantHibernateRepository")
public class TenantHibernateRepository implements TenantRepository {

	public Tenant tenantOfId(TenantId aTenantId) {
	
		return new Tenant(aTenantId,"MockTenant",true);
	}

	public User userWithName(TenantId tenantId, String aUserName) {
		
		return new User(aUserName,tenantId);
	}

}
