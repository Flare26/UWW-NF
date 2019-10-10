// SCOPE AND ECT
public class Lab8inclass {

	public static void main(String[] args) {
		Movie myMovie = new Movie( "Inception" , "Christopher Nolan" , 2010, 9.8 );
		
		System.out.println(myMovie.title);
		System.out.println(myMovie.director);
		System.out.println(myMovie.year);
		System.out.println(myMovie.rating);
		System.out.println(myMovie.toString());
		System.out.println("Was movie released before 2000 : " + myMovie.wasReleasedBefore(2000));
	}

}

//CLASS CAN BE DEFINED WITHIN A PUBLIC OR PRIVATE CLASS
class Movie {
	
	//Movie constructor
	Movie( String title , String director , int year , double rating ) {
		this.title = title;
		this.director = director;
		this.year = year;
		this.rating = rating;
	}
	
	//Movie instance variables
	String title;
	String director;
	int year;
	double rating;
	
	public boolean wasReleasedBefore( int otherYear ) {
		
		if ( year < otherYear)
			return true;
		else
			return false;
	}
	
	public String toString() {
		System.out.printf("%s (%d) by %s (%f/10)\n" , title , year , director , rating);
		return title+director+year+rating;
	}
}
