package com.gordoncaleb.geocoding.bing;

public class Address {

	private String addressLine1;
	private String adminDistrict;
	private String adminDistrict2;
	private String countryRegion;
	private String formattedAddress;
	private String locality;
	private String postalCode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAdminDistrict() {
		return adminDistrict;
	}

	public void setAdminDistrict(String adminDistrict) {
		this.adminDistrict = adminDistrict;
	}

	public String getAdminDistrict2() {
		return adminDistrict2;
	}

	public void setAdminDistrict2(String adminDistrict2) {
		this.adminDistrict2 = adminDistrict2;
	}

	public String getCountryRegion() {
		return countryRegion;
	}

	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", adminDistrict=" + adminDistrict + ", adminDistrict2=" + adminDistrict2
				+ ", countryRegion=" + countryRegion + ", formattedAddress=" + formattedAddress + ", locality=" + locality + ", postalCode="
				+ postalCode + "]";
	}

}
