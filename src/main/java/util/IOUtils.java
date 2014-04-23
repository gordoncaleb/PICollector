package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils {

	public static URL getResourceAsURL(String... path) throws Exception {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < path.length; i++) {
			sb.append(path[i]);
			if (i < (path.length - 1)) {
				sb.append(File.separator);
			}
		}

		System.out.println("Path:" + sb.toString());

		return IOUtils.class.getResource(sb.toString());
	}

	public static String getResource(String... path) throws Exception {

		URL url = getResourceAsURL(path);

		Path resPath = java.nio.file.Paths.get(url.toURI());
		String file = new String(Files.readAllBytes(resPath), StandardCharsets.UTF_8);

		return file;
	}

	public static InputStream getResourceAsStream(String... path) throws Exception {
		URL url = getResourceAsURL(path);
		return url.openStream();
	}

	public static BufferedReader getResourceAsBufferedReader(String... path) throws Exception {
		URL url = getResourceAsURL(path);
		return new BufferedReader(new InputStreamReader(url.openStream()));
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
}
