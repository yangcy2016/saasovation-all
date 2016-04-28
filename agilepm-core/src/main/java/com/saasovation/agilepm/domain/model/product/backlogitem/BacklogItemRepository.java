package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.List;



public interface BacklogItemRepository {

	List<BacklogItem> allOutstandingBacklogItems(Tenant tenant,
												 ProductId productId);

	BacklogItem backlogItemOfId(BacklogItemId aBacklogItemId);

	void add(BacklogItem backlogItem);

	BacklogItem backlogItemOfId(TenantId tenantId, BacklogItemId backlogItemId);

	void update(BacklogItem backlogItem);

}
