package com.saasovation.agilepm.domain.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.common.domain.AbstractDomainEvent;

/**
 * @author : huanghy
 * @create : 2016/4/28 0028 ÏÂÎç 5:54
 * @since : ${VERSION}
 */
public class ProductCreated  extends AbstractDomainEvent{
    @Expose
    @SerializedName("tenantId")
    private TenantId tenantId;
    @Expose
    @SerializedName("productId")
    private ProductId productId;
    @Expose
    @SerializedName("productOwnerId")
    private ProductOwnerId productOwnerId;
    @Expose
    @SerializedName("productName")
    private String productName;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("requestingDiscussion")
    private boolean requestingDiscussion;

    public ProductCreated(TenantId tenantId, ProductId productId, ProductOwnerId productOwnerId, String productName, String description, boolean isRequested) {
        this.tenantId = tenantId;
        this.productId = productId;
        this.productOwnerId = productOwnerId;
        this.productName = productName;
        this.description = description;
        this.requestingDiscussion = isRequested;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequestingDiscussion() {
        return requestingDiscussion;
    }

    public void setIsRequested(boolean isRequested) {
        this.requestingDiscussion = isRequested;
    }
}
