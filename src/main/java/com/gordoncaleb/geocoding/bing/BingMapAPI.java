package com.gordoncaleb.geocoding.bing;

import java.util.List;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.util.JSON;
import com.gordoncaleb.util.WebUtil;

public class BingMapAPI {

	private static String key = "Aoz4X3h3n-aR8ks1pQeWTa367mTp9MpEq2IZF5KU2pcvYQmoNWcoA5l7d7NE1fL_";

	// http://dev.virtualearth.net/REST/v1/Locations?
	// countryRegion=countryRegion
	// &adminDistrict=adminDistrict
	// &locality=locality
	// &postalCode=postalCode
	// &addressLine=addressLine
	// &userLocation=userLocation
	// &userIp=userIp
	// &usermapView=usermapView
	// &includeNeighborhood=includeNeighborhood
	// &maxResults=maxResults
	// &key=BingMapsKey

	public static void main(String args[]) {

		try {

			PhysicalAddress addr = new PhysicalAddress();

			addr.setCity("WILMINGTON");
			addr.setState("NC");
			addr.setStreetName("INMAN PARK");
			addr.setHouseNumber("1214");

			List<ResourceSets> sets = BingMapAPI.queryForGeoCode(addr);

			for (ResourceSets set : sets) {
				System.out.println(set);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static List<ResourceSets> queryForGeoCode(PhysicalAddress addr) throws Exception {

		String state = addr.getState();
		String city = addr.getCity();
		String addressLine = addr.getHouseNumber() + " " + addr.getStreetName();
		String country = "US";

		String bingApiUrl = "http://dev.virtualearth.net/REST/v1/Locations?countryRegion=" + country + "&adminDistrict=" + state + "&locality="
				+ city + "&addressLine=" + addressLine + "&key=" + key;

		String json = WebUtil.get(bingApiUrl);

		System.out.println(JSON.prettyPrint(json));

		QueryResults results = parseGeoCodeJson(json);

		if (!queryWasSuccess(results)) {
			throw new Exception("API returned non-OK status!");
		}

		return results.getResourceSets();
	}

	public static QueryResults parseGeoCodeJson(String json) throws Exception {

		QueryResults results = JSON.getCarelessJsonParser().readValue(json, QueryResults.class);

		return results;

	}

	public static boolean queryWasSuccess(QueryResults results) {
		return results.getStatusDescription().equalsIgnoreCase("OK");
	}
}
