import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortAndFriends {

	private static int[] binaryMerge(int A[], int B[], int lenA, int lenB) { // complete this function
	}

	public static ArrayList<Integer> commonElements(int A[], int B[], int lenA, int lenB) { // complete this function
	}

	public static int[] kWayMerge(int lists[][], int listLengths[], int k) { // complete this function
	}

	public static void mergesort(int[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergesort(array, left, mid);
			mergesort(array, mid + 1, right);
			int A[] = Arrays.copyOfRange(array, left, mid + 1);
			int B[] = Arrays.copyOfRange(array, mid + 1, right + 1);
			int mergedArray[] = binaryMerge(A, B, A.length, B.length);
			int i = left;
			int j = 0;
			while (j <= right - left)
				array[i++] = mergedArray[j++];
		}
	}
}