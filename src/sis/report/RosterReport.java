package sis.report;

import sis.studentinfo.*;
import static util.StringUtil.NEWLINE;
import util.StringUtil;

public class RosterReport {
	static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + "----" + NEWLINE;
	static final String ROSTER_REPORT_FOOTER = "# of students = ";
	private CourseSession session;
	
	RosterReport(CourseSession session){
		this.session = session;
	}
	
	public String getReport() {
		StringBuilder buffer = new StringBuilder();
		writeHeader(buffer);
		writeBody(buffer);
		writeFooter(buffer);
		return buffer.toString();
	}
	
	void writeHeader(StringBuilder buffer) {
		buffer.append(ROSTER_REPORT_HEADER);
	}
	
	void writeBody(StringBuilder buffer) {
		for(Student student: session.getAllStudents()) {
			buffer.append(StringUtil.appendNewLine(student.getName()));
		}
	}
	
	void writeFooter(StringBuilder buffer) {
		buffer.append(StringUtil.appendNewLine(ROSTER_REPORT_FOOTER + session.getAllStudents().size()));
	}
}
