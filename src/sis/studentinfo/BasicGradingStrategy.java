package sis.studentinfo;

abstract public class BasicGradingStrategy implements GradingStrategy{
	public int getGradePointsFor(Student.Grade grade) {
		return grade.getPoints();
	}

}
