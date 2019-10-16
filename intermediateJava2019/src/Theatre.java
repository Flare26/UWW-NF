
public class Theatre {
	public static int totalTicketsSold;
	public static double totalTicketIncome;

    private String name;
    //We can use other objects inside of objects to form more complicated objects
    private Movie currentMovie;
    //These instance variables are those which are unique to a Theatre and would not make sense to be in Movie
    private double admissionPrice;
    private int seatCapacity;
    private int ticketsSold;
    private double ticketIncome;

    
    public Theatre(String name, Movie currentMovie, double admissionPrice, int seatCapacity) {
	this.name = name;
	this.currentMovie = currentMovie;
	this.admissionPrice = admissionPrice;
	this.seatCapacity = seatCapacity;
	ticketsSold = 0;
    }

    public void sellTicket() {
    	ticketsSold++;
    	ticketIncome += admissionPrice;
    	
    	totalTicketsSold++;
    	totalTicketIncome += admissionPrice;
    }
    
    //OVERLOADED methods. The constructor sig can be different. Must be same return type however!
    //USE THIS FOR JSONOBJECTS
    public boolean canSellTicket() {
	if (ticketsSold < seatCapacity)
	    return true;
	else
	    return false;
    }
    
    public boolean canSellTicket( double money ) {
    	if ( money < admissionPrice )
    		return true;
    	return canSellTicket(); //we can do this because return type is THE SAME
    }

    public void print() {
	System.out.println(name + " is showing " + currentMovie.getTitle() + " by " + currentMovie.getDirector());
    }

    public Movie getCurrentMovie() {
	return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
	this.currentMovie = currentMovie;
    }

    public double getAdmissionPrice() {
	return admissionPrice;
    }

    public void setAdmissionPrice(double admissionPrice) {
	if (admissionPrice >= 0d)
	    this.admissionPrice = admissionPrice;
    }

    public int getSeatCapacity() {
	return seatCapacity;
    }

    public int getTicketsSold() {
	return ticketsSold;
    }

    public String getName() {
	return name;
    }
    
    public double getTicketIncome() {
    	return this.ticketIncome;
    }
    
    //Override tostring to override memory reference
    //USE THIS FOR JSON OBJECTS TOO
    public String toString( ) { 
    	return String.format("%s (%d) by %s (%.1f / 10.0)", title, year, director, rating );
    	
    }
}
