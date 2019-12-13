package Lab19;
// Nathan Frazier Lab 19

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
// public Team(String name, String city, Province province, Country country, int yearFounded)

public final class Lab19 {

	private static void printUniqueProvinces() {
		//throw new UnsupportedOperationException();
		
		HashSet<Province> provinces = new HashSet<Province>();
		System.out.println("Reading from database...");
		for (Team t : NHL) {
			provinces.add(t.getProvince());
		}
		
		System.out.println("Printing provinces HashSet...");
		System.out.println(provinces.toString());
		
		//Instructions:
		//	Print out all of the Province/States represented in the NHL
		//	Do not print any duplicates!
	}

	private static void printNumberOfTeamsByCountry() {
		//throw new UnsupportedOperationException();
		ArrayList<Country> countries = new ArrayList<Country>();
		
		HashMap<Country, Integer> teamCount = new HashMap<Country, Integer>();
		
		// Hash Maps support .values() which returned an iterable array
		for ( Country c : Country.values()) {
			teamCount.put(c, 0); // initialize to 0
		}
		
		for (Team t : NHL ) {
			if (teamCount.containsKey(t.getCountry())) {
				teamCount.put(t.getCountry(), teamCount.get(t.getCountry())+1);
			}else {
				teamCount.put(t.getCountry(),1);
			}
		} 
		
		System.out.println("Printing countries / team count...");
		
		printMap("Country" , "Team Count" , teamCount);
		
		
		//Instructions:
		//	Print out the number of teams each country has in the NHL
		//	Do not print any duplicates and do not hard-code USA or CAN
	}

	private static void printTeamsSortedByName() {
		//throw new UnsupportedOperationException();
		
		ArrayList<Team> sortable = new ArrayList<Team>();
		
		// Manual copy
		for ( Team t : NHL ) {
			sortable.add((Team) t);
		}
		
		Collections.sort(sortable , new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {
				return t1.getName().compareTo(t2.getName());
			}
			
				
		}); // END anonymous comparator construction
		System.out.println("Printing team names in alphabetical order...");
		
		printCollection(sortable);
		
		
		//Instructions:
		//	Print out all of the teams in the NHL sorted by their team name (alphabetical)
		//	Remember not to modify the original Team class (i.e. don't implement Comparable)
	}

	private static void printTeamsSortedByProvince() {
		//throw new UnsupportedOperationException();
		
		ArrayList<Team> instance = new ArrayList<Team>(NHL);
		
		// use a custom comparator as an argument, use anonymous class to define this one time use function
		Collections.sort( instance, new Comparator<Team>() {
			  @Override
			  public int compare(Team t1, Team t2) {
			    return t1.getProvince().compareTo(t2.getProvince());
			  }
			});

		System.out.println("Printing team names sorted by province...");
		
		printCollection(instance);
		
		//Instructions:
		//	Print out all of the teams in the NHL sorted by their Province/State
		//	Remember not to modify the original Team class (i.e. don't implement Comparable)
	}

	private static void printTeamsSortedByYearThenName() {
		//throw new UnsupportedOperationException();

		ArrayList<Team> instance = new ArrayList<Team>(NHL);
		
		// use a custom comparator as an argument, use anonymous class to define this one time use function
		Collections.sort( instance, new Comparator<Team>() {
			  @Override
			  public int compare(Team t1, Team t2) {
			    
				  int val;
				  
				  // call method of integer class .compare() since .compareTo() cannot be invoked on an int itself
				  val = Integer.compare(t1.getYearFounded(), t2.getYearFounded());
				  // if years are the same, value will = 0, and name will then be compared
				  if ( val == 0 ) {
					  val = t1.getName().compareTo(t2.getName());
				  }
				  
				  return val;
				 
			  }
			  
			  
			}); // END Collections.sort()
		
		System.out.println("Printing team names sorted by year, then name...");
		printCollection(instance);
		
		
			
		//Instructions:
		//	Print out all of the teams in the NHL sorted FIRST by the year they were founded THEN by their name (alphabetical)
		//	Another way to think of it- sort by year but break any ties by using their team name
		//	Remember, this is a single sort operation. Don't sort twice!
	}

	private static void printTeamsFrom(Province province) {
		//throw new UnsupportedOperationException();

		ArrayList <Team> teams = new ArrayList <Team> ();
		
		for ( Team t : NHL ) {
			if ( t.getProvince() == province ) {
				teams.add(t);
			}
		}
		
		System.out.printf("Printing teams from province %s...\n" , province);
		
		printCollection(teams);
		
		
		
		//Instructions:
		//	Print out all of the teams in the NHL that represent the given Province/State
	}

	//============================ Don't modify anything below this line! ============================//
	private static final List<Team> NHL = new ArrayList<Team>();

	public static void main(String[] args) {
		initializeTeams();

		System.out.println("\nUnique Provinces and States:");
		printUniqueProvinces();

		System.out.println("\nNumber of Teams In Each Country:");
		printNumberOfTeamsByCountry();

		System.out.println("\nTeams (Sorted by Name):");
		printTeamsSortedByName();

		System.out.println("\nTeams (Sorted by Province or State):");
		printTeamsSortedByProvince();

		System.out.println("\nTeams (Sorted by Year then Name):");
		printTeamsSortedByYearThenName();

		System.out.println("\nTeams From California:");
		printTeamsFrom(Province.CA);

		System.out.println("\nTeams From New York:");
		printTeamsFrom(Province.NY);

		System.out.println("\nTeams From Illinois:");
		printTeamsFrom(Province.IL);

		System.out.println("\nTeams From Florida:");
		printTeamsFrom(Province.FL);
	}

	private static <E> void printCollection(Collection<E> collection) {
		String format = "\t%-50s%n";
		for (E entry : collection) {
			String e = entry.toString();
			if (e.length() > 47)
				e = e.substring(0, 47) + "...";

			System.out.printf(format, e);
		}
	}

	private static <K, V> void printMap(String keyHeader, String valueHeader, Map<K, V> map) {
		String format = "\t%-50s%20s%n";
		System.out.printf(format, keyHeader, valueHeader);
		for (K key : map.keySet()) {
			V value = map.get(key);

			String k = key.toString();
			if (k.length() > 47)
				k = k.substring(0, 47) + "...";

			String v = value.toString();
			if (v.length() > 47)
				v = v.substring(0, 47) + "...";

			System.out.printf(format, k, v);
		}
	}

	private static void initializeTeams() {
		//Atlantic Division
		NHL.add(new Team("Boston Bruins", "Boston", Province.MA, Country.USA, 1924));
		NHL.add(new Team("Buffalo Sabres", "Buffalo", Province.NY, Country.USA, 1970));
		NHL.add(new Team("Detroit Red Wings", "Detroit", Province.MI, Country.USA, 1926));
		NHL.add(new Team("Florida Panthers", "Sunrise", Province.FL, Country.USA, 1993));
		NHL.add(new Team("Montreal Canadiens", "Montreal", Province.QC, Country.CAN, 1909));
		NHL.add(new Team("Ottawa Senators", "Ottawa", Province.ON, Country.CAN, 1992));
		NHL.add(new Team("Tampa Bay Lightning", "Tampa", Province.FL, Country.USA, 1992));
		NHL.add(new Team("Toronto Maple Leafs", "Toronto", Province.ON, Country.CAN, 1917));
		//Metropolitan Division
		NHL.add(new Team("Carolina Hurricanes", "Raleigh", Province.NC, Country.USA, 1972));
		NHL.add(new Team("Columbus Blue Jackets", "Columbus", Province.OH, Country.USA, 2000));
		NHL.add(new Team("New Jersey Devils", "Newark", Province.NJ, Country.USA, 1974));
		NHL.add(new Team("New York Islanders", "New York City", Province.NY, Country.USA, 1972));
		NHL.add(new Team("New York Rangers", "New York City", Province.NY, Country.USA, 1926));
		NHL.add(new Team("Philadelphia Flyers", "Philadelphia", Province.PA, Country.USA, 1967));
		NHL.add(new Team("Pittsburgh Penguins", "Pittsburgh", Province.PA, Country.USA, 1967));
		NHL.add(new Team("Washington Capitals", "Washington D.C.", Province.DC, Country.USA, 1974));
		//Central Division
		NHL.add(new Team("Chicago Blackhawks", "Chicago", Province.IL, Country.USA, 1926));
		NHL.add(new Team("Colorado Avalanche", "Denver", Province.CO, Country.USA, 1972));
		NHL.add(new Team("Dallas Stars", "Dallas", Province.TX, Country.USA, 1967));
		NHL.add(new Team("Minnesota Wild", "Saint Paul", Province.MN, Country.USA, 2000));
		NHL.add(new Team("Nashville Predators", "Nashville", Province.TN, Country.USA, 1998));
		NHL.add(new Team("St. Louis Blues", "St. Louis", Province.MO, Country.USA, 1967));
		NHL.add(new Team("Winnipeg Jets", "Winnipeg", Province.MB, Country.CAN, 1999));
		//Pacific Division
		NHL.add(new Team("Anaheim Ducks", "Anaheim", Province.CA, Country.USA, 1993));
		NHL.add(new Team("Arizona Coyotes", "Glendale", Province.AZ, Country.USA, 1972));
		NHL.add(new Team("Calgary Flames", "Calgary", Province.AB, Country.CAN, 1967));
		NHL.add(new Team("Edmonton Oilers", "Edmonton", Province.AB, Country.CAN, 1972));
		NHL.add(new Team("Los Angeles Kings", "Los Angeles", Province.CA, Country.USA, 1967));
		NHL.add(new Team("San Jose Sharks", "San Jose", Province.CA, Country.USA, 1991));
		NHL.add(new Team("Vancouver Canucks", "Vancouver", Province.BC, Country.CAN, 1945));
		NHL.add(new Team("Vegas Golden Knights", "Paradise", Province.NV, Country.USA, 2017));
	}
}
