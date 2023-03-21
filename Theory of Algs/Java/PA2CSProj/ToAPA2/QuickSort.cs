using System;
namespace _PA2
{
    public class QuickSort
	{
		private static Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));

		public static void quicksortRandom(int[] array, int left, int right)
		{
			if (left <= right)
			{
				int pivot = array[left + rand.Next(right - left + 1)];
				int[] partitionIndex = Partition.partition(array, left, right, pivot);
				int lowerPartitionIndex = partitionIndex[0];
				int upperPartitionIndex = partitionIndex[1];
				quicksortRandom(array, left, lowerPartitionIndex - 1);
				quicksortRandom(array, upperPartitionIndex + 1, right);
			}
		}
	}
}
