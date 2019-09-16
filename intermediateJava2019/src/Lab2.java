
//We need to import Scanner because it's not part of the base java library
//Alternatively, we can import everything in the java.util library by using
//import java.util.*;
import java.util.Scanner;
//The goal of this Lab is to simply prompt the user for information about a movie and then nicely print it back to them for display
//This should be a review of your Introduction to Java course
public class Lab2{
	public static Scanner scan = new Scanner(System.in);
    //Our main method is what the program will run
    //Command-line arguments can be passed through the 'args' array
    public static void main( String[] args ){
        //The \f escape character will clear the console
        System.out.println( "\f" );
        //It's always good practice to start your program by printing out what it is
        System.out.println( "Lab 1/2" );

        //Scanner is an object which must be instantiated
        //Don't worry about the specifics for now- we'll learn more about this later
        //You can call the Scanner anything you want, I just called mine 'input'
        //Scanner scan = new Scanner( System.in );

        //We don't use println so that the user input will appear next to the text
        //System.out.print( "Please input a movie title: " );
        //The .nextLine() method of the Scanner class returns a String, so we must store the output in a String variable
        String title = promptUser("a movie title");
        //Years are commonly represented as an int because they don't have a decimal part
        //System.out.print( "Please input the release year: " );
        //Representing a year as a String would be bad because we can't treat it as a number when it's stored in a String
        //This would prevent us from performing mathematical functions on it (like comparisons, addition, etc.)
        //Therefore- we need to Parse it from a String into a usable data type (like integer)
        //Even though we're expecting an integer, we still ask the user for a String so that we can parse the year later
        String yearStr = promptUser("the release year");
        //The Integer.parseInt() method expects a String argument which it will attempt to convert into an int
        int year = Integer.parseInt( yearStr );

        //The director name is like the movie title- it's already a String so we don't need to modify it
        //System.out.print( "Please input the director: " );
        String director = promptUser("the director");

        //Doubles support decimal math, so they're good for our rating system
        //System.out.print( "Please input a rating for this movie: " );
        String ratingStr = promptUser("a rating");
        //Just like Integer.parseInteger, we can parse a double using Double.parseDouble()
        double rating = Double.parseDouble( ratingStr );

        //True/False values are stored as a boolean
        System.out.print( "Please input whether the movie is 3D: " );
        String is3DStr = promptUser("whether the movie is 3D");
        //Boolean has similar parse support to Integer and Double
        boolean is3D = Boolean.parseBoolean( is3DStr );

        //The combination of String literals (the text in quotes) and variables is a form of String concatenation
        //This is typically slow since a new String object is formed each time we add something to it
        //However, it is perfectly valid unless otherwise stated. Use whichever methodology makes sense to you
       	//System.out.println( "\nYou entered '" + title + "' by " + director + " (" + year + ") with a rating of " + rating );
        
        //Printf is short for "Print Formatted" and is a form of String interpolation
        //This is typically faster than concatenation and the code is easier to read
        //Each Converter (kind of like a placeholder) will be filled with an argument from the method call and formatted accordingly
        //For example- %s means a String, %d means a decimal number, and %f means a floating point number
        //See the Java Docs for all Converter values and meanings
        //We end with a \n escape character which means "new line"
        System.out.printf( "You entered '%s' by %s (%d) with a rating of %f\n" , title , director , year , rating );
    }
    
    private static void printTitle(String title) {
    	System.out.println(title);
    }
    
    //Scope refers to a block and what exists in it, including only what get passed to it.
    private static String promptUser(String prompt) {
    	//using %s will replace the string with what is stored in the prompt passed to it
    	System.out.printf("Please input %s: " , prompt);
    	String input = scan.nextLine();
    	return input;
    }
}