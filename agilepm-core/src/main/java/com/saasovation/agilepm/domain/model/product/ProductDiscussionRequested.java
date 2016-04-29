package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.common.domain.AbstractDomainEvent;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 8:49
 * @since : ${VERSION}
 */
public class ProductDiscussionRequested extends AbstractDomainEvent{
    private TenantId tenantId;
    private ProductId productId;
    private ProductOwnerId productOwnerId;
    private String productName;
    private String descriptor;
    //协作附加功能是否启用
    private boolean requestingDiscussion;

    public ProductDiscussionRequested(TenantId tenantId, ProductId productId, ProductOwnerId productOwnerId,
                                      String productName, String descriptor, boolean enabled) {
        this.tenantId = tenantId;
        this.productId = productId;
        this.productOwnerId = productOwnerId;
        this.productName = productName;
        this.descriptor = descriptor;
        this.requestingDiscussion = enabled;
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

    public ProductOwnerId getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(ProductOwnerId productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public boolean isRequestingDiscussion() {
        return requestingDiscussion;
    }

    public void setRequestingDiscussion(boolean isRequest) {
        this.requestingDiscussion = isRequest;
    }
}
