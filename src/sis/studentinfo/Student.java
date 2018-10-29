package sis.studentinfo;

import java.util.*;

public class Student {
	public enum Grade {
		A(4),
		B(3),
		C(2),
		D(1),
		F(0);
	private int points;
	Grade(int points){
		this.points = points;
	}
	
	int getPoints() {
		return points;
	}
	}
	private List<Grade> grades = new ArrayList<Grade>();
	private String name;
	private int credits;
	static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	static final String IN_STATE ="CO";
	private String state = "";
	private GradingStrategy gradingStrategy = new RegularGradingStrategy();
	
	public Student(String name) {
		this.name = name;
		credits = 0;
		
	}
//	Getters
	public String getName() {
		return name;
	}
	int getCredits() {
		return credits;
	}
	boolean isFullTime() {
		return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
	}
	boolean isInState() {
		return state.equals(Student.IN_STATE);
	}
	double getGpa() {
		if (grades.isEmpty())
			return 0.0;
		double total = 0.0;
		for (Grade grade: grades) {
			total += gradingStrategy.getGradePointsFor(grade);
		}
		return total / grades.size();
	}
	
//	Modifiers
	void addCredits(int credits) {
		this.credits += credits;
	}
	void setState(String state) {
		this.state = state;
	}
	
	void addGrade(Grade grade) {
		grades.add(grade);
	}
	void setGradingStrategy(GradingStrategy gradingStrategy) {
		this.gradingStrategy = gradingStrategy;
	}
}
