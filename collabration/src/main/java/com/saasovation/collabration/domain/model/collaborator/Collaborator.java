package com.saasovation.collabration.domain.model.collaborator;

public class Collaborator {
	protected String emailAddress;
	protected String name;
	public Collaborator(String emailAddress, String name) {
		super();
		this.emailAddress = emailAddress;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
