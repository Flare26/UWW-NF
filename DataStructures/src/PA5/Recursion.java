public class Recursion {
//Nathan Frazier
	public static int sumEvenDigits(int n) { // complete this function
		if ( n <= 0)
			return 0;
		
		if ( n % 2 == 0 ) {
			return sumEvenDigits(n/10) + n%10;
		} else {
		 	// nothing gets added if odd, just pass in the next number set by div by 10. This effectively moves decimal place left
			return sumEvenDigits(n/10);
		}
		}

	public static void binaryStringsWithMoreOnes(int n) {
		binaryStringsWithMoreOnes("", 0, 0, n);
	}

	private static void binaryStringsWithMoreOnes(String str, int numZeroes, int numOnes, int n) { // complete this function  
		if ( str.length() == n && numZeroes < numOnes )
			System.out.println(str);
		else if ( str.length() < n ) {
			// Method will run again with appending 0. It will end up running both cases
			binaryStringsWithMoreOnes(str + "0" , numZeroes + 1, numOnes, n);
			binaryStringsWithMoreOnes(str + "1" , numZeroes, numOnes + 1, n);
		}
		
	}
}