
import java.util.ArrayList;

public class LIS {

	public static ArrayList<Integer> longestIncreasingSubsequence(int[] arr, int len) { // complete this method
	}

	private static void reverse(ArrayList<Integer> list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}
