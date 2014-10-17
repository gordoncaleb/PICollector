package com.gordoncaleb.schoolsitelocator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.util.JSON;
import com.gordoncaleb.util.WebUtil;

public class SchoolSiteLocatorAPI {

	public static void main(String[] args) {

		PhysicalAddress addr = new PhysicalAddress();
		addr.setHouseNumber("4924");
		addr.setStreetName("INDIAN CORN");
		addr.setMode("TRL");

		try {

			SchoolSiteLocatorAPI locator = new SchoolSiteLocatorAPI();

			System.out.println("Getting " + addr);
			Map<String, Object> resp = locator.findAddressCandidates(addr
					.generateStreetAddress());

			System.out.println(resp);

			String[] loc = locator.getBestCandidateLocation(resp);

			System.out.println("Best candidate location: x:" + loc[0] + " y:"
					+ loc[1]);

			resp = locator.queryForSchoolCode(loc[0], loc[1]);

			System.out.println(resp);

			Map<String, String> schoolCodes = locator.getSchoolCodes(resp);

			System.out.println(" Schools:" + schoolCodes);

			resp = locator.queryForSchoolInfo(schoolCodes.keySet());

			System.out.println(resp);

			List<School> schools = locator.parseSchoolObject(resp);

			for (School s : schools) {
				s.setType(schoolCodes.get(s.getSchoolCode()));
				System.out.println(s);
			}

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String, String> getSchoolCodes(PhysicalAddress addr)
			throws Exception {

		//System.out.println("Getting " + addr);
		Map<String, Object> resp = findAddressCandidates(addr
				.generateStreetAddress());

		//System.out.println(resp);

		String[] loc = getBestCandidateLocation(resp);

		//System.out.println("Best candidate location: x:" + loc[0] + " y:"
		//		+ loc[1]);

		resp = queryForSchoolCode(loc[0], loc[1]);

		//System.out.println(resp);

		Map<String, String> schoolCodes = getSchoolCodes(resp);

		//System.out.println("Schools:" + schoolCodes);

		return schoolCodes;

	}

	public List<School> getSchoolInfo(Collection<String> schoolCode)
			throws Exception {

		Map<String, Object> resp = queryForSchoolInfo(schoolCode);

		//System.out.println(resp);

		List<School> schools = parseSchoolObject(resp);

//		for (School s : schools) {
//			System.out.println(s);
//		}

		return schools;

	}

	public Map<String, Object> findAddressCandidates(String streetAddress)
			throws JsonParseException, JsonMappingException, IOException,
			URISyntaxException {

		String uri = "http://maps.schoolsitelocator.com/ArcGIS/rest/services/geocodeServices/newhanover/GeocodeServer/findAddressCandidates?Street="
				+ streetAddress + "&f=json&outSR={\"wkid\":102100}&outFields=*";

		String json = WebUtil.get(uri);

		return JSON.getStringObjectMap(json);
	}

	public String[] getBestCandidateLocation(Map<String, Object> candidates)
			throws Exception {
		Object list = JSON.getElement(candidates, "candidates");

		if (list instanceof List<?>) {
			Map<String, Object> best = null;
			Double bestScore = null;

			for (Object c : (List<?>) list) {

				if (c instanceof Map<?, ?>) {
					Map<String, Object> cand = (Map<String, Object>) c;

					try {
						Double score = Double.parseDouble(cand.get("score")
								.toString());

						if (bestScore == null || bestScore < score) {
							bestScore = score;
							best = cand;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (best != null) {
				String x = JSON.getElement(best, "location", "x").toString();
				String y = JSON.getElement(best, "location", "y").toString();
				return new String[] { x, y };
			} else {
				throw new Exception("No candidate!");
			}
		} else {
			throw new Exception("No candidates list found!");
		}

	}

	public Map<String, Object> queryForSchoolCode(String x, String y)
			throws URISyntaxException, IOException {

		String uri = "http://maps.schoolsitelocator.com/ArcGIS/rest/services/sslJS_NZ/MapServer/26/query?f=json&where=&returnGeometry=false&spatialRel=esriSpatialRelIntersects"
				+ "&geometry={\"x\":"
				+ x
				+ ",\"y\":"
				+ y
				+ ",\"spatialReference\":{\"wkid\":102100,\"latestWkid\":3857}}&geometryType=esriGeometryPoint&inSR=102100&outFields=STDYAREA,ELEM_,MID_,INT_,HIGH_,ADD_SCHL1,ADD_SCHL2,ADD_SCHL3,ADD_SCHL4,ADD_SCHL5&outSR=102100";

		// http://maps.schoolsitelocator.com/ArcGIS/rest/services/sslJS_NZ/MapServer/26/query?f=json&where=&returnGeometry=false&spatialRel=esriSpatialRelIntersects&geometry={"x":-8675163.214874761,"y":4075442.8399342047,"spatialReference":{"wkid":102100,"latestWkid":3857}}&geometryType=esriGeometryPoint&inSR=102100&outFields=STDYAREA,ELEM_,MID_,INT_,HIGH_,ADD_SCHL1,ADD_SCHL2,ADD_SCHL3,ADD_SCHL4,ADD_SCHL5&outSR=102100

		String json = WebUtil.get(uri);

		return JSON.getStringObjectMap(json);
	}

	public Map<String, String> getSchoolCodes(Map<String, Object> queryResp)
			throws Exception {

		List<Map<String, Object>> features = (List<Map<String, Object>>) JSON
				.getElement(queryResp, "features");

		if (!features.isEmpty()) {

			Map<String, Object> attribs = (Map<String, Object>) features.get(0)
					.get("attributes");

			Map<String, String> schoolCodes = new HashMap<String, String>();

			schoolCodes.put(attribs.get("ELEM_").toString(), "ELEM");
			schoolCodes.put(attribs.get("INT_").toString(), "INT");
			schoolCodes.put(attribs.get("HIGH_").toString(), "HIGH");

			return schoolCodes;
		} else {
			throw new Exception("No attributes found!");
		}

	}

	public Map<String, Object> queryForSchoolInfo(Collection<String> schoolCodes)
			throws URISyntaxException, IOException {

		StringBuilder sb = new StringBuilder();

		Iterator<String> it = schoolCodes.iterator();

		while (it.hasNext()) {
			sb.append("SCHL_CODE=");
			sb.append(it.next());

			if (it.hasNext()) {
				sb.append(" OR ");
			}
		}

		String uri = "http://maps.schoolsitelocator.com/ArcGIS/rest/services/sslJS_NZ/MapServer/25/query?f=json&where="
				+ sb.toString()
				+ "&returnGeometry=true&spatialRel=esriSpatialRelIntersects&outFields=NAME,SCHL_CODE,ADDRESS,CITY,PHONE,WEBSITE,NOTES,GRD_RANGE,STRT_GRD,END_GRD&orderByFields=STRT_GRD,NAME&outSR=102100";

		// http://maps.schoolsitelocator.com/ArcGIS/rest/services/sslJS_NZ/MapServer/25/query?f=json&where=SCHL_CODE=309
		// OR SCHL_CODE=343 OR
		// SCHL_CODE=326&returnGeometry=true&spatialRel=esriSpatialRelIntersects&outFields=NAME,SCHL_CODE,ADDRESS,CITY,PHONE,WEBSITE,NOTES,GRD_RANGE,STRT_GRD,END_GRD&orderByFields=STRT_GRD,NAME&outSR=102100

		//System.out.println("URI: " + uri);
		String json = WebUtil.get(uri);

		return JSON.getStringObjectMap(json);
	}

	public List<School> parseSchoolObject(Map<String, Object> schoolInfos)
			throws Exception {
		List<Map<String, Object>> features = (List<Map<String, Object>>) JSON
				.getElement(schoolInfos, "features");

		List<School> schools = new ArrayList<School>();

		for (Map<String, Object> feat : features) {

			Map<String, Object> attribs = (Map<String, Object>) feat
					.get("attributes");

			School school = JSON.fromJSON(JSON.toJSON(attribs), School.class);

			schools.add(school);

		}

		return schools;

	}
}
