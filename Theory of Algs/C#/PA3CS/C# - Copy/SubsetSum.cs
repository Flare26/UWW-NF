using System;
using System.Collections.Generic;
namespace _PA3
{
    public class SubsetSum
    {
		public static bool isSumPossible(int[] elements, int numElements, int target)
		{
			SortedSet<int> sums = new SortedSet<int>();
			sums.Add(0);
			for (int i = 0; i < numElements; i++)
			{
				int[] temp = new int[sums.Count];
				int j = 0;
				IEnumerator<int> it = sums.GetEnumerator();
				while (it.MoveNext())
					temp[j++] = it.Current;
				int s = sums.Count;
				for (j = 0; j < s; j++)
				{
					int val = temp[j] + elements[i];
					if (val == target)
						return true;
					else if (val < target && !sums.Contains(val))
						sums.Add(val);
				}
			}
			return false;
		}
	}
}
