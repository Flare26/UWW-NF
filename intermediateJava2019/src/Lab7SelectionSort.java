//Nathan Frazier
import java.util.Arrays;
import java.util.Random;

public class Lab7SelectionSort {
	
	private static int[] NUMS;
	private static char[] CHARS;

	private static void randomizeArray( int length ) { 
		NUMS = new int[ length ];
		CHARS = new char[ length ];
		Random rand = new Random();
		for ( int i = 0 ; i < NUMS.length; i++ )
		{
			NUMS [ i ] = rand.nextInt( 15 );
			CHARS [ i ] = (char) ( 'A' + rand.nextInt( 'Z' - 'A' + 1 ) ); //Chars are just ints
		}
	}
	
	private static void selectionSort(  ) {
		//everything below i is sorted
		for ( int i = 0 ; i < NUMS.length; i++ ) {
			int minIndex = i ;
			
			for ( int j = i + 1 ; j < NUMS.length ; j++) {
				
				if ( NUMS[ minIndex ] > NUMS[j] ) {
					minIndex = j;
				}
				if ( i != minIndex )
					swap( minIndex , i );
			}
		}
		
	}
	
	private static void swap(int index1, int index2) {
		var temp = NUMS [ index1 ];
		NUMS[ index1 ] = NUMS [ index2 ];
		NUMS[ index2 ] = temp;
		
		var tempch = CHARS[ index1 ];
		CHARS[ index1 ] = CHARS [ index2 ];
		CHARS[ index2 ] = tempch;
	}
	
	public static void main ( String [] args ) {
		randomizeArray(10);
		System.out.println(Arrays.toString(NUMS));
		System.out.println(Arrays.toString(CHARS));
		selectionSort();
		System.out.println(Arrays.toString(NUMS));
		System.out.println(Arrays.toString(CHARS));
	}
	
}
