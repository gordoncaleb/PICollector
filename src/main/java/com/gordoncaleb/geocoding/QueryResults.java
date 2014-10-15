package com.gordoncaleb.geocoding;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class QueryResults {

	private String status;

	@JsonProperty(value = "results")
	private List<GeoCode> geoCodeResults;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GeoCode> getGeoCodeResults() {
		return geoCodeResults;
	}

	public void setGeoCodeResults(List<GeoCode> geoCodeResults) {
		this.geoCodeResults = geoCodeResults;
	}

	@Override
	public String toString() {
		return "QueryResults [status=" + status + ", geoCodeResults="
				+ geoCodeResults + "]";
	}

}
