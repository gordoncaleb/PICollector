package pi.contact;

public class Phone {

	private String number;
	private String provider;

	public Phone() {
		super();
	}

	public Phone(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "Phone [number=" + number + ", provider=" + provider + "]";
	}

}
