package com.gordoncaleb.geocoding.bing;

import java.util.List;

public class QueryResults {

	private String statusCode;
	private String statusDescription;

	private List<ResourceSets> resourceSets;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<ResourceSets> getResourceSets() {
		return resourceSets;
	}

	public void setResourceSets(List<ResourceSets> resourceSets) {
		this.resourceSets = resourceSets;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return "QueryResults [statusCode=" + statusCode + ", statusDescription=" + statusDescription + ", resourceSets=" + resourceSets + "]";
	}

}
