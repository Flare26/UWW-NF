using System;
namespace _PA2
{
	public class SelectionMofM
	{
		private static void insertionSort(int[] arr, int left, int right)
		{
			for (int i = left + 1; i <= right; i++)
			{
				int j = i, temp = arr[j];
				while (j > left && temp < arr[j - 1])
				{
					arr[j] = arr[j - 1];
					j--;
				}
				arr[j] = temp;
			}
		}

		public static int select(int[] arr, int left, int right, int k)
		{// complete this method
		}
	}
}
