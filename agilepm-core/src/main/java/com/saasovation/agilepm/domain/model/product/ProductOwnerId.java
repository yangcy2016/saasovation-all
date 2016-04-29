package com.saasovation.agilepm.domain.model.product;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author : huanghy
 * @create : 2016/4/28 0028 обнГ 5:56
 * @since : ${VERSION}
 */
public class ProductOwnerId implements Serializable{
    @Expose
    private String id;

    public ProductOwnerId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
