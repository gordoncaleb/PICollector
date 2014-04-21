package pi.contact;


public abstract class Phone implements Contact {

	protected String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
