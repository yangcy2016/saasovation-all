package com.saasovation.agilepm.domain.model.product;

import com.saasovation.common.domain.AbstractDomainEvent;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ионГ 11:19
 * @since : ${VERSION}
 */
public class ProductDiscussionInitiateFailed extends AbstractDomainEvent{
    private Product product;

    public ProductDiscussionInitiateFailed(Product product) {
        this.product = product;
    }

    public Product product(){
        return this.product;
    }
}
