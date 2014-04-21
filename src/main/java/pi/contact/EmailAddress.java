package pi.contact;


public class EmailAddress implements Contact {
	private String userName;
	private String domain;

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

	@Override
	public String toString() {
		return "EmailAddress [userName=" + userName + ", domain=" + domain
				+ "]";
	}

	public static String getFullAddress(EmailAddress a) {
		return a.getUserName() + "@" + a.getDomain();
	}

}
