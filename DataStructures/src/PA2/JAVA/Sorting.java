

import java.util.Arrays;

public class Sorting {

	private static void selectionSort(int[] array, int arrayLen) { // Complete this function
	}

	private static void insertionSort(int[] array, int arrayLen) { // Complete this function
	}

	public static void main(String[] args) {
		int A[] = { 13, 17, 8, 14, 1 };
		System.out.println("Original Array: " + Arrays.toString(A));
		selectionSort(A, A.length);
		System.out.println("Selection Sorted Array: " + Arrays.toString(A));

		System.out.println();

		int B[] = { -13, -17, -8, -14, -1, -20 };
		System.out.println("Original Array: " + Arrays.toString(B));
		insertionSort(B, B.length);
		System.out.println("Insertion Sorted Array: " + Arrays.toString(B));
	}
}
