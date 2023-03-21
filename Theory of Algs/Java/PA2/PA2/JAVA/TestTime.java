

import java.util.Arrays;
import java.util.Random;

public class TestTime {

	final static int[] LARGE_STRING_SIZES = { 1000000, 5000000, 10000000, 15000000, 20000000, 25000000 };

	static Random rand = new Random(System.currentTimeMillis());

	private static void testIfSorted(int A[], int len, char s) throws Exception {
		for (int i = 0; i < len - 1; i++)
			if (A[i] > A[i + 1]) {
				if (s == 'M')
					throw new Exception("MergeSort code is incorrect");
				else if (s == '3')
					throw new Exception("QuickSort (median of 3) code is incorrect");
				else if (s == 'Q')
					throw new Exception("QuickSort (randomized) code is incorrect");
				else if (s == 'R')
					throw new Exception("RadixSort code is incorrect");
			}
	}

	private static void compareSorting() throws Exception {
		System.out.println("****************** Time Test Sorting ******************");
		double mergeAvg = 0, quickRandomAvg = 0;
		int numExec = 0;
		String[] duplicateText = { "Very Few Duplicates", "Few Duplicates", "More Duplicates", "Even More Duplicates",
				"Plenty of Duplicates" };
		for (int duplicate = 10, k = 0; duplicate <= 100000; duplicate *= 10, k++) {
			System.out.println("\n**** " + duplicateText[k] + " ****\n");
			System.out.println("Length,         MergeSort,         Randomized Quick-Sort");
			for (int num = 500000; num <= 50000000; num *= 2) {
				int[] array = new int[num];
				int[] temp = new int[num];
				for (int i = 0; i < num; i++)
					array[i] = rand.nextInt(num / duplicate);

				double timeStart, timeEnd;

				for (int i = 0; i < num; i++)
					temp[i] = array[i];

				timeStart = System.currentTimeMillis();
				new MergeSort(array, num).mergesort();
				timeEnd = System.currentTimeMillis();

				testIfSorted(array, num, 'M');
				mergeAvg += (timeEnd - timeStart);
				System.out.printf("%8d,        %7.2f", num, (timeEnd - timeStart));

				for (int i = 0; i < num; i++)
					temp[i] = array[i];

				timeStart = System.currentTimeMillis();
				QuickSort.quicksortRandom(temp, 0, temp.length - 1);
				timeEnd = System.currentTimeMillis();

				testIfSorted(temp, num, 'Q');
				quickRandomAvg += (timeEnd - timeStart);
				System.out.printf(",             %7.2f\n", (timeEnd - timeStart));

				numExec++;
			}
			System.out.printf("\nMergeSort average time is: %.2f millisecs%n", mergeAvg / numExec);
			System.out.printf("QuickSort (randomized) average time is: %.2f millisecs%n", quickRandomAvg / numExec);
		}
	}

	private static void compareSelection() throws Exception {
		System.out.println("\n****************** Time Test Selection ******************\n");
		double randomAvg = 0, momAvg = 0;
		int numExec = 0;
		System.out.println("Length,      Median of Medians,      Randomized Selection");
		for (int num = 500000; num <= 50000000; num *= 2) {
			int[] array = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = rand.nextInt(num / 10000);

			double timeStart;
			int K[] = new int[(int) Math.log(num)];
			int lenK = K.length;
			for (int i = 0; i < lenK; i++)
				K[i] = rand.nextInt(num);

			double selTimeMOM = 0, selTimeRandom = 0;
			int selArray[] = new int[num];
			int[] answersMoM = new int[lenK];
			int[] answersRandom = new int[lenK];
			for (int i = 0; i < lenK; i++) {

				for (int j = 0; j < num; j++)
					selArray[j] = array[j];

				timeStart = System.currentTimeMillis();
				answersMoM[i] = SelectionMofM.select(selArray, 0, selArray.length - 1, K[i]);
				selTimeMOM += System.currentTimeMillis() - timeStart;

				for (int j = 0; j < num; j++)
					selArray[j] = array[j];

				timeStart = System.currentTimeMillis();
				answersRandom[i] = SelectionRandom.select(selArray, 0, selArray.length - 1, K[i]);
				selTimeRandom += System.currentTimeMillis() - timeStart;

			}
			Arrays.sort(array);
			for (int i = 0; i < lenK; i++) {
				if (answersMoM[i] != array[K[i] - 1])
					throw new Exception("Code for Median of Medians is incorrect.");
				if (answersRandom[i] != array[K[i] - 1])
					throw new Exception("Code for Randomized Selection is incorrect.");
			}
			randomAvg += selTimeRandom;
			momAvg += selTimeMOM;
			numExec += lenK;
			System.out.printf("%8d, %12.2f, %24.2f%n", num, selTimeMOM / lenK, selTimeRandom / lenK);
		}
		System.out.printf("\nSelection using median of medians average time is: %.2f millisecs%n", momAvg / numExec);
		System.out.printf("Selection using random pivot average time is: %.2f millisecs%n", randomAvg / numExec);
	}

	private static void testStringSorter() throws Exception {
		System.out.println("\n****************** Time Test String Sorting ******************\n");
		long timeMerge = 0, timeRadix = 0;
		Random rand = new Random(System.currentTimeMillis());
		int maxLength = 25;
		for (int size : LARGE_STRING_SIZES) {
			String[] radixSortStrings = new String[size];
			String[] mergeSortStrings = new String[size];
			for (int i = 0; i < size; i++) {
				int stringLength = 1 + rand.nextInt(maxLength);
				StringBuilder str = new StringBuilder();
				for (int j = 0; j < stringLength; j++)
					str.append((char) (97 + rand.nextInt(26)));

				radixSortStrings[i] = mergeSortStrings[i] = str.toString();
			}

			long startTime = System.currentTimeMillis();
			new StringMergeSort(mergeSortStrings, size).mergesort();
			timeMerge = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			StringSorter.radixSort(radixSortStrings, size);
			timeRadix = System.currentTimeMillis() - startTime;

			for (int i = 0; i < radixSortStrings.length - 1; i++) {
				if (mergeSortStrings[i].compareTo(mergeSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with merge sort");
				if (radixSortStrings[i].compareTo(radixSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with radix sort");
			}

			System.out.println(
					"Merge-sort time for " + size + " strings = " + timeMerge + " (may vary with each execution)");
			System.out.println(
					"Radix-sort time for " + size + " strings = " + timeRadix + " (may vary with each execution)");
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		compareSorting();
		compareSelection();
		testStringSorter();
	}
}
