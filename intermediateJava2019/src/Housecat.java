//By declaring Housecat as final, we prevent other classes from extending it
//Use this when a class is already as specific as necessary and more expansion would be meaningless

public final class Housecat extends Cat {

	//Provide a definition for the given constructor, but use super() to avoid re-writing code
	public Housecat(String name, int age) {
		super(name, age);
	}

	//This concrete class is still missing a definition for speak()- so we need to provide one!
	@Override
	public void speak() {
		System.out.println("Meow!");
	}

	//Same for walkOnKeyboard()!
	@Override
	public void walkOnKeyboard() {
		System.out.printf("%s walked on the keyboard!%n", name);
	}
}
