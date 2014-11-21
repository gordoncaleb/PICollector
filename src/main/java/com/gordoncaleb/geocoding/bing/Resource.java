package com.gordoncaleb.geocoding.bing;

public class Resource {

	private String name;
	private Point point;
	private Address address;
	private String confidence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	@Override
	public String toString() {
		return "Resource [name=" + name + ", point=" + point + ", address=" + address + ", confidence=" + confidence + "]";
	}

}
