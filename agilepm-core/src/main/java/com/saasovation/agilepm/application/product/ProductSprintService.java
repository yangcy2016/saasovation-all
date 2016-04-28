package com.saasovation.agilepm.application.product;

import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;


public class ProductSprintService {

	public void scheduleSprint(String aTenantId, String aProductId, String aName,
			String aGoals, Date aBegins, Date anEnds) {
		Product product = DomainRegistry.productRepository().productOfId(
				new TenantId(aTenantId), new ProductId(aProductId));
		Sprint sprint = product.scheduleSprint(aName, aGoals, aBegins, anEnds);
		DomainRegistry.sprintRepository().add(sprint);
	}
}
