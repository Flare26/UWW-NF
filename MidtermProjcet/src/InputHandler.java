//Nathan Frazier InputHandler
import java.util.Scanner;
/* setYNAllowed - will keep looping for input until number detected
 * setYNOnly - will keep looping until y / n
 * always returns char value of the input charAt(0) / the char at index 0
 */
public class InputHandler {
	
	//True by default - controls whether or not input handler will accept yes or no
	private boolean ynAllowed = true;
	//False by default - controls whether or not ONLY to accept yes / no
	private boolean ynOnly = false;
	//instead of local vars, making scanner an instance var
	private Scanner console = new Scanner(System.in);
	
	public InputHandler() {
	}
	
	public void setYNAllowed(boolean bool) {
		ynAllowed = bool;
	}
	
	public void setYNOnly(boolean bool) {
		ynOnly = bool;
		if (ynAllowed == false && ynOnly == true)
			ynAllowed = true;
	}
	
	public char getInput(int choices) {
		String input = console.nextLine();
		//make sure it is always lowercase
		input.toLowerCase();
		input.trim();
		//First things first check if they're quitting
		if ( input.equalsIgnoreCase("quit") )
			close();
		
		boolean iIsValid = inputIsValid(input , choices);
		//inputIsValid will only return true if input is valid.... if not keep looping
		while ( ! iIsValid ) {
			input = "";
			System.out.println("!!! Please enter a valid input : ");
			input = console.nextLine();
			input.toLowerCase();
			iIsValid = inputIsValid (input, choices);
		}
		System.out.println("Valid input detected! :) >" + input);
		return input.charAt(0);
	}
	
	public boolean inputIsValid(String input , int choices) {
		boolean isval;
		if ( Character.getNumericValue( input.charAt(0) ) > 0 
				&& Character.getNumericValue( input.charAt(0) ) <= choices 
				&& ! ynOnly ) {
			isval = true;
			return true;
			//True as long as : not negative, not over the amount of choices, ynOnly is false
		} else {
			if ( ( input.charAt(0) == 'y' ) || input.charAt(0) == 'n' ) {
				isval = false;
				if ( ynAllowed )
					isval = true;
				return isval;
			} else {
				isval = false;
				return isval;
			}
		}	
	}
	
	public String scannerName() {
	    String name = "DEFAULT";
	    name = console.nextLine();
	    name.toLowerCase();
	    name.trim();
	    if (name.equalsIgnoreCase("quit"))
	    	close();
	    return name;
	}
	
	private void close() {
		System.out.println("Goodbye...");
		console.close();
		System.exit(0);
		return;
	}
 	
}
