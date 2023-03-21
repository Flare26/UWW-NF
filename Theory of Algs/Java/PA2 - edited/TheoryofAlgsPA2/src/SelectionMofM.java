

public class SelectionMofM {

	private static void insertionSort(int[] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int j = i, temp = arr[j];
			while (j > left && temp < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}

	public static int select(int[] arr, int left, int right, int k) {
		// k is the number of sub arrays that arr will be broken into, but each sub array created will be of size 5
		insertionSort(arr, left, right);
		return null; // complete this method
	}
}
