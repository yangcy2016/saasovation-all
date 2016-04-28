package com.saasovation.identityaccess.domain.model.user;

public class ContactInformation {
	private String emailAddress;
	private String telephone;
	private String homeAddress;
	public ContactInformation(String emailAddress, String telephone,
			String homeAddress) {
		super();
		this.emailAddress = emailAddress;
		this.telephone = telephone;
		this.homeAddress = homeAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
}
