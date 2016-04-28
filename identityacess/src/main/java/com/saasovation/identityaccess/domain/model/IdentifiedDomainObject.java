package com.saasovation.identityaccess.domain.model;

public abstract class IdentifiedDomainObject {
	private long id = -1L;

	public IdentifiedDomainObject() {
		super();
	}
	
	public long id(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
}
