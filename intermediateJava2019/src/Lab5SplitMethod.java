//Nathan Frazier Lab4 in class
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Lab5SplitMethod {

	private static final String PATH = "files/Rolling-Stone-Top-500-Albums.csv";
	private static final int NUMBER_OF_ALBUMS = 500;
	private static final int [] YEARS = new int [ NUMBER_OF_ALBUMS ];
	private static final int [] ARTISTS = new int [ NUMBER_OF_ALBUMS ];
	private static final int [] GENRE = new int [ NUMBER_OF_ALBUMS ];
	public static void main(String[] args) throws Exception {
		
		//Buffered reader is a GREAT alternative for scanner, as it reads values it passes other values out of memory
		
		FileReader fileReader = new FileReader( PATH );
		BufferedReader reader = new BufferedReader( fileReader );
		File file = new File( PATH );
		if ( ! file.exists()) {
			System.out.println("File does not exist!");
			System.exit(0);
		} else {
			System.out.println("File exists!");
			Scanner scan = null;
			try {
				 scan = new Scanner ( file );
			} catch (Exception e) {
				System.out.println("There was a problem creating scanner object from file " + PATH);
				return;
			}
			System.out.println("Executing scan...");
			//Skip header
			//scan.nextLine();
			reader.readLine();
			String str = scan.nextLine();
			System.out.println(str);
			//Scanner crashes while storing large amounts of chars and data
			for (int i = 0; i < NUMBER_OF_ALBUMS; i++)
			{
				String line = reader.readLine();
				
				if ( line != null ) {
					System.out.println(line);
				}
				else {
					System.out.println("EOF Reached");
				}
			}
		}
		
		
	}

}
