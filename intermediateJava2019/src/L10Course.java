//Nathan Frazier Lab 10 Course Object
public class L10Course {
	/*
    Name [String]
    Subject (CS, COMM, MATH, etc.) [String]
    Number (101, 220, etc.) [int]
    Section (1,2,etc.) [int]
    Instructor [Instructor]
    Enrolled Students (array) [Student[]]
	*/
	
	private String coursename;
	private String subject;
	private int courseNUM;
	private int section;
	private L10Instructor instructor;
	private L10Student [] classList;
	
	public L10Course (String cname, String subject, int coursenumber, int section, L10Instructor instructor, L10Student [] classList) {
		coursename = cname;
		this.subject = subject;
		courseNUM = coursenumber;
		this.section = section;
		this.instructor = instructor;
		
		//Initialize class list array with correct number of students
		this.classList = new L10Student [classList.length];
		//Copy array through constructor into the class
		for ( int i = 0 ; i < classList.length ; i ++ ) {
			this.classList[i] = classList[i];
		}
		
	}
	//END CONSTRUCTOR
	
	public String getCourseName() {
		return coursename;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public int getCourseNumber() {
		return courseNUM;
	}
	
	public int getCourseSection() {
		return section;
	}
	
	public L10Instructor getInstructorOBJ() {
		return instructor;
	}
	
	public L10Student [] getClassList () { 
		return classList;
	}
	
	public String toString( ) {
		return String.format("%s (%s %d-%d) w/ Prof. %s", coursename, subject, courseNUM, section, instructor.getName());
	}
}
