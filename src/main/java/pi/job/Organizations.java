package pi.job;

import collector.Collector;
import collector.ECUCollector;

public class Organizations {

	public final static String APPALACHIAN_STATE = "APPALACHIAN STATE UNIVERSITY";
	public final static String EAST_CAROLINA = "EAST CAROLINA UNIVERSITY";
	public final static String FAYETTEVILLE_STATE = "FAYETTEVILLE STATE UNIVERSITY";
	public final static String NC_SCIENCE_MATH = "NC SCHOOL OF SCIENCE AND MATH";
	public final static String NC_AT_STATE = "NORTH CAROLINA A&T STATE UNIVERSITY";
	public final static String NC_CENTRAL = "NORTH CAROLINA CENTRAL UNIVERSITY";
	public final static String NC_STATE = "NORTH CAROLINA STATE UNIVERSITY";
	public final static String UNC_CHAPEL_HILL = "UNC-CHAPEL HILL";
	public final static String UNC_CHARLOTTE = "UNC-CHARLOTTE";
	public final static String UNC_WILMINGTON = "UNC-WILMINGTON";

	public static Collector getCollector(String organization) {

		switch (organization) {
		case EAST_CAROLINA:
			return new ECUCollector();
		default:
			return null;
		}
	}
}
