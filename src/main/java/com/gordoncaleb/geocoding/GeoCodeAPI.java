package com.gordoncaleb.geocoding;

import java.util.List;

import com.gordoncaleb.util.JSON;
import com.gordoncaleb.util.WebUtil;

public class GeoCodeAPI {

	public static int apiIndex = 0;

	public static final String[] APIKEY = {
			"AIzaSyD0ovoqmGiuoWuZbLPmCh4OK2vV3bzE9Zk",
			"AIzaSyAh_Clk31HQGxUdWMcUWsQSc2fATSZTXOo",
			"AIzaSyCW0AUkKJwBf1JJlMk9xoPPF-Ux1D-Wx0g",
			"AIzaSyBkw6ePv7i7GUF8g39QlS2XCzxeuEZs0vY",
			"AIzaSyCEXwVXImPidJb1SzKmuK6XONvbyQ5u6wU",
			"AIzaSyBfne0q65uD2BnGhFWSwu7XY93tyddI3pg",
			"AIzaSyBRSnI1U2g1n5cpUrrtM1m7rQmh5auPTzk",
			"AIzaSyB02bHtJz-_EwrD66wZCJuXfP_Mk0x8AO8" };
	
	public static void main(String args[]) {

		try {
			System.out.println(GeoCodeAPI
					.queryForGeoCode("4924 INDIAN CORN TRL CASTLE HAYNE NC"));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static List<GeoCode> queryForGeoCode(String address)
			throws Exception {

		String googleApiUrl = "https://maps.googleapis.com/maps/api/geocode/json?key="
				+ APIKEY[apiIndex] + "&address=";

		String json = WebUtil.get(googleApiUrl + address);

		QueryResults results = parseGeoCodeJson(json);

		if (!queryWasSuccess(results)) {
			throw new Exception("API returned non-OK status!");
		}

		return results.getGeoCodeResults();
	}

	public static QueryResults parseGeoCodeJson(String json) throws Exception {

		QueryResults results = JSON.getCarelessJsonParser().readValue(json,
				QueryResults.class);

		return results;

	}

	public static boolean queryWasSuccess(QueryResults results) {
		return results.getStatus().equalsIgnoreCase("OK");
	}

	// public static String getGeoCodeString(String address)
	// throws URISyntaxException, IOException {
	//
	// String googleApiUrl =
	// "https://maps.googleapis.com/maps/api/geocode/json?key="
	// + APIKEY + "&address=";
	//
	// String json = WebUtil.get(googleApiUrl + address);
	//
	// System.out.println("JSON response:\n" + json);
	//
	// List<Map<String, Object>> results = (List<Map<String, Object>>) JSON
	// .getStringObjectMap(json).get("results");
	//
	// for (Map<String, Object> map : results) {
	// try {
	// Object lat = JSON
	// .getElement(map, "geometry", "location", "lat");
	//
	// System.out.println("Lat:" + lat.toString());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// Object lon = JSON
	// .getElement(map, "geometry", "location", "lng");
	//
	// System.out.println("Lon:" + lon.toString());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// return json;
	//
	// }

}
