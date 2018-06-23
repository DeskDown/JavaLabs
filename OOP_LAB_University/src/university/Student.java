package university;

import java.util.*;

class Student {
	
	private String Fname = new String();
	private String Sname = new String();
	private Integer ID;
	private List <Courses> listOfCourses = new ArrayList<>();
	
	public Student(String fname, String sname, int iD) {
		super();
		Fname = fname;
		Sname = sname;
		ID = iD;
	}

	public String getFname() {
		return Fname;
	}

	public String getSname() {
		return Sname;
	}

	public Integer getID() {
		return ID;
	}

	@Override
	public String toString() {
		return this.ID +" "+ this.Fname +" "+ this.Sname;
	}

	public List<Courses> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(Courses c) {
		this.listOfCourses.add(c);
	}
	
	
	
	
}
