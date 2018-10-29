package sis.studentinfo;

import java.util.*;

public abstract class Session implements Comparable<Session> {
	protected static int count;
	private String department;
	private String number;
	private List<Student> students = new ArrayList<Student>(); 
	private Date startDate;
	private int numberOfCredits;
	
	protected Session(String department, String number, Date startDate) {
		this.department = department;
		this.number = number;
		this.startDate = startDate;
	}
	
	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		if (compare == 0)
			compare = this.getNumber().compareTo(that.getNumber());
		return compare;
	}
	
	void setNumberOfCredits(int credits) {
		this.numberOfCredits = credits;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getNumber() {
		return number;
	}
	
	int getNumberOfStudents() {
		return students.size();
	}
	
	public void enroll(Student student) {
		student.addCredits(numberOfCredits);
		this.students.add(student);
	}
	
	Student get(int index) {
		return students.get(index);
	}
	
	protected Date getStartDate() {
		return startDate;
	}
	
	public List<Student> getAllStudents(){
		return students;
		 
	}
	
	abstract protected int getSessionLength();
	
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		int numberOfDays = getSessionLength() * 7 - 3;	//	weeks * days per week - 3 days
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);		
		return calendar.getTime();
	}
	

}
