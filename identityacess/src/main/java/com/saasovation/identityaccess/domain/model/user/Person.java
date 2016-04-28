package com.saasovation.identityaccess.domain.model.user;

public class Person {
	private Name name;
	private ContactInformation contactInformation;
	public Person(Name name, ContactInformation contactInformation) {
		super();
		this.name = name;
		this.contactInformation = contactInformation;
	}
	public Name getName() {
		return name;
	}
	public ContactInformation getContactInformation() {
		return contactInformation;
	}
}
