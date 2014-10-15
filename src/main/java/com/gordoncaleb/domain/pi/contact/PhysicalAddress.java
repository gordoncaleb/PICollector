package com.gordoncaleb.domain.pi.contact;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.gordoncaleb.geocoding.GeoCode;

public class PhysicalAddress {

	@Id
	private String id;

	private String fipsCode;
	private String unformattedApn;
	private String houseNumber;
	private String streetName;
	private String mode;
	private String city;
	private String state;

	private List<GeoCode> geoCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFipsCode() {
		return fipsCode;
	}

	public void setFipsCode(String fipsCode) {
		this.fipsCode = fipsCode;
	}

	public String getUnformattedApn() {
		return unformattedApn;
	}

	public void setUnformattedApn(String unformattedApn) {
		this.unformattedApn = unformattedApn;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<GeoCode> getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(List<GeoCode> geoCode) {
		this.geoCode = geoCode;
	}

	public String generateMailingAddress() {
		return this.houseNumber + " " + this.streetName + " " + this.mode
				+ ", " + this.city + ", " + this.state;
	}

	@Override
	public String toString() {
		return "PhysicalAddress [id=" + id + ", fipsCode=" + fipsCode
				+ ", unformattedApn=" + unformattedApn + ", houseNumber="
				+ houseNumber + ", streetName=" + streetName + ", mode=" + mode
				+ ", city=" + city + ", state=" + state + ", geoCode="
				+ geoCode + "]";
	}

}
