

public class BinarySearchApplications {

	private static int minIndexBinarySearch(int array[], int arrayLength, int key) { // Complete this function.
	}

	private static int maxIndexBinarySearch(int array[], int arrayLength, int key) { // Complete this function.
	}

	private static int countNumberOfKeys(int array[], int arrayLength, int key) { // Complete this function.
	}

	private static int predecessor(int array[], int arrayLen, int key) { // Complete this function.
	}

	public static void main(String[] args) throws Exception {

		int A[] = { 1, 1, 3, 7, 9, 14, 14, 14, 14, 14, 14, 18, 27, 39, 39, 39 };
		System.out.println("*** Counting the number of occurrences of key ***\n");
		System.out.println("Number of occurrences of 1 is " + countNumberOfKeys(A, A.length, 1));
		System.out.println("Number of occurrences of 14 is " + countNumberOfKeys(A, A.length, 14));
		System.out.println("Number of occurrences of 39 is " + countNumberOfKeys(A, A.length, 39));
		System.out.println("Number of occurrences of 7 is " + countNumberOfKeys(A, A.length, 7));
		System.out.println("Number of occurrences of 100 is " + countNumberOfKeys(A, A.length, 100));
		System.out.println("Number of occurrences of -88 is " + countNumberOfKeys(A, A.length, -88));
		System.out.println("Number of occurrences of 16 is " + countNumberOfKeys(A, A.length, 16));

		System.out.println("\n*** Finding Predecessor ***\n");
		int B[] = { 1, 0, 39, 47, 36, 12, 6 };
		for (int i = 0; i < B.length; i++) {
			int key = B[i];
			int x = predecessor(A, A.length, key);
			if (x >= 0)
				System.out.println("Predecessor of " + B[i] + " is " + A[x]);
			else
				System.out.println("Predecessor of " + B[i] + " is not defined.");
		}
	}
}
