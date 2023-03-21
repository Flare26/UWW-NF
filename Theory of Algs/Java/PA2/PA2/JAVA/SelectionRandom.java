

import java.util.Random;

public class SelectionRandom {

	private static Random rand = new Random(System.currentTimeMillis());

	private static int generateRandomPivot(int[] array, int left, int right) {
		int pivotIndex = left + rand.nextInt(right - left + 1);
		return array[pivotIndex];
	}

	public static int select(int[] arr, int left, int right, int k) { // complete this method
	}
}
