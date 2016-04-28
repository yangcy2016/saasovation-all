package com.saasovation.agilepm.application.product;


import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.common.domain.DomainEventSubscriber;
import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.BacklogItemCommitted;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;

public class BacklogItemApplicationService {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void commitBacklogItem(Tenant aTenant, BacklogItemId aBacklogItemId,
			SprintId aSprintId) {
		
	
		DomainEventSubscriber subscriber = new DomainEventSubscriber<BacklogItemCommitted>() {
			public Class<?> subscribedToEventType() {
				return BacklogItemCommitted.class;
			}

			public void handle(BacklogItemCommitted aDomainEvent) {
				//do handle event
				System.out.println("a backlog item committed...");
				System.out.println("BacklogItemId:"+aDomainEvent.backlogItemId());
			}
			
		};
		DomainEventSubscriber storeSubscriber = new DomainEventSubscriber<BacklogItemCommitted>() {

			public Class<?> subscribedToEventType() {
				return BacklogItemCommitted.class;
			}

			public void handle(BacklogItemCommitted aDomainEvent) {
				System.out.println("stored a domain event:"+aDomainEvent.backlogItemId());
			}
			
		};
		DomainEventPublisher.instance().subscribe(subscriber,storeSubscriber);
		
		BacklogItem backlogItem = DomainRegistry.backlogItemRepository()
			.backlogItemOfId(aBacklogItemId);
		
		Sprint sprint = DomainRegistry.sprintRepository()
				.sprintOfId(aSprintId);
		
		backlogItem.commitTo(sprint);
		
	}
}
