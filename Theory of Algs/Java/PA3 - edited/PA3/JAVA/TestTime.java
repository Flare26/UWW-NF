import java.util.Arrays;
import java.util.Random;

public class TestTime {

	private static void testMaxSumSubarrayHelper(int A[], int length) {
		System.out.print("Array: " + Arrays.toString(A) + "\n");
		int answer[] = MaxSubarraySum.method3(A, length);
		System.out.printf("Maximum sum subarray has sum = %d. It starts at index = %d and ends at index = %d%n",
				answer[0], answer[1], answer[2]);
	}

	private static void testMaxSumSubarray() {
		System.out.println("****************** Maximum Subarray Sum ******************\n");
		int A[] = { 10, -5, 15, -10, 20, -15 };
		int length = A.length;
		testMaxSumSubarrayHelper(A, length);

		System.out.println("\n***\n");
		int B[] = { 70, -80, 60, 80, -90, 100, -150, 50 };
		length = B.length;
		testMaxSumSubarrayHelper(B, length);

		System.out.println("\n***\n");
		int C[] = { 70, -50, 40, -70, 20, 40, -10, 15 };
		length = C.length;
		testMaxSumSubarrayHelper(C, length);
	}

	private static void loadTestMaxSumSubArray() throws Exception {
		
		System.out.println("\n*** Method 1 vs Method 2 vs Method 3 ***\n");
		for (int maxArrayLen = 1000; maxArrayLen <= 150000; maxArrayLen = (int) (maxArrayLen * 1.2)) {
			Random rand = new Random(System.currentTimeMillis());
			int A[] = new int[maxArrayLen];
			for (int i = 0; i < maxArrayLen; i++) {
				A[i] = 2500 - rand.nextInt(5000);
			}
			long startTime_1, startTime_2, startTime_3;
			long timeDiff_1, timeDiff_2, timeDiff_3;
			startTime_1 = System.currentTimeMillis();
			int[] answer_1 = MaxSubarraySum.method1(A, maxArrayLen);
			timeDiff_1 = System.currentTimeMillis() - startTime_1;

			startTime_2 = System.currentTimeMillis();
			int[] answer_2 = MaxSubarraySum.method2(A, maxArrayLen);
			timeDiff_2 = System.currentTimeMillis() - startTime_2;

			startTime_3 = System.currentTimeMillis();
			int[] answer_3 = MaxSubarraySum.method3(A, maxArrayLen);
			timeDiff_3 = System.currentTimeMillis() - startTime_3;

			if (answer_1[0] != answer_2[0] || answer_2[0] != answer_3[0])
				throw new Exception("Okay, we are screwed!");

			System.out.printf(
					"Length = %6d, Method 1 Time = %4dms, Method 2 Time = %2dms, Method 3 Time = %dms\n",
					maxArrayLen, timeDiff_1, timeDiff_2, timeDiff_3);
		}
		
		System.out.println("\n*** Method 2 vs Method 3 ***\n");
		for (int maxArrayLen = 150000; maxArrayLen <= 50000000; maxArrayLen = (int) (maxArrayLen * 1.2)) {
			Random rand = new Random(System.currentTimeMillis());
			int A[] = new int[maxArrayLen];
			for (int i = 0; i < maxArrayLen; i++) {
				A[i] = 2500 - rand.nextInt(5000);
			}
			long startTime_2, startTime_3;
			long timeDiff_2, timeDiff_3;

			startTime_2 = System.currentTimeMillis();
			int[] answer_2 = MaxSubarraySum.method2(A, maxArrayLen);
			timeDiff_2 = System.currentTimeMillis() - startTime_2;

			startTime_3 = System.currentTimeMillis();
			int[] answer_3 = MaxSubarraySum.method3(A, maxArrayLen);
			timeDiff_3 = System.currentTimeMillis() - startTime_3;

			if (answer_2[0] != answer_3[0])
				throw new Exception("Okay, we are screwed!");

			System.out.printf(
					"Length = %8d, Method 2 Time = %4dms, Method 3 Time = %2dms\n",
					maxArrayLen, timeDiff_2, timeDiff_3);
		}
	}
	
	public static void main(String[] args) throws Exception {
		testMaxSumSubarray();
		loadTestMaxSumSubArray();
	}
}
