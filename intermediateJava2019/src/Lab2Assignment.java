//Nathan Frazier Lab 2 Assignment (REsubmitted 9/22)
//This program simply gathers informatiom
import java.util.InputMismatchException;
import java.util.Scanner;
public class Lab2Assignment {

	static Scanner scan = new Scanner(System.in);
	static String pcName;
	static double ghz;
	static int memorySize;
	static char loadType;
	static boolean hasWireless;
	static String gpu;
	static short storageSize;
	
	public static void main(String[] args) {	
		//Done initializing static vars
		System.out.println("This program will ask for your computer specifications!");
		try {
			
			dispPrompt("storage size [short]");
			storageSize = scan.nextShort();
			
			dispPrompt("memory size [int]");
			memorySize = scan.nextInt();
			
			dispPrompt("clock speed [double]");
			ghz = scan.nextDouble();
			//Advance scanner to fresh line as .nextFoo() only expects one data value at a time
			String clear_scanner = scan.nextLine();
			
			dispPrompt("GPU make [str]");
			gpu = scan.nextLine();
			
			dispPrompt("PC name [str]");
			pcName = scan.nextLine();
			
			dispPrompt("PC Archetype [char] :\n[A-Gaming]\n[B-Workstation]\n[C-Hybrid]");
			String temp = scan.next();
			temp.toUpperCase();
			loadType = temp.charAt(0);
			//Example of using a char type in a boolean expression!!
			if (loadType < 'A' || loadType > 'C')
			{
				throw new InputMismatchException();
			}
			
			dispPrompt("Wireless Capability [boolean]");
			hasWireless = scan.nextBoolean();
			
			} catch (InputMismatchException e) {
				inputError();
				System.exit(0);
			} finally {
				// %d does NOT repesent a double. Must use a float tag %f
				System.out.println("| FINALLY BLOCK | ");
				System.out.println("PC NAME : " + pcName);
				System.out.printf("GPU MAKE : %s", gpu);
				System.out.println("\nARCHETYPE : " + loadType);
				System.out.println("WIRELESS = " + hasWireless);
				System.out.printf("PROCESSOR CLOCK : %s", (ghz+"GHZ"));
				System.out.println();
				System.out.printf("MEMORY CAPACITY : %d", memorySize);
				System.out.println();
				System.out.printf("STORAGE CAPACITY : %d", storageSize);
			}
	}
	
	public static void inputError()
	{
	System.out.println("Error, the program will now exit! You've entered invalid input.");
	}
	
	public static void dispPrompt(String text)
	{
		System.out.printf("Please enter a value for %s:", text);
	}
}
