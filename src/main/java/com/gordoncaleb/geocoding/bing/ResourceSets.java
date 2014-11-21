package com.gordoncaleb.geocoding.bing;

import java.util.List;

public class ResourceSets {

	private String estimatedTotal;

	private List<Resource> resources;

	public String getEstimatedTotal() {
		return estimatedTotal;
	}

	public void setEstimatedTotal(String estimatedTotal) {
		this.estimatedTotal = estimatedTotal;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "ResourceSets [estimatedTotal=" + estimatedTotal + ", resources=" + resources + "]";
	}

}
