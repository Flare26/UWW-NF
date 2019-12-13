//The Cat class provides a more specific subclass of Animal, but cannot itself be instantiated (due to being abstract)
//Multiple parent classes can help define our concrete subclasses as simply as possible since they inherit most of their info

public abstract class Cat extends Animal {

	//We need to define a constructor with the same signature (same argument types, not necessarily the same names)
	//However, we don't need to re-write any code...
	public Cat(String name, int age) {

		//A super() call will delegate this constructor call to the immediate superclass of this Object (in this case, Animal)
		//This saves us the time of having to write "this.name = name; this.age = age;" again
		//Think of how much time this saves for more complex objects with more than two constructor arguments!
		super(name, age);
	}

	//Cats (usually) don't like to swim, so the default implementation of the swim() method in Animal doesn't work for Cats
	//That's okay, since it's a virtual method we can Override it
	//If we wanted to, we could declare this Override method as final to prevent subclasses of Cat from Overriding it themselves
	//However, some types of Cat might be alright with water, so we leave this method as virtual (i.e. not final) to account for that
	@Override
	public void swim() {
		System.out.printf("%s refuses to get wet!%n", name);
	}

	//Abstract classes can provide concrete definitions of methods!
	//This saves us from having to Override this method in Housecat and Lion
	//Ideally, we could create another superclass of Cat called Mammal and include Dog as a subclass (simplifies our code even more!)
	//Notice that Housecat and Lion (concrete subclasses) have a definition for this method because it's defined here
	//This fufills the contract!
	@Override
	public final boolean hasGills() {
		return false;
	}

	//Any abstract class can define its own abstract methods
	//For our example, we say that only Cats are capable of walking on keyboards
	//However, there's a big difference between a Housecat walking on your keyboard compared to a Lion or Tiger
	//Therefore, we don't have enough information in this class to define the method body
	//In these situations, we delegate it to the subclasses to provide their own definition!
	public abstract void walkOnKeyboard();
}
