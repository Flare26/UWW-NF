//Nathan Frazier Lab 5 Assignment
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
//CSV Legend :
//Transaction_date,Product,Price,Payment_Type,Name,City,State,Country,Account_Created,Last_Login,Latitude,Longitude
import java.util.Date;


public class Lab5Assignment {
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
	private static int count = -2; //default value
	private static final String PATH = "SalesJan2009.csv";
	private static Object [] DATE_TIME; //index 0
	private static long [] PRICE; //index 2
	private static String [] PAY_TYPE; //index 3
	public static void main(String[] args) {
		try {
			count = getLineCount();
			System.out.println("Main recieved : " + count);
			
			setArrays(count);
			
			readFile();
			
			System.out.println("Confirming DATE_TIME array contents...");
			for (int i = 0 ; i < DATE_TIME.length ; i ++ )
			{
				if ( DATE_TIME[i] == null ) {
					System.err.println("ERROR OCCURED WHILE PARSING FROM CSV TO ARRAY!!!");
					System.exit(0);
				}
				i++;
			}
			
			System.out.println( "NO NULLs found in array! Testing parallel arrays...\n");
			// NOTE DATE_TIME is an OBJ array
			Date timestamp = (Date) DATE_TIME[666];
			String payType = PAY_TYPE[666];
			double finalPrice = PRICE[666];
			
			System.out.printf( "\nTimestamp : %s\nPaymentType : %s\nTotal Cost : $ %f" , timestamp, payType, finalPrice );
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("An error occured in MAIN!");
			e.printStackTrace();
		}

	}

	//Instead of using an ArrayList<>, attempt to take the long route and hard code the value....
	//Creating these methods assuming we're taking in variable amounts of data
	public static int getLineCount() throws Exception {
		int count = 0;
		boolean sentinel = true;
		String line;
		BufferedReader reader = new BufferedReader ( new FileReader ( PATH ));
		
		while ( sentinel ) {
			line = reader.readLine();
			if ( line != null ) {
				count++;
				System.out.printf("%d line(s) found!\n", count);
			} else {
				System.out.println("NULL detected!\nReturning count : " + count);
				sentinel = false;
			}
		}
		reader.close();
		return count-1; //Exclude first line as it is just a legend
	}
	
	public static void setArrays(int count) {
		
		System.out.println("Initializing arrys...");
		DATE_TIME = new Object [count];
		PRICE = new long [count];
		PAY_TYPE = new String [count];
		System.out.println("...finished!");
		
	}
	
	public static void readFile() throws Exception {
		System.out.println("Beginning file read... " + PATH);
		String line;
		BufferedReader reader = new BufferedReader( new FileReader ( PATH ) );
		reader.readLine(); //Skip legend line, otherwise it'll try to parse a word as a date and break...

		for (int i = 0 ; i < count ; i++ )
		{
			System.out.println("beginning enhanced for...");
			//Create buffered reader for large amounts of data
			
			boolean sentinel = true;
			
			while (sentinel) {
				line = reader.readLine();
				//System.out.println( line );
				if ( line != null ) {
					//System.out.println( line );
					String [] current_line = line.split(","); //.split() creates a string []
					DATE_TIME[i] = formatter.parse( current_line [ 0 ] ); //this is why we skipped earlier
					PRICE[i] = Long.parseLong( current_line[2] );
					line = null; //probably unnecessary
					i++;
					System.out.println(i + " parse operations completed...");
				} else {
					System.out.println("NULL detected during data read!");
					sentinel = false;
					reader.close();
				}
		} //end while
			System.out.println("Sentinel break detected!");
		}//end for
	}//end readFile()
}
