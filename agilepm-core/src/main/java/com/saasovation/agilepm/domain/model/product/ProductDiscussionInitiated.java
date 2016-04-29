package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.forum.Discussion;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ÉÏÎç 10:22
 * @since : ${VERSION}
 */
public class ProductDiscussionInitiated {
    private TenantId tenantId;
    private ProductId productId;
    private Discussion discussion;

    public ProductDiscussionInitiated(TenantId tenantId, ProductId productId, Discussion discussion) {
        this.tenantId = tenantId;
        this.productId = productId;
        this.discussion = discussion;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
}
