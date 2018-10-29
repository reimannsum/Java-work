package sis.report;

import junit.framework.TestSuite;

public class AllTests {
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(RosterReportTest.class);
		suite.addTestSuite(CourseReportTest.class);
		suite.addTestSuite(ReportCardTest.class);
		return suite;
	}
}
