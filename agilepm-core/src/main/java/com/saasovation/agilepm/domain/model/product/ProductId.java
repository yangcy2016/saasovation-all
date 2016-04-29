package com.saasovation.agilepm.domain.model.product;

import com.google.gson.annotations.Expose;

public class ProductId {
	@Expose
	private String id;
	
	public ProductId(String aProductId) {
		this.id = aProductId;
	}

	public String id() {
		return this.id;
	}

}
