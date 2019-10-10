import java.util.Arrays;

public class Lab6InsertionSort {
	private static int [] NUMS = { 5,2,7,23,71,23,66,34,72,64,98,42,99,1,224 };
	public static void main(String[] args) {
		System.out.println(Arrays.toString(NUMS));
		insertionSort();
		System.out.println(Arrays.toString(NUMS));

	}
	//goes through once and iterates i to 1, then begins the sort
	private static void insertionSort() {
		for ( int i = 0 ; i < NUMS.length ; i++) {
			int j = i;
			//include a range within this condition of j - 1
			while ( j > 0 && NUMS [ j - 1 ] > NUMS [ j ] ) { 
				swap ( j , j - 1 );
				System.out.println(Arrays.toString( NUMS ) );
				j--;
			}
		}
		
	}
	
	private static void swap( int index1 , int index2 ) { 
		int temp = NUMS [ index1 ];
		NUMS [ index1 ] = NUMS [ index2 ];
		NUMS [ index2 ] = temp;
	}
}
