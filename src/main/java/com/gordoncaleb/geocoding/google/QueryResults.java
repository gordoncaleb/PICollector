package com.gordoncaleb.geocoding.google;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class QueryResults {

	private String status;

	@JsonProperty(value = "results")
	private List<GoogleGeoCode> geoCodeResults;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GoogleGeoCode> getGeoCodeResults() {
		return geoCodeResults;
	}

	public void setGeoCodeResults(List<GoogleGeoCode> geoCodeResults) {
		this.geoCodeResults = geoCodeResults;
	}

	@Override
	public String toString() {
		return "QueryResults [status=" + status + ", geoCodeResults="
				+ geoCodeResults + "]";
	}

}
