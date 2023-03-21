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
        { // complete this function
            int[] pidxs = new int[2]; // pidxs[0] = lower partition index, pidxs[1] = upper partition index
            if (left == right)
                return arr[left];

            int ptx = left - 1;
            int MoM = GenerateMoMPivot(arr, left, right, k);
            pidxs = Partition.partition(arr, left, right, MoM);

            if (k >= pidxs[0] - left + 1 && k <= pidxs[1] - left + 1)
                return MoM;

            else if (k < pidxs[0] - left + 1)
                return select(arr, left, pidxs[0], k);
            else
                return select(arr, pidxs[1] + 1, right, k - (right - pidxs[1] + 1));
        }

        static int GenerateMoMPivot(int[] arr, int left, int right, int k)
        {
            // If the array size is group sized, just return the median
            if (right - left + 1 <= 5)
            {
                insertionSort(arr, left, right);
                return arr[(left + right) / 2];
            }

            // Otherwise, divide the array into groups of 5 elements and find the medians of those groups
            //int numGroups = (int)Math.Ceiling((double)(right - left + 1) / 5); // ceiling will ensure we have enough groups to cover the whole array
            int numGroups = (int) Math.Round((right - left + 1) / 5.0, MidpointRounding.ToPositiveInfinity);
            int[] medians = new int[numGroups];
            for (int i = 0; i < numGroups; i++)
            {
                int groupLeft = left + i * 5;
                int groupRight = Math.Min(groupLeft + 4, right); // taking the minimum of these two covers the case in which the last array is smaller than 5
                insertionSort(arr, groupLeft, groupRight);
                medians[i] = arr[(groupLeft + groupRight) / 2];
            }

            // Recursively find the median of the medians
            return GenerateMoMPivot(medians, 0, medians.Length - 1, medians.Length/2);
        }
    }

}
