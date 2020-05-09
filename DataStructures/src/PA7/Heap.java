

import java.util.ArrayList;

public class Heap {

	ArrayList<String> heapArray;

	public Heap() {
		heapArray = new ArrayList<String>();
	}

	public String getMinimum() {
		return heapArray.get(0);
	}

	public void deleteMinimum() { // complete this function
		heapArray.set(0,  heapArray.get(size() - 1));
		heapArray.remove(heapArray.size()-1);
		int curIdx = 0;
		int leftIdx = 1;
		int rightIdx = 2;
		
		while (leftIdx<size()) {
			String curKey = heapArray.get(curIdx);
			String leftKey = heapArray.get(leftIdx);
			if (rightIdx<size())
			{
				String rightKey = heapArray.get(rightIdx);
				if (leftKey.compareTo(curKey) < 0 && leftKey.compareTo(rightKey) < 0)
				{
					//left child is the smallest
					swap(leftIdx, curIdx);
					curIdx = leftIdx;
				} else if (rightKey.compareTo(curKey) < 0)
				{
					swap(rightIdx, curIdx);
					curIdx = rightIdx;
				} else
					break;
			} else if (leftKey.compareTo(curKey) < 0)
			{
				// right child DNE
				swap(leftIdx, curIdx);
				break;
			} else
				break;
		
			leftIdx = 2 * curIdx + 1;
			rightIdx = leftIdx + 1;
		}
	}

	public void insert(String value) { // complete this function
		int curIdx = heapArray.size();
		int parIdx = (curIdx - 1) / 2; // will seperate the pairs proportionally instead of using 2 arrays.
		heapArray.add(value);
		while (curIdx > 0 && heapArray.get(parIdx).compareTo(heapArray.get(curIdx)) > 0 )
		{
		swap(parIdx, curIdx);
		curIdx = parIdx;
		parIdx = (curIdx-1) /2;
		}
	}

	private void swap(int index1, int index2) {

		String temp = heapArray.get(index1);
		heapArray.set(index1, heapArray.get(index2));
		heapArray.set(index2, temp);
	}

	public int size() {
		return heapArray.size();
	}
}
