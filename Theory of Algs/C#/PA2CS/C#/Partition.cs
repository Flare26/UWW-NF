using System;
namespace _PA2
{
	public class Partition
	{
		private static void swap(int[] array, int x, int y)
		{
			int temp = array[x];
			array[x] = array[y];
			array[y] = temp;
		}

        public static int[] partition(int[] array, int left, int right, int pivot)
		{ // complete this method
			int[] ptIdxs = new int[2]; // pidxs[0] = lower partition index, pidxs[1] = upper partition index

            // find partition index. O(n)
            int pivIdx = left;
            int ptIdx = left - 1; // partition index

            int pivotCount = 0; // count of pivot elements



            for (int k = left; k <= right; k++)
            {
                if (array[k] == pivot)
                    pivIdx = k;

                if (array[k] <= pivot)
                {
                    ptIdx++;
                    if (array[k] == pivot)
                    {
                        pivIdx = ptIdx; // update pivot index to last occurrence of pivot
                        pivotCount++;
                    }
                }
            }

            //partIdx -= 1; // Is = number of items <= pivot -1

            // swap the pivot into the partition index
            // Loop above counts up to the last occurence of pivot value, after which it will place
            // that pivot element at thea appropriate place with swap function.
            // for modified partition, this becomes upper part idx
            
            Partition.swap(array, pivIdx, ptIdx);
            int i = left;
            int j = right;
            ptIdxs[1] = ptIdx; // set upper partition index
            // upper partition index is already found so now to do the partitioning and find lower index
            
            while (i < j)
            {
                if (array[i] <= pivot && i <= ptIdx)
                    i++; // increment i going from left to partIdx for each value lower than pivot. Does not increment if value is > piv ( out of place ) 
                else if (array[j] > pivot && j >= ptIdx)
                    j--; // decrement j going from right to partIdx for each value greater than pivot. Does not decrement if value is < piv ( out of place ) 
                else
                {
                    Partition.swap(array, i, j);
                    i++;
                    j--;
                }
            }

            i = left;
            j = ptIdx - 1;
            // this loops find occurences that are lower than pivot and puts them at the front of array

            while (i < j)
            {
                if (array[i] == pivot && array[j] < pivot)
                {
                    
                    Partition.swap(array, i, j);
                    i++;
                    j--;
                } else if (array[j] == pivot)
                {
                    j--; // this will keep decrementing j until it's at a value not equal to pivot ( less than due to partition )
                } else if (array[i] != pivot)
                {
                    i++;
                }

            }

            // find lower ptidx
            for (int s = 0; i < array.Length; i++ )
            {
                if (array[i] == pivot)
                    pivotCount++;
            }
            ptIdxs[0] = ptIdx - pivotCount + 1;
            return ptIdxs;
		}
	}
}
