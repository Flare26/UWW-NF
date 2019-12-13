//Lion is a concrete (i.e. not abstract) subclass of Cat which is a subclass of Animal
//It will inherit all of the methods and members from Cat, which inherited all the methods and members from Animal, and so on...

public class Lion extends Cat {

	//Provide a definition for the given constructor, but use super() to avoid re-writing code
	public Lion(String name, int age) {
		super(name, age);
	}

	//Since this is a concrete class and none of our superclasses (Cat or Animal) provide a definition for the speak() method, we MUST do it here
	//Until a concrete class contains a definition for EVERY abstract method, Java will not compile your code!
	@Override
	public void speak() {
		System.out.println("Roar!");
	}

	//Similar to speak(), this class MUST define the walkOnKeyboard() method to fufill its contract with the Cat class
	@Override
	public void walkOnKeyboard() {
		System.out.printf("%s crushed the keyboard!%n", name);
	}
}
