public class RotatedBinarySearch {
//Nathan Frazier
	public static int search(int array[], int length, int key) { // complete this function
		// use binary search to find index where the shift point is located
		int solution = -1;
		int maxIdx = maxIndex(array , array[length-1] , 0 , length - 1);
		int left = 0;
		int right = length - 1 ;
			int midpoint = left + ( right - left ) / 2;
			// If the middle of L & R is greater than the rightmost bound, restrict bounds to one greater than it. 
			// Tells us which side of the array will potentially contain our key
			if ( array[midpoint] > array[right])
				left = midpoint + 1; // middle element is for sure NOT the smallest
			else
				right = midpoint; // if array[mid] < array [right] then from right -> mid is sorted as expected so set right
			
			return binarySearch ( array , maxIdx + 1, length - 1, key);
					
	}

	private static int maxIndex(int array[], int lastValue, int left, int right) { // complete this function
		// find idx of max element
		int maxIdx = -1;
		if ( left == right)
			return array[right];
		
		int midpoint = left + ( right - left ) / 2;
		
		if ( array[midpoint] > array[midpoint+1] )
			return midpoint;
		//Check left side
		else if ( array[midpoint] < lastValue )
			maxIdx = maxIndex(array , lastValue, left, midpoint - 1);
		else // check right side of array
			maxIdx = maxIndex(array, lastValue, midpoint + 1, right);
		return maxIdx;
	}

	private static int binarySearch(int array[], int left, int right, int key) {
		if (left <= right) {
			int mid = (left + right) / 2;
			if (array[mid] == key) {
				return mid;
			} else if (array[mid] < key)
				return binarySearch(array, mid + 1, right, key);
			else
				return binarySearch(array, left, mid - 1, key);
		}
		return -1;
	}
}