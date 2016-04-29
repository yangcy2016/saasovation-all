package com.saasovation.agilepm.infrastructure.persistence.mybatis;


import com.saasovation.agilepm.domain.model.forum.DiscussionAvailability;
import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.ProductOwnerId;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.UUID;

public class ProductMybatisRepository implements ProductRepository {

	public Product productOfId(TenantId aTenantId, ProductId aProductId) {

		return new Product(
				aTenantId,
				aProductId,
				"default product",
				new ProductOwnerId("abac"),
				"desc",
				DiscussionAvailability.REQUESTED);
	}

	public String nextIdentity() {
		return UUID.randomUUID().toString();
	}

	public void add(Product product) {

	}

	@Override
	public Product productOfDiscussionInitiationId(TenantId tenantId, String id) {
		return null;
	}

}
