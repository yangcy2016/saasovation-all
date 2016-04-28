package com.saasovation.agilepm.domain.model.team;


import com.saasovation.agilepm.domain.model.tenant.TenantId;

public interface TeamRepository {

	Team teamOfId(TenantId tenantId, TeamId teamId);

}
