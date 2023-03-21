using System;
using System.Collections.Generic;

namespace _PA3
{
    public class LIS
    {
		public static List<int> longestIncreasingSequence(int[] arr, int len)
		{ // complete this method
		}

		private static void reverse(List<int> list)
		{
			for (int i = 0, j = list.Count - 1; i < j; i++, j--)
			{
				int temp = list[j];
				list[j] = list[i];
				list[i] = temp;
			}
		}
	}
}
