package com.gordoncaleb.domain.pi.contact;

public class EmailAddress {
	private String userName;
	private String domain;

	public EmailAddress() {
		super();
	}

	public EmailAddress(String fullAddress) {
		setFullAddress(fullAddress);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFullAddress() {
		return getUserName() + "@" + getDomain();
	}

	public void setFullAddress(String fullAddress) {
		String[] tokens = fullAddress.split("@");
		if (tokens.length > 1) {
			userName = tokens[0];
			domain = tokens[1];
		}
	}

	@Override
	public String toString() {
		return "EmailAddress [userName=" + userName + ", domain=" + domain + ", fullAddress=" + getFullAddress() + "]";
	}

}
