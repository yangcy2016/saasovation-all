package com.saasovation.agilepm.infrastructure.persistence.mybatis;

import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemRepository;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.ArrayList;
import java.util.List;



public class BacklogItemMybatisRepository implements BacklogItemRepository {

	public List<BacklogItem> allOutstandingBacklogItems(Tenant tenant,
			ProductId productId) {
		// TODO Auto-generated method stub
		return new ArrayList<BacklogItem>();
	}

	public BacklogItem backlogItemOfId(BacklogItemId aBacklogItemId) {
		// TODO Auto-generated method stub
		SprintId sprintId = new SprintId();
		TenantId tenantId = new TenantId("123");
		return new BacklogItem(aBacklogItemId, sprintId, tenantId);
	}

	public void add(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
		
	}

	public BacklogItem backlogItemOfId(TenantId tenantId,
			BacklogItemId backlogItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
		
	}

}
