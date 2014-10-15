package com.gordoncaleb.geocoding;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.gordoncaleb.util.JSON;

public class GeocodingTest {

	@Test
	public void shouldParse() throws JsonParseException, JsonMappingException,
			IOException {
		String test = "{\"location\" : {\"lat\" : 34.347497, \"lng\" : -77.92895 }, \"location_type\" : \"ROOFTOP\"}";

		Geometry geometry = null;

		geometry = JSON.fromJSON(test, Geometry.class);

		// System.out.println(geometry);

	}

	@Test
	public void shouldParseAll() throws Exception {

		String test = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"1759\",               \"short_name\" : \"1759\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"U Street Northwest\",               \"short_name\" : \"U St NW\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Northwest Washington\",               \"short_name\" : \"Northwest Washington\",               \"types\" : [ \"neighborhood\", \"political\" ]            },            {               \"long_name\" : \"Washington\",               \"short_name\" : \"Washington\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"District of Columbia\",               \"short_name\" : \"DC\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"United States\",               \"short_name\" : \"US\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"20009\",               \"short_name\" : \"20009\",               \"types\" : [ \"postal_code\" ]            }         ],         \"formatted_address\" : \"1759 U Street Northwest, Washington, DC 20009, USA\",         \"geometry\" : {            \"location\" : {               \"lat\" : 38.917221,               \"lng\" : -77.040426            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 38.9185699802915,                  \"lng\" : -77.03907701970849               },               \"southwest\" : {                  \"lat\" : 38.9158720197085,                  \"lng\" : -77.04177498029151               }            }         },         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\"}";

		QueryResults results = GeoCodeAPI.parseGeoCodeJson(test);

		assertTrue(GeoCodeAPI.queryWasSuccess(results));

		System.out.println(results);

	}
}
