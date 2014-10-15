package com.gordoncaleb.geocoding;

public class Geometry {

	private LatLonCoord location;
	
	private ViewPort viewport;

	public LatLonCoord getLocation() {
		return location;
	}

	public void setLocation(LatLonCoord location) {
		this.location = location;
	}

	public ViewPort getViewport() {
		return viewport;
	}

	public void setViewport(ViewPort viewport) {
		this.viewport = viewport;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + ", viewport=" + viewport
				+ "]";
	}

}
