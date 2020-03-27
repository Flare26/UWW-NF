public class Recursion {
//Nathan Frazier
	public static int sumEvenDigits(int n) { // complete this function
		if ( n <= 0)
			return 0;
		
		if ( n % 2 == 0 ) {
			return sumEvenDigits(n/10) + n%10;
		}
		 	// nothing gets added if odd
			return sumEvenDigits(n/10);

		}

	public static void binaryStringsWithMoreOnes(int n) {
		binaryStringsWithMoreOnes("", 0, 0, n);
	}

	private static void binaryStringsWithMoreOnes(String str, int numZeroes, int numOnes, int n) { // complete this function  
	}
}