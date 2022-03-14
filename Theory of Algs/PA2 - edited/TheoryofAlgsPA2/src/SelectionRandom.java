

import java.util.Random;

public class SelectionRandom {

	private static Random rand = new Random(System.currentTimeMillis());

	private static int generateRandomPivot(int[] array, int left, int right) {
		int pivotIndex = left + rand.nextInt(right - left + 1);
		return array[pivotIndex];
	}
	private static void swap(int [] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	public static int select(int[] arr, int left, int right, int k) { // complete this method
		// choose pivot
		int pivIdx = generateRandomPivot(arr, left, right);
		int partitionIdx = findPartitionIdx(arr, pivIdx);
		
		// move the pivot to the partition index
		swap(arr, partitionIdx, pivIdx);
		
		// basic partition algorithm
		for ( int i = 0 ; i < partitionIdx ; i ++)
		{
			if ( arr[i] > arr[partitionIdx])
			{
				// there's a value below the partition index that is greater! This is not allowed
				for (int j = arr.length - 1 ; j >= partitionIdx; j -- )
				{
					if (arr [j] <= arr[partitionIdx])
					{
						// if something is found on the right side that is lower than the value @ partitionIdx then swap with i. This check includes the value @ partition index and has potential to change the value held there
						// if val @ i is less than val @ this j then swap them
						if (arr[i] <= arr[j])
							swap(arr, i, j);
					}
				}
			}
				
		}
		
		return select(arr, left + 1, right - 1, k);
	}
	
	
	
	
	private static int findPartitionIdx(int [] array, int p)
	{
		int count = 0;
		for (int i = 0; i < array.length-1; i++)
		{
			if (array[i] <= p)
				count++;
		}
		
		return count-1;
	}
}
