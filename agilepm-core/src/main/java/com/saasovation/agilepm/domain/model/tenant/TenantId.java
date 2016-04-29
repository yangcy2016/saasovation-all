package com.saasovation.agilepm.domain.model.tenant;

import com.google.gson.annotations.Expose;

/**
 * value object
 * @author Administrator
 *
 */
public class TenantId {
	@Expose
	private String id;
	
	public TenantId(String aTenantId) {
		this.id = aTenantId;
	}

	public String id(){
		return id;
	}
}
