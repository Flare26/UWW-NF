public class RotatedBinarySearch {
//Nathan Frazier
	public static int search(int array[], int length, int key) { // complete this function
	}

	private static int maxIndex(int array[], int lastValue, int left, int right) { // complete this function
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