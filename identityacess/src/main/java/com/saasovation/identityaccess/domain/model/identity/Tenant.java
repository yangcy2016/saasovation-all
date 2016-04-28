package com.saasovation.identityaccess.domain.model.identity;


import com.saasovation.identityaccess.domain.model.user.Person;

public class Tenant {
	private TenantId id;
	private String name;
	private boolean activate;
	
	
	
	public Tenant() {
		super();
	}
	public Tenant(TenantId id, String name, boolean activate) {
		super();
		this.id = id;
		this.name = name;
		this.activate = activate;
	}
	public boolean isActivate(){
		return this.activate;
	}
	public void activate(){
		this.activate = true;
	}
	public void deactivate(){
		this.activate = false;
	}
	
	public User registerUser(String aUsername,String aPassword,
							TenantId aTenantId,Person aPerson){
		return new User(
						aUsername, 
						aPassword, 
						aTenantId, 
						aPerson);
	}
	
}
