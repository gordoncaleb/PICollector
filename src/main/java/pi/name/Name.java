package pi.name;

public class Name {
	private String initial;
	private String name;

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name [initial=" + initial + ", name=" + name + "]";
	}

}
