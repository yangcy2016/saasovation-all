package com.saasovation.agilepm.infrastructure.persistence.mybatis;


import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

public class ProductMybatisRepository implements ProductRepository {

	public Product productOfId(TenantId aTenantId, ProductId aProductId) {
		
		return null;
	}

	public String nextIdentity() {
		
		return null;
	}

	public void add(Product product) {

	}

}
