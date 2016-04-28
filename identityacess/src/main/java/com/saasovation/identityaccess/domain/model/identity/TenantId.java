package com.saasovation.identityaccess.domain.model.identity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TenantId {

	@Expose
	@SerializedName("id")
	private String tenantId;
	
	public TenantId(String aTenantId) {
		this.tenantId = aTenantId;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	

}
