package com.saasovation.agilepm.domain.model.product.task;


import com.saasovation.common.domain.DomainEvent;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;


public class TaskHoursRemainingEstimation implements DomainEvent {

	private long id;
	private Date occurredOn;
	private TenantId tenantId;
	private BacklogItemId backlogItemId;
	private TaskId taskId;
	private int hoursRemaining;

	public long id() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Date occurredOn() {
		// TODO Auto-generated method stub
		return null;
	}

	public long version() {
		// TODO Auto-generated method stub
		return 0;
	}

}
