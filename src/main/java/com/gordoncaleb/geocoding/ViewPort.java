package com.gordoncaleb.geocoding;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViewPort {

	@JsonProperty(value = "northeast")
	private LatLonCoord northEast;

	@JsonProperty(value = "southwest")
	private LatLonCoord southWest;

	public LatLonCoord getNorthEast() {
		return northEast;
	}

	public void setNorthEast(LatLonCoord northEast) {
		this.northEast = northEast;
	}

	public LatLonCoord getSouthWest() {
		return southWest;
	}

	public void setSouthWest(LatLonCoord southWest) {
		this.southWest = southWest;
	}

	@Override
	public String toString() {
		return "ViewPort [northEast=" + northEast + ", southWest=" + southWest
				+ "]";
	}

}
