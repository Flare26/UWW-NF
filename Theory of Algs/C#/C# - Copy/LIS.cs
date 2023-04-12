using System;
using System.Collections.Generic;

namespace _PA3
{
    public class LIS
    {
		public static List<int> longestIncreasingSequence(int[] arr, int len)
		{ // complete this method
            int[] length = new int[len];
            int[] pred = new int[len];

            for (int i = 0; i < len; i++)
            {
                length[i] = 1;
                pred[i] = -1;

                for (int j = 0; j < i; j++)
                {
                    if (arr[j] < arr[i] && length[j] + 1 > length[i])
                    {
                        length[i] = length[j] + 1;
                        pred[i] = j;
                    }
                }
            }

            int lisIndex = 0;
            for (int i = 1; i < len; i++)
            {
                if (length[i] > length[lisIndex])
                {
                    lisIndex = i;
                }
            }

            List<int> lis = new List<int>();
            while (lisIndex >= 0)
            {
                lis.Add(arr[lisIndex]);
                lisIndex = pred[lisIndex];
            }

            lis.Reverse();
            return lis;
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
