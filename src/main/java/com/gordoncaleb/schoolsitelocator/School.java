package com.gordoncaleb.schoolsitelocator;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;

public class School {

	@Id
	private String id;

	@JsonProperty(value = "NAME")
	private String name;

	@JsonProperty(value = "SCHL_CODE")
	private String schoolCode;

	@JsonProperty(value = "ADDRESS")
	private String address;

	@JsonProperty(value = "CITY")
	private String city;

	@JsonProperty(value = "PHONE")
	private String phone;

	@JsonProperty(value = "WEBSITE")
	private String website;

	@JsonProperty(value = "NOTES")
	private String notes;

	@JsonProperty(value = "GRD_RANGE")
	private String gradeRange;

	@JsonProperty(value = "STRT_GRD")
	private String startGrade;

	@JsonProperty(value = "END_GRD")
	private String endGrade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getGradeRange() {
		return gradeRange;
	}

	public void setGradeRange(String gradeRange) {
		this.gradeRange = gradeRange;
	}

	public String getStartGrade() {
		return startGrade;
	}

	public void setStartGrade(String startGrade) {
		this.startGrade = startGrade;
	}

	public String getEndGrade() {
		return endGrade;
	}

	public void setEndGrade(String endGrade) {
		this.endGrade = endGrade;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", schoolCode=" + schoolCode + ", address=" + address + ", city=" + city + ", phone=" + phone
				+ ", website=" + website + ", notes=" + notes + ", gradeRange=" + gradeRange + ", startGrade=" + startGrade + ", endGrade="
				+ endGrade + "]";
	}

}
