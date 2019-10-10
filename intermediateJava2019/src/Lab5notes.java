import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Lab5notes{
    private static final int NUMBER_OF_ALBUMS = 500;
    private static final String PATH = "Rolling-Stone-Top-500-Albums.csv";

    //Declare parallel arrays to store the data from the dataset
    //Make sure the types match what's appropriate for the column!
    private static final int[] YEARS = new int[ NUMBER_OF_ALBUMS ];
    private static final String[] ALBUMS = new String[ NUMBER_OF_ALBUMS ];
    private static final String[] ARTISTS = new String[ NUMBER_OF_ALBUMS ];
    private static final String[] GENRES = new String[ NUMBER_OF_ALBUMS ];

    public static void main( String[] args ){
        System.out.println( "\f\nLab 5\n" );

        try{ 
            //This method throws an Exception so we enclose it in a try/catch block
            readFile();
            
            //Uncomment this line to see every entry in the database
            //printAllAlbums();

            //Uncomment these lines to see print queries
            /*
            printAlbumsBeforeYear( 2000 );
            printAlbumsAfterYear( 2000 );
             */

            //Get the number of albums in the database released by The Beatles
            int beatlesCount = getAlbumsReleasedBy( "The Beatles" );
            System.out.printf( "Albums released by The Beatles: %d%n" , beatlesCount );

            //Print the number of albums released in 1973
            System.out.printf( "Albums released in 1973: %d%n" , getAlbumsReleasedIn( 1973 ) );
        }
        catch( Exception e ){
        }
    }

    //A simple method which prints all Albums in the database in a nicely formatted way
    private static void printAllAlbums(){
        System.out.println( "All albums in database:" );
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ )
            System.out.printf( "\t%s (%d) by %s%n" , ALBUMS[i] , YEARS[i] , ARTISTS[i] );
    }

    //A simple query which only prints albums which were released before the given year (as a method parameter)
    private static void printAlbumsBeforeYear( int year ){
        System.out.printf( "Albums released before %d:%n" , year );
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ ){
            if( YEARS[i] <= year )
                System.out.printf( "\t%s (%d) by %s%n" , ALBUMS[i] , YEARS[i] , ARTISTS[i] );
        }
    }

    //A simple query which only prints albums which were released after the given year (as a method parameter)
    private static void printAlbumsAfterYear( int year ){
        System.out.printf( "Albums released after %d:%n" , year );
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ ){
            if( YEARS[i] >= year )
                System.out.printf( "\t%s (%d) by %s%n" , ALBUMS[i] , YEARS[i] , ARTISTS[i] );
        }
    }

    //A query which returns the number of times an artist appears in the database
    private static int getAlbumsReleasedBy( String artist ){
        //Initialize the count to 0
        int count = 0;
        //Iterate over all entires
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ ){
            //If the artist at this index in the array equals the artist we're looking for (via method parameter) then increment the count
            //We use .equalsIgnoreCase() since == should not be used on Strings (since they're Objects not primitives)
            if( ARTISTS[i].equalsIgnoreCase( artist ) )
                count++;
        }
        return count;
    }

    //A query which returns the number of albums released in a given year
    private static int getAlbumsReleasedIn( int year ){
        //Initialize the count to 0
        int count = 0;
        //Iterate over all entires
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ ){
            //If the year at this index in the array equals the year we're looking for (via method parameter) then increment the count
            //We use == not .equals() because integers are primitives not Objects
            if( YEARS[i] == year )
                count++;
        }
        return count;
    }

    //Reads in the file and parses the data into our parallel arrays
    private static void readFile() throws Exception{
        System.out.print( "Reading file ..." );

        //BufferedReader is preferred to Scanner since the dataset is large
        //The FileReader instantiation was done inline
        BufferedReader reader = new BufferedReader( new FileReader( PATH ) );   

        //Read the first line but don't store it- we skip it because it's just a header row (see the .csv file)
        reader.readLine();

        //Run a loop so that i equals every index from 0 to the number of albums
        for( int i = 0; i < NUMBER_OF_ALBUMS; i++ ){
            //Read the line from the reader
            String line = reader.readLine();

            //If the line is null (remember- String is an Object not a primitive so the default value is null)
            if( line == null ){
                //If we get here, then there were fewer lines in the file than what our constant value expected
                System.out.println( "Reached end of file while reading" );
                //Close the reader to prevent memory leaks!
                reader.close();
                //End the program
                return;
            }
            else{
                //The .split() method expects a String to use as a delimiter
                //The resulting array of Strings will contain every substring of 'line' which was separated by the delimiter (a comma)
                String[] columns = line.split( "," );
                //Column 0 contains the 'number' value, which is irrelevant to us for this program since the dataset is already sorted
                //Column 1 contains the release year of the album. We need to parse it into an int before we can put it in the array
                YEARS[i] = Integer.parseInt( columns[1] );
                //Album name is a String, so we don't need to parse it
                ALBUMS[i] = columns[2];
                //Artist name is a String- no parse required
                ARTISTS[i] = columns[3];
                //Same for genre- no parse because it's a String
                GENRES[i] = columns[4];
            }
        }

        //Close the reader to prevent memory leaks!
        reader.close();
        System.out.print( "Done!\n" );
    }
}