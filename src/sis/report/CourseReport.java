package sis.report;

import java.util.*;
import sis.studentinfo.*;
import static util.StringUtil.NEWLINE;

public class CourseReport {
	private List<CourseSession> sessions = new ArrayList<CourseSession>();
	
	public void add(CourseSession session) {
		sessions.add(session);
	}
	
	public String text() {
		Collections.sort(sessions);
		StringBuilder builder = new StringBuilder();
		for (CourseSession session: sessions) 
			builder.append(session.getDepartment() + " " + session.getNumber() + NEWLINE);
		return builder.toString();
	}

}
