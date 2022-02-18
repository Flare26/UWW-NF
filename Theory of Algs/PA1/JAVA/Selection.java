

public class Selection extends Partition {

	public Selection(int[] array, int n) {
		super(array, n);
	}

	public int select(int k) {
		return select(0, n - 1, k);
	}

	public int select(int left, int right, int k) { // complete this function
	}
}
