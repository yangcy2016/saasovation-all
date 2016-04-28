package com.saasovation.agilepm.domain.model.product;


import com.saasovation.common.domain.DomainEvent;
import com.saasovation.common.domain.DomainEventIdCreator;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;

public class BacklogItemCommitted implements DomainEvent {
	//事件产生时间
	private Date occurredOn = new Date();
	/*什么导致事件产生**/
	//事件发起方
	private BacklogItemId backlogItemId;
	//事件参与方
	private SprintId sprintId;
	private TenantId tenantId;
	
	private long id = DomainEventIdCreator.getId();
	
	public BacklogItemCommitted(BacklogItemId backlogItemId,
			SprintId sprintId, TenantId tenantId) {
		super();
		this.setBacklogItemId(backlogItemId);
		this.setSprintId(sprintId);
		this.setTenantId(tenantId);
	}
	public Date occurredOn() {
		return this.occurredOn;
	}
	public BacklogItemId backlogItemId(){
		return this.backlogItemId;
	}
	public SprintId committedToSprintId(){
		return this.sprintId;
	}
	public TenantId tenantId(){
		return this.tenantId;
	}
	private void setSprintId(SprintId sprintId ){
		if(sprintId==null){
			throw new IllegalArgumentException("sprintId must not be null");
		}
		this.sprintId = sprintId;
	}
	private void setTenantId(TenantId tenantId){
		if(tenantId==null){
			throw new IllegalArgumentException("tenantId must not be null");
		}
		this.tenantId = tenantId;
	}
	private void setBacklogItemId(BacklogItemId backlogItemId){
		if(backlogItemId==null){
			throw new IllegalArgumentException("backlogItemId must not be null");
		}
		this.backlogItemId = backlogItemId;
	}
	public long id() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public long version() {
		// TODO Auto-generated method stub
		return 0;
	}

}
