using System;
namespace _433_PA1
{
    public class QuickSort : Partition
    {
        public QuickSort(int[] array, int n) : base(array, n)
        {
        }

        public void quicksortMedianOf3()
        {
            quicksortMedianOf3(0, n - 1);
        }

        public void quicksortRandom()
        {
            quicksortRandom(0, n - 1);
        }

        private void quicksortMedianOf3(int left, int right)
        { // complete this function
            if (left < right)
            {
                int piv = generateMedianOf3Pivot(left, right);
                int ptx = partition(left, right, piv);
                //recursion time. Recurse left, then recurse right.
                quicksortMedianOf3(left, ptx - 1);
                quicksortMedianOf3(ptx + 1, right);
            }
        }

        private void quicksortRandom(int left, int right)
        { // complete this function
            if (left < right)
            {
                int piv = generateRandomPivot(left,right);
                int ptx = partition(left, right, piv);
                //recursion time. Recurse left, then recurse right.
                quicksortRandom(left, ptx - 1);
                quicksortRandom(ptx + 1, right);
            }
        }
    }
}
