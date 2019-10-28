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
		//Default constructor will default to accept yes / no answers AND numbers
	}
	
	public void setYNAllowed(boolean bool) {
		ynAllowed = bool;
	}
	
	public void setYNOnly(boolean bool) {
		ynOnly = bool;
		//If being set to a yes / no question, allow yes / no answers if not already
		if (ynAllowed == false && ynOnly == true)
			ynAllowed = true;
	}
	
	public char getInput(int choices) {
		boolean iIsValid;
		
		if ( ynOnly == true )
			System.out.printf("(y/n) : ");
		String input = console.nextLine();
		//make sure it is always lowercase
		input.toLowerCase();
		input.trim();
		//First things first check if they're quitting
		if ( input.equalsIgnoreCase("quit") )
			close();
		
		iIsValid = inputIsValid(input , choices);
		//inputIsValid will only return true if input is valid.... if not keep looping
		while ( ! iIsValid ) {
			input = "";
			System.out.println("!!! Please enter a valid input : ");
			input = console.nextLine();
			input.toLowerCase();
			//Check for quit on retry
			if ( input.equalsIgnoreCase("quit") )
				close();
			iIsValid = inputIsValid (input, choices);
		}
		System.out.printf("iIsValid : %s\t---> return %s\n", iIsValid, input.charAt(0));
		return input.charAt(0);
	}
	
	private boolean inputIsValid(String input , int choices) {
		//Takes into consideration the class booleans ynOnly & ynAllowed
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
		//Added a fun personalized goodbye message
		// This works because if Midterm.player1 is initialized, it MUST have a name.
		if ( ( Midterm.player1 instanceof Player ) == false ) {
			System.out.println("\tGoodbye...");
			console.close();
			System.exit(0);
		}
				
		else if ( Midterm.player1 instanceof Player) {
			System.out.println("\tGoodbye. I hope you live another day, " + Midterm.player1.getName() + "...");
			console.close();
			System.exit(0);
		}
		return;
	}
 	
}
