package pi.job;

import collector.*;

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
		case APPALACHIAN_STATE:
			return new AppStateCollector();
		case EAST_CAROLINA:
			return new ECUCollector();
		case FAYETTEVILLE_STATE:
			return new FayettevilleStateCollector();
		case NC_SCIENCE_MATH:
			return new NcScienceMath();
		case NC_AT_STATE:
			return new NcATStateCollector();
		case NC_CENTRAL:
			return new NcCentralCollector();
		case NC_STATE:
			return new NcStateCollector();
		case UNC_CHAPEL_HILL:
			return new UncChapHillCollector();
		case UNC_CHARLOTTE:
			return new UncCharlotteCollector();
		case UNC_WILMINGTON:
			return new UncWilmingtonCollector();
		default:
			return null;
		}
	}
}
