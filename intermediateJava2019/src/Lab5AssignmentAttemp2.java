//Nathan Frazier Lab 5 Assignment
//This program is way longer than it needed to be but thats what i get for using a third party csv off google

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

//Create buffered reader for large amounts of data

public class Lab5AssignmentAttemp2 {

	private static final String PATH = "SalesJan2009.csv";
	private static String[] TIMESTAMPS;
	private static String[] PAY_TYPE;
	private static String[] COUNTRY;
	private static long [] PRICES;
	private static String[] NAME;
	private static int LINE_COUNT;
	
	
	public static void init() {
		
		try {
			
		int line_count = getLineCount();
		LINE_COUNT = line_count;
		
		TIMESTAMPS = new String[line_count];
		COUNTRY = new String[line_count];
		PRICES = new long[line_count];
		PAY_TYPE = new String[line_count];
		NAME = new String[line_count];
	
		System.out.println("Arrays sucessfully initialized! LINE COUNT ["+line_count+"]\n");
		
		} catch (Exception e) {
			System.out.println("Error during initialization phase...");
			e.printStackTrace();
		}
	}
	
	//instead of using ArrayList<> pre-read file for efficient hard-coded values
	public static int getLineCount() throws Exception {
		int count = 0;
		boolean sentinel = true;
		String line;
		BufferedReader reader = new BufferedReader ( new FileReader ( PATH ));
		
		while ( sentinel ) {
			line = reader.readLine();
			if ( line != null ) {
				count++;
				
			} else {
				System.out.println("NULL detected!");
				System.out.printf("%d line(s) found!\n", count);
				sentinel = false;
			}
		}
		reader.close();
		return count; //Exclude first line as it is just a legend
	}
	
	public static void readFile() {
		int successcount = 0;
		String [] current_line;
		
		try {
		
		System.out.println("Beginning file read... " + PATH);
	
		BufferedReader reader = new BufferedReader( new FileReader ( PATH ) );
		
		reader.readLine(); //SKIP KNOWN HEADER LINE
		
		//System.out.println("FOR " + LINE_COUNT);
		for (int i = 0 ; i < LINE_COUNT - 1; i++ ) {
			String linedata = reader.readLine();
			current_line = linedata.split(","); //gets vals for current columns
			for (String s : current_line) {
				s.trim();
			}
			TIMESTAMPS[i] = current_line[0];
			COUNTRY[i] = current_line[7];
			PRICES[i] = Long.parseLong(current_line[2]);
			PAY_TYPE[i] = current_line[3];
			NAME[i] = current_line[4].trim();
			successcount++;
			
		}
		
		System.out.printf("Data build SUCCESS !!! %d lines completed (1 skipped)", successcount);
		reader.close();
		} catch (Exception e) {
			System.out.printf("Critical error in readFile() !!! %d lines completed.", successcount);
			e.printStackTrace();
		}
	}//end readFile()
	
public static void validateData() {
		
		Random rand = new Random();
		int i = rand.nextInt(LINE_COUNT+1);
		System.out.printf("\nPulling random parallel array index... [%d]", i );
		getParallels(i);
		
	}
	
public static void getParallels(int index) {
		String timestamp = TIMESTAMPS[index];
		String country = COUNTRY[index];
		Long price = PRICES[index];
		String payment_type = PAY_TYPE[index];
		String customername = NAME[index];
		
		System.out.printf("\n%s | Name: %s |Payment: $ %d --- %s | Country: %s", timestamp, customername, price, payment_type, country);
	}
	
	
	public static void query1( ) {
		//Searches for transactions that exceed 3400
		
		System.out.println("\n--- QUERY 1 ---");
		int count = 0;
		int threshold = 3400;
		int [] indexes = new int [PRICES.length];
		
		for ( int i = 0 ; i < PRICES.length ; i ++ ) {
			
			if ( Long.valueOf(PRICES[i]) >= threshold ) {
				indexes[count] = i ;
				count++;
			}
		}
		
		System.out.printf("Found %d transactions valued at / above $ 3,400", count);
		System.out.println("\n|INDEX|: " + Arrays.toString(indexes));
		System.out.println("\n\t...finished!");
	}
	
	public static void query2( ) {
		//find transactions from the 15th and after
		
		System.out.println("\n--- QUERY 2 ---");
		
		int count = 0;
		int[] indexes = new int[TIMESTAMPS.length];
		
		for ( int i = 0 ; i < TIMESTAMPS.length ; i ++ ) {

			String entry = TIMESTAMPS[i];
			
			if ( entry != null ) {
				String [] splits = entry.split("/");
				if ( Integer.valueOf(splits[1]) >= 15 ) {
					indexes[count] = i;
					count++;
				}
			} //end if & nested if
			
		}
		System.out.printf("Found %d out of %d transactions that took place on OR after Jan 15th", count, LINE_COUNT-1);
		System.out.println("\n|INDEX|: " + Arrays.toString(indexes));
		System.out.println("\n\t...finished!");
		
	}		
	
	public static void query3( ) {
		//Find names that start with A
		System.out.println("\n--- QUERY 3 ---");
		
		int count = 0;
		char key = 'A';
		int[] indexes = new int[NAME.length];
		
		for ( int i = 0 ; i < NAME.length-1 ; i ++ ) {
			String current = NAME[i].toUpperCase();
			if (current.charAt(0) == 'A') {
				indexes[count] = i;
				count++;
			}
				
		}
		System.out.printf("Found %d customer names that start with 'A' ", count);
		System.out.println("\n|INDEX|: " + Arrays.toString(indexes));
		System.out.println("\n\t...finished!");
		
	}	
	
	public static void query4( ) {
		//Find transactions from the United States
		System.out.println("\n--- QUERY 4 ---");
		int count = 0;
		int[] indexes = new int[COUNTRY.length];
		
		for ( int i = 0 ; i < COUNTRY.length - 1 ; i++ ) {
		
			String current = COUNTRY[i].toUpperCase();
			//System.out.println(current);
			if ( current.equals("UNITED STATES") ) {
				indexes[count] = i;
				count++;
			}
			
		}
		System.out.printf("Found %d transactions originating from United States ", count);
		System.out.println("\n|INDEX|: " + Arrays.toString(indexes));
		System.out.println("\n\t...finished!");
		
	}	
	
	public static void query5( ) {
		//Find transactions NOT using Visa
		System.out.println("\n--- QUERY 5 ---");
		int count = 0;
		String key = "VISA";
		int[] indexes = new int[PAY_TYPE.length];
				for ( int i = 0 ; i < PAY_TYPE.length - 1 ; i++ ) {
					String current = PAY_TYPE[i].toUpperCase();
					if ( ! current.equals(key) ) {
						indexes[count] = i;
						count++;
					}
				}
		System.out.printf("Found %d transactions NOT originating from VISA", count);
		System.out.println("\n|INDEX|: " + Arrays.toString(indexes));
		System.out.println("\n\t...finished!");
		
	}	
	
	
	//Transaction_date,Product,Price,Payment_Type,Name,City,State,Country
	public static void main(String[] args) {
		System.out.println("Initializing arrays...");
		init();
		readFile();
		validateData();
		query1();
		query2();
		query3();
		query4();
		query5();

	}

}
