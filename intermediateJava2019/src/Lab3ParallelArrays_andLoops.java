//Nathan Frazier Lab3
public class Lab3ParallelArrays_andLoops {

	public static void main(String[] args) {
		
		String [] titles = new String [5];
		titles[0] = "2001: A Space Odyssey";
		titles[1] = "Interstellar";
		titles[2] = "Contact";
		titles[3] = "Blade Runner 2049";
		titles[4] = "Moon";
		
		int [] releaseYears = new int [] {
				1968,
				2014,
				1997,
				2017,
				2009,
		};

		String [] directors = {
				"Stanley Kubrick",
				"Christopher Nolan",
				"Robert Zemeckis",
				"Denis Villeneueve",
				"Duncan Jones",
		};
		int index = 0;
		int index2 = 0;
		System.out.println("\n| WHILE LOOP |");
		while (index < titles.length) {
			String title = titles [ index ];
			int year = releaseYears [ index ];
			String director = directors[ index ];
			
			System.out.printf("\t%s (%d) by %s \n", title, year, director);
			index++;
		}
		
		//little for loop trick!
		System.out.println("\n| FOR LOOP |");
		for ( ; index2 < titles.length; index2++)
		{
			String title = titles [ index2 ];
			int year = releaseYears [ index2 ];
			String director = directors[ index2 ];
			System.out.printf("\t%s (%d) by %s \n", title, year, director);
		}
		
		System.out.println("\n| ENHANCED FOR | ");
		
		//NEVER USE VAR FOR CLASS VARIABLE
		//CANNOT USE VAR FOR RETURN TYPE
		//var is implied and the compiler has to trace the source using context it's not an actual data type
		for (var title : titles ) { 
			System.out.printf("\t%s%n", title);
		}
		
		}
	}


