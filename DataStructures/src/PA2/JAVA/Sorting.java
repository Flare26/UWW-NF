package PA2.JAVA;

// Nathan Frazier

import java.util.Arrays;

public class Sorting {

	private static void selectionSort(int[] array, int arrayLen) { // Complete this function
		
		  
        // incrementally decrease the top limit index
        for (int i = 0; i < arrayLen-1; i++) 
        { 
            // find minimum element of (new) array boundaries
            int minIdx = i; 
            for (int j = i+1; j < arrayLen; j++) 
                if (array[j] < array[minIdx]) 
                    minIdx = j; 
  
            // Swap into correct position
            int temp = array[minIdx]; 
            array[minIdx] = array[i]; 
            array[i] = temp; 
        }
		
	}

	private static void insertionSort(int[] array, int arrayLen) { // Complete this function
		// start with one above in order to check what comes before it
        for (int i = 1; i < arrayLen; ++i) { 
            int key = array[i]; 
            int j = i - 1; 
 
            // shift elements greater than key up one index relative to where they were
            while (j >= 0 && array[j] > key) { 
                array[j + 1] = array[j]; 
                j = j - 1; 
            } 
            array[j + 1] = key; 
        } 
		
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
