package com.gordoncaleb.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class StringUtils {

	public static final String emailRegex = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	public static int stringMatchesIgnoreCase(List<String> aList, List<String> bList) {

		int matches = 0;

		for (String a : aList) {
			for (String b : bList) {
				if (a.equalsIgnoreCase(b)) {
					matches++;
				}
			}
		}

		return matches;
	}

	public static void removeMatchesIgnoreCase(List<String> aList, List<String> bList) {

		Iterator<String> ita = aList.iterator();
		Iterator<String> itb;
		while (ita.hasNext()) {
			String a = ita.next();

			itb = bList.iterator();
			while (itb.hasNext()) {
				String b = itb.next();

				if (a.equalsIgnoreCase(b)) {
					itb.remove();
					ita.remove();
					break;
				}
			}

		}

	}

	public static List<List<String>> getMatches(String regex, CharSequence seq) {
		List<List<String>> allMatches = new ArrayList<>();

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(seq);

		List<String> groups;
		while (m.find()) {
			groups = new ArrayList<String>();

			for (int i = 0; i < m.groupCount() + 1; i++) {
				groups.add(m.group(i));
			}

			allMatches.add(groups);
		}

		return allMatches;
	}

	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
			});
		} catch (Exception e) {

			try {
				map.put("list", mapper.readValue(json, new TypeReference<ArrayList<Object>>() {

				}));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		return map;
	}

	public static String getBaseURL(String url) {
		int last = url.lastIndexOf("/");
		if (last > 0) {
			return url.substring(0, last);
		} else {
			return url;
		}
	}
}
