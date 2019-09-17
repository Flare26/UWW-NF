//Nathan Frazier Lab 2 Assignment
//This program simply gathers information
import java.util.InputMismatchException;
import java.util.Scanner;
public class Lab2Assignment {

	static Scanner scan = new Scanner(System.in);
	static String pcName;
	static double ghz;
	static double memorySize;
	static String memorySpeed;
	static boolean hasWireless;
	static String gpu;
	static double storageSize;
	
	public static void main(String[] args) {	
		//Done initializing static vars
		System.out.println("This program will ask for your computer specifications!");
		try {
			
			dispPrompt("storage size");
			storageSize = scan.nextDouble();
			
			dispPrompt("memory size");
			memorySize = scan.nextDouble();
			
			dispPrompt("clock speed");
			ghz = scan.nextDouble();
			//Advance scanner to fresh line as .nextFoo() only expects one data value at a time
			String clear_scanner = scan.nextLine();
			
			dispPrompt("GPU make");
			gpu = scan.nextLine();
			
			dispPrompt("PC name");
			pcName = scan.nextLine();
			
			} catch (InputMismatchException e) {
				inputError();
				System.exit(0);
			} finally {
				// %d does NOT repesent a double. Must use a float tag %f
				System.out.println("| FINALLY BLOCK | ");
				System.out.println("PC NAME : " + pcName);
				System.out.println("GPU MAKE : " + gpu);
				System.out.printf("PROCESSOR CLOCK : %s", (ghz+"GHZ"));
				System.out.println();
				System.out.printf("MEMORY CAPACITY : %f", memorySize);
				System.out.println();
				System.out.printf("STORAGE CAPACITY : %f", storageSize);
			}
	}
	
	public static void inputError()
	{
	System.out.println("Error, try again! You've entered invalid input.");
	System.out.println("Exiting...\n...did you think I was going to stick around and check for typos?\nNot getting paid enough for that :(");
	}
	
	public static void dispPrompt(String text)
	{
		System.out.printf("Please enter a value for %s:", text);
	}
}
