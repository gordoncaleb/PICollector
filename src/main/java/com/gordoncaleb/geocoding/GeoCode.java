package com.gordoncaleb.geocoding;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoCode {

	@JsonProperty(value = "geometry")
	private Geometry geometry;

	@JsonProperty(value = "formatted_address")
	private String formattedAddress;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	@Override
	public String toString() {
		return "GeoCode [geometry=" + geometry + ", formattedAddress="
				+ formattedAddress + "]";
	}

}
