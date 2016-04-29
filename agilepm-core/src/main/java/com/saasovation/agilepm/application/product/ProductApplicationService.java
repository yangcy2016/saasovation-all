package com.saasovation.agilepm.application.product;


import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.forum.DiscussionAvailability;
import com.saasovation.agilepm.domain.model.forum.DiscussionDescriptor;
import com.saasovation.agilepm.domain.model.product.*;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.agilepm.infrastructure.messaging.Exchangers;
import com.saasovation.agilepm.infrastructure.messaging.TimeConstrainedProcessTracker;
import com.saasovation.common.domain.AbstractDomainEventSubscriber;
import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.common.port.adapter.messaging.rabbitmq.ConnectionSettings;
import com.saasovation.common.port.adapter.messaging.rabbitmq.Exchange;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageParameters;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageProducer;
import com.saasovation.common.serializater.ObjectSerializer;
import com.saasovation.common.tracker.ProcessId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "com.saasovation.agilepm.application.product.ProductApplicationService")
public class ProductApplicationService {

	public BusinessPriorityTotals productBusinessPriority(String aTenantId,
			String aProductId) {

		return DomainRegistry.businessPriorityCalculator()
				.businessPriorityTotals(new TenantId(aTenantId),
						new ProductId(aProductId));
	}

	@Transactional
	public String newProductWithDiscussion(String aTenantId, String aProductName,
										   String productOwnerId,
										   String aProductDescription) {
		DomainEventPublisher.instance().subscribe(new AbstractDomainEventSubscriber<ProductCreated>() {
			@Override
			public void handle(ProductCreated aDomainEvent){
				String serialization = ObjectSerializer.newInstance().serialize(aDomainEvent);
				messageProducer().send(
						serialization,
						MessageParameters.durableTextParameters(
								ProductCreated.class.getName(),
								String.valueOf(aDomainEvent.id()),
								new Date(),
								"product.created")
				);
			}
		});
		return newProductWith(
				aTenantId,
				aProductName,
				productOwnerId,
				aProductDescription,
				this.requestDiscussionIfAvailable());
	}

	private String newProductWith(String aTenantId, String aProductName,String productOwnerId,
								  String aProductDescription,DiscussionAvailability availability){
		return new Product(
				new TenantId(aTenantId), //
				new ProductId(DomainRegistry.productRepository().nextIdentity()),//
				aProductName,//
				new ProductOwnerId(productOwnerId),
				aProductDescription,//
				requestDiscussionIfAvailable()).productId().id();
	}

	private DiscussionAvailability requestDiscussionIfAvailable() {
		return DiscussionAvailability.REQUESTED;
	}

	@Transactional
	public void initiateDiscussion(String tenantId, String productId, String discussionId) {
		Product product = DomainRegistry.productRepository().productOfId(
				new TenantId(tenantId),
				new ProductId(productId));
		if(product == null){
			throw new IllegalStateException("unknown tenantId:"+tenantId+" productId:"+productId);
		}
		product.initiateDiscussion(new DiscussionDescriptor(discussionId));
		TimeConstrainedProcessTracker tracker = DomainRegistry.processTrackerRepository().trackerOfProcessId(
				ProcessId.existingProcessId(product.getDiscussionInitiateId()));
		tracker.completed();
	}

	@Transactional
	public void timeOutProductDiscussionRequest(TenantId tenantId, String pid, Date date) {
		ProcessId processId = ProcessId.existingProcessId(pid);
		Product product = DomainRegistry.productRepository().productOfDiscussionInitiationId(
				tenantId,
				processId.getId()
		);
		if(product==null){
			throw new IllegalStateException("unknown tenantId:"+tenantId+" processId:"+pid);
		}
		product.failDiscussionInitiation();
	}

	@Transactional
	public void retryProductDiscussionRequest(TenantId tenantId, String pid) {
		ProcessId processId = ProcessId.existingProcessId(pid);
		Product product = DomainRegistry.productRepository().productOfDiscussionInitiationId(
				tenantId,
				processId.getId()
		);
		if(product==null){
			throw new IllegalStateException("unknown tenantId:"+tenantId+" processId:"+pid);
		}
		product.requestDiscussion(requestDiscussionIfAvailable());
	}

	public void startDiscussionInitiation(String tenantId, String productId) {
		Product product = DomainRegistry.productRepository().productOfId(
				new TenantId(tenantId),
				new ProductId(productId));
		if(product==null){
			throw new IllegalStateException("unknown tenantId:"+tenantId+" of productId:"+productId);
		}
		String timeOutEventName = ProductDiscussionRequestTimedOut.class.getName();
		TimeConstrainedProcessTracker tracker = new TimeConstrainedProcessTracker(
				product.tenantId().id(),
				ProcessId.newProcessId(),
				"Create discussion for product "+product.name(),
				new Date(),
				5*60*1000,
				3,
				timeOutEventName);
		DomainRegistry.processTrackerRepository().add(tracker);
		product.setDiscussionInitiateId(tracker.getProcessId());
	}

	private MessageProducer messageProducer(){
		Exchange exchange = Exchange.topicInstance(
				ConnectionSettings.instance(),
				Exchangers.AGILEPM_EXCHNAGE_NAME,
				true);
		return new MessageProducer(exchange);
	}
}
