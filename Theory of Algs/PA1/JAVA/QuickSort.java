

public class QuickSort extends Partition {

	public QuickSort(int[] array, int n) {
		super(array, n);
	}

	public void quicksortMedianOf3() {
		quicksortMedianOf3(0, n - 1);
	}

	public void quicksortRandom() {
		quicksortRandom(0, n - 1);
	}

	private void quicksortMedianOf3(int left, int right) { // complete this function
		// uses median number as the pivot point
		int mid = left + right / 2;
		int partitionIndex = partition(left, right, mid);
		quicksortRandom(left, partitionIndex - 1);
		quicksortRandom(partitionIndex + 1, right);
	}

	private void quicksortRandom(int left, int right) { // complete this function
		// quicksort recursively sorts by going back and recursively sorting smaller partitions made from original partition task.
		int pivot = generateRandomPivot(left,right);
		int partitionIndex = partition(left, right, pivot);
		quicksortRandom(left, partitionIndex - 1);
		quicksortRandom(partitionIndex + 1, right);
	}
}
