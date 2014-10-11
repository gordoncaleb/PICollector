package com.google.geocoding;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import util.JSON;
import util.WebUtil;

public class GeoCode {

	public static final String APIKEY = "AIzaSyD0ovoqmGiuoWuZbLPmCh4OK2vV3bzE9Zk";

	public static void main(String args[]) {

		try {
			System.out.println(GeoCode.getGeoCode("1759 U st NW, Washington DC, 20009"));

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getGeoCode(String address) throws URISyntaxException, IOException {
		
		String googleApiUrl = "https://maps.googleapis.com/maps/api/geocode/json?key=" + APIKEY + "&address=";

		String json = WebUtil.get(googleApiUrl + address);

		System.out.println("JSON response:\n" + json);

		List<Map<String, Object>> results = (List<Map<String, Object>>) JSON.getStringObjectMap(json).get("results");

		for (Map<String, Object> map : results) {
			try {
				Object lat = JSON.getElement(map, "geometry", "location", "lat");

				System.out.println("Lat:" + lat.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Object lon = JSON.getElement(map, "geometry", "location", "lng");

				System.out.println("Lon:" + lon.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return json;

	}
}
