package com.gordoncaleb.geocoding.bing;

import java.util.List;

public class Point {

	private List<String> coordinates;

	public List<String> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<String> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Point [coordinates=" + coordinates + "]";
	}

}
