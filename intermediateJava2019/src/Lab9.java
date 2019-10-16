
public class Lab9 {

    public static void main(String[] args) {
	System.out.println("Lab 9");

	//We can instantiate a Movie using any of its constructors
	//This is an empty constructor, so all of its values will be set to the default
	Movie m1 = new Movie();
	//This is the full constructor, it will have custom values for every instance variable
	Movie m2 = new Movie("2001: A Space Odyssey", "Stanley Kubrick", 1968, 10.0);
	//This constructor only assigns the title, all other values are default
	Movie m3 = new Movie("Interstellar");

	//Print the movies so we can see how their values were initialized
	m1.print();
	m2.print();
	m3.print();

	System.out.println("Modifying values...");

	//Try to break our setter methods
	m3.setDirector("");
	m3.setTitle(null);
	m3.setRating(-3.4d);

	//Print m3 to see if our invalid arguments worked
	m3.print();

	//The Theatre class constructor wants a String for the name, a Movie, a ticket price, and a seating capacity
	Theatre theatre = new Theatre("Whitewater Theatre", m2, 7.50, 35);
	
	//Theatre also has a print() method
	theatre.print();
    }
}
