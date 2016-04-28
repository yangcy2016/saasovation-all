package com.saasovation.agilepm.domain.model.team;


import com.saasovation.common.domain.ConcurrencySafeEntity;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

/**
 * aggregate root
 * @author Administrator
 *
 */
public class Team extends ConcurrencySafeEntity {
	private TenantId tenantId;
}
