package com.saasovation.agilepm.domain.model.product.task;


import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

public interface TaskRepository {
	int totalBacklogItemTaskHoursRemaining(TenantId aTenantId,
										   BacklogItemId aBacklogItemId);
}
