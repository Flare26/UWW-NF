//Nathan Frazier Lab 10 Student Class
public class L10Student {

	public static int studentcount = 0;
	
	private String name;
	private int ID;
	private double GPA;
	private String stuYear;
	private String MAJOR = "Undeclared";
	private String MINOR = "Undeclared";
	
	//Use multiple constructors with different parameter signatures!!!! This can be very useful for optional parameters
	
	public L10Student (String name, int ID, double GPA, String stuYear, String major, String minor) {
		this.name = name;
		this.ID = ID;
		this.GPA = setGPA(GPA);
		this.stuYear = stuYear;
		this.MAJOR  = major;
		this.MINOR = minor;
		studentcount++;
	}
	
	
	public L10Student (String name, int ID, double GPA, String stuYear) {
		this.name = name;
		this.ID = ID;
		this.GPA = setGPA(GPA);
		this.stuYear = stuYear;
		studentcount++;
	}
	
	public double setGPA(double newGPA) {
		if (newGPA > 0 && newGPA <= 4.0) {
			return newGPA;
		} else {
			System.err.println("WARNING : GPA IS INVALID FOR "+name+".\n\tSET TO DEFAULT 0.0");
			return 0.0;
		}
	}
			
	public String getName() { 
		return name;
	}
	public int getID( ) {
		return ID;
	}
	
	public String getMAJ() {
		return MAJOR;
	}
	
	public String getMINR() {
		return MINOR;
	}
	
	public double getGPA() {
		return this.GPA;
	}
	
	public String toString() {
		//String.format() is static and returns a string value with desired format
		return String.format("%s (%d) %s Major / %s Minor (%s) GPA: %.2f", name, ID, MAJOR, MINOR, stuYear, GPA );
	}
}
