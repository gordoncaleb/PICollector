package com.gordoncaleb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.util.URIUtil;

public class WebUtil {

	public static String get(String uri) throws URISyntaxException, IOException {

		String encoded = URIUtil.encodeQuery(uri);

		URL url = new URI(encoded).toURL();

		URLConnection conn = url.openConnection();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

			String inputLine;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}

			return sb.toString();
		}

	}

}
