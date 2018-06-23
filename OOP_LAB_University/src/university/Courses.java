package university;

import java.util.*;

class Courses {
	private String title; 
	private String teacher;
	private Integer courseCode;
	private List <Student> listOfStudents = new ArrayList<>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Integer getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public Courses(String title, String teacher, int courseCode) {
		super();
		this.title = title;
		this.teacher = teacher;
		this.courseCode = courseCode;
	}
	@Override
	public String toString() {
		return this.courseCode +" "+ this.title +" "+ this.teacher;
	}
	public void addStudent(Student s) {
		this.listOfStudents.add(s);
	}
	public List <Student> getStudents(){
		
		return this.listOfStudents;
		
	}
}
