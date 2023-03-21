

import java.util.Random;

public class QuickSort {

	static Random rand = new Random(System.currentTimeMillis());

	public static void quicksortRandom(int[] array, int left, int right) {
		if (left <= right) {
			int pivot = array[left + rand.nextInt(right - left + 1)];
			int[] partitionIndex = Partition.partition(array, left, right, pivot);
			int lowerPartitionIndex = partitionIndex[0];
			int upperPartitionIndex = partitionIndex[1];
			quicksortRandom(array, left, lowerPartitionIndex - 1);
			quicksortRandom(array, upperPartitionIndex + 1, right);
		}
	}
}