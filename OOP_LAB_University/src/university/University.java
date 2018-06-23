package university;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class University {

	private String name = new String();
	private String Rname = new String();
	private Map <Integer,Student> StudentList = new HashMap();
	private Map <Integer,Courses> CourseList = new HashMap();
	private Integer Cid = 10;	
	private Integer Sid = 10000;
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		//TODO: to be implemented
		super();
		this.name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return this.name;
	}
	
	/**
	 * Defines the rector for the university
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.Rname = first + " " + last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * @return
	 */
	public String getRector(){
		//TODO: to be implemented
		return Rname;
	}
	
	/**
	 * Enroll a student in the university
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		
		Student s = new Student(first, last, Sid);
		IncreaseSid();
		StudentList.put(s.getID(),s);
		return s.getID();
	}
	
	private void IncreaseSid() {
		// TODO Auto-generated method stub
		this.Sid ++;
	}

	/**
	 * Retrieves the information for a given student
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		return StudentList.get(id).toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		Courses C = new Courses(title, teacher, Cid);
		this.Cid ++;
		CourseList.put(C.getCourseCode(), C);
		return C.getCourseCode();
	}
	
	/**
	 * Retrieve the information for a given course
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		return CourseList.get(code).toString();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		this.StudentList.get(studentID).setListOfCourses(this.CourseList.get(courseCode));
		this.CourseList.get(courseCode).addStudent(this.StudentList.get(studentID));
	}
	
	/**
	 * Retrieve a list of attendees
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		return this.CourseList.get(courseCode).getStudents().stream()
				.map(Object::toString).collect(Collectors.joining("\n"));
		
	}

	/**
	 * Retrieves the study plan for a student
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		return this.StudentList.get(studentID).getListOfCourses().stream().
				map(Object::toString).collect(Collectors.joining("\n"));
		
	}
}
