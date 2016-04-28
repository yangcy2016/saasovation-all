package com.saasovation.agilepm.application.product;


import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.forum.Discussion;
import com.saasovation.agilepm.domain.model.forum.DiscussionAvailability;
import com.saasovation.agilepm.domain.model.forum.DiscussionDescriptor;
import com.saasovation.agilepm.domain.model.product.BusinessPriorityTotals;
import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

public class ProductApplicationService {

	public BusinessPriorityTotals productBusinessPriority(String aTenantId,
			String aProductId) {

		return DomainRegistry.businessPriorityCalculator()
				.businessPriorityTotals(new TenantId(aTenantId),
						new ProductId(aProductId));
	}

	public String newProduct(String aTenantId, String aProductName,
			String aProductDescription) {
		Product product = new Product(
				//
				new TenantId(aTenantId), //
				new ProductId(DomainRegistry.productRepository().nextIdentity()),//
				aProductName,//
				aProductDescription,//
				new Discussion(//
						new DiscussionDescriptor(
								DiscussionDescriptor.UNDEFIND_ID),//
						DiscussionAvailability.NOT_REQUESTED));//
		//save product to db
		DomainRegistry.productRepository().add(product);
		return product.productId().id();
	}
}
