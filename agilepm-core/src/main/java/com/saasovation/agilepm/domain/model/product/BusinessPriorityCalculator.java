package com.saasovation.agilepm.domain.model.product;


import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Collection;

public class BusinessPriorityCalculator {
	
	public BusinessPriorityTotals businessPriorityTotals(TenantId aTenantId,ProductId aProductId){
		int totalBenefit = 0;
		int totalPenalty = 0;
		int totalCost    = 0;
		int totalRisk    = 0;
		Tenant tenant = DomainRegistry.tenantRepository()
				.tenantOfId(aTenantId);
		Collection<BacklogItem> outstandingBacklogItems =
				DomainRegistry.backlogItemRepository()
				.allOutstandingBacklogItems(tenant,aProductId);
		for(BacklogItem backlogItem:outstandingBacklogItems){
			BusinessPriorityRating rating = backlogItem.businessPriority().rating();
			totalBenefit +=rating.benefit();
			totalPenalty +=rating.penalty();
			totalCost    +=rating.cost();
			totalRisk	 +=rating.risk();
		}
		BusinessPriorityTotals businessPriorityTotals = 
				new BusinessPriorityTotals(
						totalBenefit,
						totalPenalty,
						totalBenefit+totalPenalty,
						totalCost,
						totalRisk);
		
		return businessPriorityTotals;
	}
}
