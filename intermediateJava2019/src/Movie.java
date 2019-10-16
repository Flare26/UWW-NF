
public class Movie {

    //Introduce constants as final static
    //Final means that they cannot be modified
    //Static means that only one copy is kept rather than keeping one copy per instance (like title, director, etc.)
    private static final int MIN_YEAR = 1880;
    private static final int MAX_YEAR = 2020;
    private static final double MIN_RATING = 0d;
    private static final double MAX_RATING = 10d;
    private static final String DEFAULT_TITLE = "Untitled";
    private static final String DEFAULT_DIRECTOR = "No Director";

    //Instance variables:
    private String title;
    private String director;
    private int year;
    private double rating;

    //This constructor doesn't expect any arguments and will fill in all instance variables using default values
    public Movie() {
	title = DEFAULT_TITLE;
	director = DEFAULT_DIRECTOR;
	year = MIN_YEAR;
	rating = MIN_RATING;
    }

    //This constructor expects only a single String to use as the title
    //All other values are set to their default
    public Movie(String title) {
	this.title = title;
	director = DEFAULT_DIRECTOR;
	year = MIN_YEAR;
	rating = MIN_RATING;
    }

    //This is a full constructor which initializes every instance variable
    public Movie(String title, String director, int year, double rating) {
	this.title = title;
	this.director = director;
	this.year = year;
	this.rating = rating;
    }

    public String getTitle() {
	return title;
    }

    //By using setters, we can control what kind of values can be passed in from the outside
    //For example- we don't allow the title to be set to a null or blank String
    public void setTitle(String title) {
	//The two-pipe operator || "OR" is different than the single-pipe operator |
	//Both will "OR" two values
	//However, || is a Short Circuit Operator
	//This means that if the first boolean expression is evaluated to true, it won't bother with the next expression
	//This is okay because for "x || y", any time x = true the statement will be true
	//We like this behaviour because it prevents a potential null reference exception below if "title" is null
	//Similar Short Circuit rules apply to AND (&& and &)
	if (title == null || title.isBlank())
	    this.title = DEFAULT_TITLE;
	else
	    this.title = title;
    }

    public String getDirector() {
	return director;
    }

    //Similar encapsulation protection as setTitle()
    public void setDirector(String director) {
	if (director == null || director.isBlank())
	    this.director = DEFAULT_DIRECTOR;
	else
	    this.director = director;
    }

    public int getYear() {
	return year;
    }

    //Protect year from being set to invalid numbers
    public void setYear(int year) {
	//We use else if because it will prevent the second "if" from being evaluated if the first "if" was true
	//An easy optimization!
	if (year < MIN_YEAR)
	    year = MIN_YEAR;
	else if (year > MAX_YEAR)
	    year = MAX_YEAR;

	//Note that we modify the ARGUMENT year, not the INSTANCE VARIABLE year
	//We could do this the other way around (see setRating())
	this.year = year;
    }

    public double getRating() {
	return rating;
    }

    //Protect rating from being set to invalid numbers
    public void setRating(double rating) {
	//Here, we first set the instance variable
	//Next, we constrain the instance variable leaving the argument 'rating' unchanged
	this.rating = rating;

	if (this.rating < MIN_RATING)
	    this.rating = MIN_RATING;
	else if (this.rating > MAX_RATING)
	    this.rating = MAX_RATING;
    }

    public void print() {
	//If you want to control the way that decimals (doubles and floats) are printed, give %f the number of values after the decimal like so:
	//For example: %.1f will print only one digit after the decimal
	//Similarly, %.2f would print two
	System.out.printf("%s (%d) by %s (%.1f / 10.0)%n", title, year, director, rating);
    }
}
