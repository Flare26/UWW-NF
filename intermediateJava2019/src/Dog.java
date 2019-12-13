//Dog is a subclass of Animal and will inherit all of its features
//We won't declare it as final because we might want to provide more specific subclasses of Dog in the future (like Wolf, DomesticDog, Fox, etc.)

public class Dog extends Animal {

	//Provide a definition for the given constructor, but use super() to avoid re-writing code
	public Dog(String name, int age) {
		super(name, age);
	}

	//This concrete class is still missing a definition for speak()- so we need to provide one!
	@Override
	public void speak() {
		System.out.println("Bark!");
	}

	//Provide a definition for the abstract method in Animal
	//Think about creating a superclass Mammal to define this method for both Dog and Cat!
	//No subclass of Dog will ever have gills, so make this definition final
	@Override
	public final boolean hasGills() {
		return false;
	}

	//Define a method that's unique to Dogs
	//Cats, Fish, etc. will not have this method since they are not a subclass of Dog
	//Leave it is a virtual method since future subclasses might want to Override it
	//For example: a Wolf might not play fetch, but most other types of Dog would
	public void playFetch() {
		System.out.printf("Played fetch with %s!%n", name);
	}
}
