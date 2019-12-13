//The Animal class is abstract- that means that:
//	1)	We cannot instantiate it
//	2)	We can define abstract methods in it

public abstract class Animal {

	//So far, we only talked about public and private visibility
	//Public:	Everything can see and modify
	//Private:	Only this class can see and modify
	//However, subclasses of this class cannot access private members (i.e. methods and instance variables)
	//When we want a method or variable to only be accessible in this class or its children, we use protected
	protected final String name;

	//Declaring a variable as final means that it cannot be modified outside of the constructor
	//This prevents subclasses from modifying values they shouldn't be able to
	protected final int age;

	//Even though we cannot instantiate an abstract class, we must still define a constructor for it
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	//Declaring a method as final prevents any subclasses from Overriding it
	//Use final methods when the method is already as specific as necessary
	//For example: all Animals will eat the same way, so there's no reason to allow them to Override this method
	public final void eat() {
		System.out.printf("%s is eating!%n", name);
	}

	//Non-final methods in Java are "virtual" meaning that subclasses are allowed to Override them
	//Use these when one definition fits most subclasses, but a few specific cases require a special Override
	//In our example: most Animals can swim, so there's no reason to write the same method for all of those
	//Those that cannot (or will not) swim can Override the method on their own
	public void swim() {
		System.out.printf("%s is swimming!%n", name);
	}

	//Abstract methods can only appear in abstract classes
	//We do not provide a definition body (hence the semicolon; instead of curly braces{})
	//This creates a contract which requires all subclasses of this class to implement the method somehow (either on their own or via superclass)
	public abstract void speak();

	//All Animals must provide a definition for this method since it's abstract!
	public abstract boolean hasGills();

	//Standard getters
	//Final methods- no reason for subclasses to Override something so simple
	public final String getName() {
		return name;
	}

	public final int getAge() {
		return age;
	}

	//All Java classes extend the Object class
	//It doesn't have very much information, but the toString() method is important!
	//Any class can Override toString() because it is a virtual method inherited from Object
	//However, since we declare the method to be final here in the Animal class, this prevents any children of Animal from Overriding it any more
	@Override
	public final String toString() {
		return name;
	}
}
