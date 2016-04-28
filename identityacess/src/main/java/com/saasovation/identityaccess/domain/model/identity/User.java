package com.saasovation.identityaccess.domain.model.identity;


import com.saasovation.identityaccess.domain.model.user.Person;

public class User {
	private String username;
	private String password;
	private TenantId tenantId;
	private Person person;
	
	
	
	
	public User(String username, TenantId tenantId) {
		super();
		this.username = username;
		this.tenantId = tenantId;
	}

	public User(String username, String password, TenantId tenantId,
			Person person) {
		this.username = username;
		this.password = password;
		this.tenantId = tenantId;
		this.person = person;
	}

	public void changePassword(){
		
	}
	
	public void changePersonName(){
		
	}
	
	public void changeContactInformation(){
		
	}

	public boolean isEnabled() {
		
		return false;
	}

	public UserDescriptor descriptor() {
		return new UserDescriptor(
				person.getContactInformation().getEmailAddress(), 
				tenantId,
				username);
	}

	public String username() {
		return username;
	}

	public Person person(){
		return this.person;
	}

	public TenantId tenantId(){
		return tenantId;
	}
	
}
