package com.saasovation.agilepm.domain.model.product;


import com.saasovation.common.domain.ConcurrencySafeEntity;
import com.saasovation.agilepm.domain.model.forum.Discussion;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemType;
import com.saasovation.agilepm.domain.model.product.backlogitem.StoryPoints;
import com.saasovation.agilepm.domain.model.product.release.Release;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;
import java.util.Set;


public class Product extends ConcurrencySafeEntity {
	private Discussion discussion;
	private Set<ProductBackItem> backlogItems;
	private String description;
	private String name;
	private ProductId productId;
	private TenantId tenantId;

	public Product(TenantId aTenantId, ProductId aProductId,
			String aProductName, String aProductDescription,
			Discussion productDiscussion) {
		this.tenantId = aTenantId;
		this.productId = aProductId;
		this.name = aProductName;
		this.description = aProductDescription;
		this.discussion = productDiscussion;
	}

	public void attachDiscussion(Discussion newDiscussion) {

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

	public void initiateDiscussion() {

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
}
