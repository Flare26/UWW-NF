import java.util.Scanner;

public class InputHandler {
	
	//True by default - controls whether or not input handler will accept yes or no
	private boolean ynAllowed = true;
	//False by default - controls whether or not ONLY to accept yes / no
	private boolean ynOnly = false;
	
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
	
	public Object getInput(int choices) {
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		boolean iIsValid = inputIsValid(input , choices);
		//inputIsValid will only return true if input is valid.... if not keep looping
		while (!iIsValid) {
			input = "";
			System.out.println("!!! Please enter a valid input : ");
			input = console.nextLine();
			iIsValid = inputIsValid (input, choices);
		}
		System.out.println("Valid input detected! :) >" + input);
		console.close();
		return input;
	}
	
	public boolean inputIsValid(String input , int choices) {
		boolean isval;
		if ( Character.getNumericValue( input.charAt(0) ) > 0 
				&& Character.getNumericValue( input.charAt(0) ) <= choices 
				&& !ynOnly) {
			isval = true;
			return true;
		} else {
			if ( ( input.toLowerCase().charAt(0) == 'y' ) || input.toLowerCase().charAt(0) == 'n' ) {
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
	
}
