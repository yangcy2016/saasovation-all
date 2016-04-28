package com.saasovation.agilepm.domain.model.product;


import com.saasovation.agilepm.domain.model.tenant.TenantId;

public interface ProductRepository {
	Product productOfId(TenantId aTenantId, ProductId aProductId);

	String nextIdentity();

	void add(Product product);
}
