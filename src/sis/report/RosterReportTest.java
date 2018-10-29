package sis.report;

import junit.framework.TestCase;
import sis.studentinfo.*;
import static util.StringUtil.NEWLINE;

public class RosterReportTest extends TestCase{
	public void testRoseterReport() {
		CourseSession session = CourseSession.create("ENGL", "101", DateUtil.createDate(2003, 1, 6));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));
		
		String rosterReport = new RosterReport(session).getReport();
//		System.out.println(rosterReport);
		assertEquals(RosterReport.ROSTER_REPORT_HEADER + "A" + NEWLINE +
				"B" + NEWLINE + RosterReport.ROSTER_REPORT_FOOTER + "2" + NEWLINE, rosterReport);
	}

}
