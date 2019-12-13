
public class Lab12 {

	public static void main(String[] args) {
		//Instantiate some of our concrete classes
		Housecat oliver = new Housecat("Oliver", 16);
		Lion king = new Lion("King", 10);

		//Next time: Casting, Upcasting, Downcasting, and Polymorphism!
		
		Animal rover = new Dog( "Rover" , 2 );
		Cat mittens = new Housecat("Mittens" , 1);
		Animal fish = new Fish( 3 );
		
		Animal [] zoo = new Animal [] { oliver, king, rover, mittens, fish } ;
		
		//Downcast
		
		Dog dog = (Dog) rover;
		dog.playFetch();
		
		try {
		Lion lion = (Lion) mittens;
		} catch  ( ClassCastException e ) {
			
		}
		
		if ( rover instanceof Dog ) {
			Dog d = (Dog) rover;
			d.playFetch();
		} else {
			System.out.printf("%s is not a Dog\n", rover);
		}
		
		
		for ( Animal animal : zoo ) {
			if (animal instanceof Cat)
				((Cat) animal).walkOnKeyboard();
			else
				System.out.printf("%s is not a Cat ; cannot walk on keyboard!\n" , animal);
		}
	}
}
