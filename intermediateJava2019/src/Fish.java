//Fish is a subclass of Animal and will inherit all of its features
//We won't declare it as final because we might want to provide more specific subclasses of Fish in the future (like Goldfish, Pike, Bass, Shark, etc.)

public class Fish extends Animal {

	//Provide a definition for the given constructor, but use super() to avoid re-writing code
	public Fish(String name, int age) {
		super(name, age);
	}

	public Fish(int age) {
		super("Generic Fishy" , age);
	}
	//This concrete class is still missing a definition for speak()- so we need to provide one!
	//Yes, Fish aren't very talkative
	@Override
	public void speak() {
		System.out.println("Blub Blub!");
	}

	//All Fish have gills, so make this method implementation final
	@Override
	public final boolean hasGills() {
		return true;
	}

	//Even though the superclass definition of swim() is suitable, we can be more specific
	//All fish are swimming all the time, so it's silly to ask them to swim!
	//Define a better method definition specific to Fish
	//Also, we make it final since ALL Fish will use this definition- no exceptions!
	@Override
	public final void swim() {
		System.out.printf("%s is always swimming!", name);
	}
}
