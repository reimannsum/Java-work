package sis.studentinfo;

import java.util.*;

/**
 * Provides a representation of a single-semester
 * session of a specific university course.
 * @author Burke
 *
 */

public class CourseSession extends Session{
	public static CourseSession create(String department, String number, Date startDate) {
		return new CourseSession(department, number, startDate);
	}
	
	protected CourseSession(String department, String number, Date startDate) {
		super(department, number, startDate);
		CourseSession.incrementCount();
	}
	
	
	static void incrementCount() {
		count++;
	}
	
	static void resetCount() {
		count = 0;
	}
	
	static int getCount() {
		return count;
	}
	
	protected int getSessionLength() {
		return 16;
	}
}
