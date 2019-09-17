//Nathan Frazier Lab 2 Assignment
//This program simply gathers information
import java.util.Scanner;
public class Lab2Assignment {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
	
		String pcName = "null";
		double ghz = null;
		double memorySize;
		String memorySpeed;
		boolean hasWireless;
		String gpu;
		double storageSpace;
		//Done initializing static vars
		System.out.println("This program will ask for your computer specifications!");
		input("the amount of storage space");

	}

	//Python's input method converted to Java terms
	public static Object input(String prompt, Object type)
	{
		System.out.printf("Please enter %s:", prompt);
		
		try {
		Object response = scan.nextLine();
		
		} 
		return response;
	}
}
