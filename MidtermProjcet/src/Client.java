import java.util.Scanner;

//using an overridden tostring method
public class Client {

	public static void main(String[] args) {
		StartMenu menu1 = new StartMenu("TEST GAME 1 :D");
		menu1.selectMenuOption(3);
		System.out.println(menu1);
		
		StartMenu menu2 = new StartMenu("TEST GAME 2 uwu");
		menu1.selectMenuOption('t');
		System.out.println(menu2);
		
		Scanner scan = new Scanner(System.in);
		InputHandler ih = new InputHandler();
		System.out.println("Choose 1 , 2 , 3, or y/n");
		ih.setYNAllowed(false);
		Object input = ih.getInput(5);
		System.out.println("Sucessfully handled input!");
		
		
	}

}
