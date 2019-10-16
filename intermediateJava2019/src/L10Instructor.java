//Nathan Frazier Lab 10 Instructor Class
public class L10Instructor {

	private String name;
	private int ID;
	private String department;
	
	public L10Instructor (String name, int ID, String dept) {
		this.name = name;
		this.ID = ID;
		department = setDepartment(dept);
	}
	
	public String getName() { 
		return name;
	}
	
	public String setDepartment(String newdept) { 
		return newdept;
	}
	
	public String toString() {
		return String.format("Prof. %s (%d) Department of %s", name, ID, department);
	}
	
}
