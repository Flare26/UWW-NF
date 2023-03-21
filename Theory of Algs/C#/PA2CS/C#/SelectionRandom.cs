using System;
namespace _PA2
{
	public class SelectionRandom
	{
		private static Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));

		private static int generateRandomPivot(int[] array, int left, int right)
		{
			int pivotIndex = left + rand.Next(right - left + 1);
			return array[pivotIndex];
		}

        public static int select(int[] arr, int left, int right, int k)
        { // complete this method
            if (left == right)
                return arr[left];

            int[] ptIdx = new int[2]; // pidxs[0] = lower partition index, pidxs[1] = upper partition index
            int piv = generateRandomPivot(arr, left, right);
            ptIdx = Partition.partition(arr, left, right, piv);

            if (k >= ptIdx[0] - left + 1 && k <= ptIdx[1] - left + 1)
                return piv;

            else if (k < ptIdx[0] - left + 1)
                return select(arr, left, ptIdx[0] - 1, k);

            else
                return select(arr, ptIdx[1] + 1, right, k - (ptIdx[1] - left + 1));
        }

    }
}
