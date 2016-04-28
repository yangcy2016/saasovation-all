package com.saasovation.identityaccess.domain.model.role;


import com.saasovation.identityaccess.domain.model.identity.TenantId;

public interface RoleRepository {

	Role roleNamed(TenantId tenantId, String aRoleName);

}
