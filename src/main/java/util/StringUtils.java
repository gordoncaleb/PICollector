package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

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
}
