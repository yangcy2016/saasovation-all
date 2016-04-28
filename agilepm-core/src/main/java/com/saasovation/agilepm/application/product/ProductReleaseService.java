package com.saasovation.agilepm.application.product;

import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.release.Release;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;



public class ProductReleaseService {

	public void scheduleRelease(String aTenantId, String aProductId,
			String aName, String aDescription, Date aBegins, Date anEnds) {
		Product product = DomainRegistry.productRepository().productOfId(
				new TenantId(aTenantId), new ProductId(aProductId));
		Release release = product.scheduleRelease(aName, aDescription, aBegins, anEnds);
		DomainRegistry.releaseRepository().add(release);
	}
}
