package com.saasovation.agilepm.domain.model.product;


import com.saasovation.agilepm.domain.model.forum.DiscussionAvailability;
import com.saasovation.agilepm.domain.model.forum.DiscussionDescriptor;
import com.saasovation.agilepm.domain.model.forum.ProductDiscussion;
import com.saasovation.common.domain.ConcurrencySafeEntity;
import com.saasovation.agilepm.domain.model.forum.Discussion;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemType;
import com.saasovation.agilepm.domain.model.product.backlogitem.StoryPoints;
import com.saasovation.agilepm.domain.model.product.release.Release;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.common.domain.DomainEvent;
import com.saasovation.common.domain.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;


public class Product extends ConcurrencySafeEntity {
	private Discussion discussion;
	private Set<ProductBackItem> backlogItems;
	private String description;
	private String name;
	private ProductId productId;
	private TenantId tenantId;
	private ProductOwnerId productOwnerId;
	private String discussionInitiateId;

	private static final Logger logger = LoggerFactory.getLogger(Product.class);

	public Product(TenantId aTenantId, ProductId aProductId,
			String aProductName,ProductOwnerId productOwnerId,String aProductDescription,
			DiscussionAvailability availability) {
		this.tenantId = aTenantId;
		this.productId = aProductId;
		this.name = aProductName;
		this.productOwnerId = productOwnerId;
		this.description = aProductDescription;
		this.discussion = ProductDiscussion.fromAvailability(availability);
		DomainEvent event = new ProductCreated(
				tenantId(),
				productId(),
				productOwnerId(),
				name(),
				description(),
				discussion().availability().isRequested());
		DomainEventPublisher.instance().publish(event);
	}

	public void attachDiscussion(Discussion newDiscussion) {

	}

	public void requestDiscussion(DiscussionAvailability availability){
		if(!this.discussion().availability().isReady()){
			this.setDiscussion(ProductDiscussion.fromAvailability(availability));
			DomainEventPublisher.instance().publish(new ProductDiscussionRequested(
					tenantId(),
					productId(),
					productOwnerId(),
					name(),
					description(),
					discussion().availability().isRequested()
			));
		}
	}

	public Set<ProductBackItem> backlogItems() {
		return this.backlogItems;
	}

	/**
	 * 告诉而非询问原则
	 * 
	 * ::直接告诉服务对象直接操作 而不是先获取backlogItems然后将其传给服务对象
	 * 
	 * @param anId
	 * @param anOrdering
	 */
	public void reorderFrom(BacklogItemId anId, int anOrdering) {
		for (ProductBackItem pbi : this.backlogItems()) {
			pbi.reorderFrom(anId, anOrdering);
		}
	}

	public BacklogItem planBacklogItem(String aSummary, String aCategory,
			BacklogItemType aType, StoryPoints aStoryPoints) {
		return null;
	}

	public Release scheduleRelease(String aName, String aDescription,
			Date aBegins, Date anEnds) {
		return null;
	}

	public Sprint scheduleSprint(String aName, String aGoals, Date aBegins,
			Date anEnds) {
		return null;
	}

	public void describeAs(String description) {
		this.description = description;
	}

	public String description() {
		return this.description;
	}

	public void initiateDiscussion(DiscussionDescriptor descriptor) {
		//判断discussion的状态 保证对discussion的操作是幂等的
		if(discussion().availability().isRequested()){
			this.setDiscussion(this.discussion().nowReady(descriptor));
			DomainEventPublisher.instance().publish(
					new ProductDiscussionInitiated(
							tenantId(),
							productId(),
							discussion()));
			logger.info("init discussion {}",descriptor.getDiscussionId());
		}
	}

	public void rename(String name) {
		this.name = name;
	}

	public String name() {
		return this.name;
	}

	public ProductId productId() {
		return this.productId;
	}

	public TenantId tenantId(){
		return this.tenantId;
	}

	public ProductOwnerId productOwnerId(){
		return this.productOwnerId;
	}

	public Discussion discussion(){
		return discussion;
	}

	public void setDiscussion(Discussion discussion){
		this.discussion = discussion;
	}

	public void failDiscussionInitiation() {
		if(!this.discussion().availability().isReady()){
			this.setDiscussionInitiateId(null);
			this.setDiscussion(ProductDiscussion.fromAvailability(DiscussionAvailability.FAILED));
			DomainEventPublisher.instance().publish(
					new ProductDiscussionInitiateFailed(this));
		}
	}

	public String getDiscussionInitiateId() {
		return discussionInitiateId;
	}

	public void setDiscussionInitiateId(String discussionInitiateId) {
		this.discussionInitiateId = discussionInitiateId;
	}
}
