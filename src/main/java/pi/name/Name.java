package pi.name;

import java.util.ArrayList;
import java.util.List;

public class Name {
	private String name = "";

	public Name() {
		super();
	}

	public Name(String name) {
		super();
		this.name = name;
	}

	public String getInitial() {
		if (name != null) {
			return name.substring(0, 1).toUpperCase();
		} else {
			return "";
		}
	}

	public void setInitial(String initial) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name [initial=" + this.getInitial() + ", name=" + name + "]";
	}
	
	public boolean match(String s){
		return name.equalsIgnoreCase(s);
	}

	public static List<String> namesToStrings(List<Name> names) {
		List<String> strings = new ArrayList<String>();

		for (Name n : names) {
			strings.add(n.getName());
		}

		return strings;
	}

	public static List<Name> stringsToNames(List<String> strings) {
		List<Name> names = new ArrayList<Name>();

		for (String s : strings) {
			names.add(new Name(s));
		}

		return names;
	}

}
