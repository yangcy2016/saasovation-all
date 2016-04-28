package com.saasovation.collabration.domain.model;

public class Tenant {
	private String tenantId;
	public Tenant(String aTenantId) {
		
		this.tenantId = aTenantId;
	}
	
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	

}
