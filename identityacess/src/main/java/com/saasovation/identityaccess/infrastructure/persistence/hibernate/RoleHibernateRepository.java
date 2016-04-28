package com.saasovation.identityaccess.infrastructure.persistence.hibernate;

import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.role.Role;
import com.saasovation.identityaccess.domain.model.role.RoleRepository;
import org.springframework.stereotype.Repository;


@Repository(value="com.sand.identityacess.infrastructure.persistence.hibernate.RoleHibernateRepository")
public class RoleHibernateRepository implements RoleRepository {

	public Role roleNamed(TenantId tenantId, String aRoleName) {
		// TODO Auto-generated method stub
		return new Role(tenantId,aRoleName);
	}

}
