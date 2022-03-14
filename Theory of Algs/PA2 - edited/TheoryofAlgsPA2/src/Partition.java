

public class Partition {

	private static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static int[] partition(int[] array, int left, int right, int pivot) {
		// complete this method
		//partition index = number of items less than or equal to pivot value, then - 1
		int partitionIdx = findPartitionIdx(array, pivot);
		int pivotIdx = -1;
		// find where the pivot is currently in the array
		for (int i = 0 ; i < array.length-1; i++)
		{
			if (array[i] == pivot)
			{
				pivotIdx = i;
				break;
			}
		}
		
		//swap where the pivot currently is with value @ where found the partition index to be
		swap(array, partitionIdx, pivotIdx);
		// now the pivot is really at partitionIdx which is what I calculated before doing any real swapping.
		
		// sweep from left until hit a value higher than pivot.
		for (int i = 0 ; i < array.length-1; i++)
		{
			//if the left sweep detects a value out-of-place, begin the right sweep and see if there is a val <= the pivot
			if (array[i] > array[partitionIdx])
			{
				for (int j = array.length-1; j >= partitionIdx + 1; j--)
				{
					if (array[j] <= array[partitionIdx])
					{
						if ( i < j )
							swap(array, i,j);
						break;
					}
					if ( i > partitionIdx || j < partitionIdx )
						return partitionIdx;
				}
			}
		}
		return partitionIdx;
		
	}
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