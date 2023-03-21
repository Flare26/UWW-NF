using System;
namespace _433_PA1
{
    public class Partition
    {

        protected readonly int[] array;
        protected readonly int n;

        static Random rand;

        public Partition(int[] array, int n)
        {
            this.array = array;
            this.n = n;
            rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));
        }

        protected void swap(int x, int y)
        {
            int temp = array[x];
            array[x] = array[y];
            array[y] = temp;
        }

        protected int generateRandomPivot(int left, int right)
        {
            int pivotIndex = left + rand.Next(right - left + 1);
            return array[pivotIndex];
        }

        protected int generateMedianOf3Pivot(int left, int right)
        {
            int mid = (left + right) / 2;

            if (array[left] > array[mid])
                swap(left, mid);

            if (array[left] > array[right])
                swap(left, right);

            if (array[mid] > array[right])
                swap(mid, right);

            return array[mid];
        }

        public int partition(int left, int right, int pivot)
        { // complete this function
            // find partition index. O(n)

            int pivotIdx = left; // pivot idx
            int partIdx = left-1; // part idx
            for (int k = left; k <= right; k++)
                {
                if (array[k] == pivot)
                    pivotIdx = k;

                if (array[k] <= pivot)
                    partIdx++;
            }

            //partIdx -= 1; // Is = number of items <= pivot -1
                       // swap the pivot into the partition index

            swap(pivotIdx, partIdx);
            int i = left;
            int j = right;

            // below while loop searches for elements out of place on their respective sides of pivot and swaps them 
            while (i < j)
            {
                if (array[i] <= pivot && i <= partIdx)
                    i++; // increment i going from left to partIdx for each value lower than pivot. Does not increment if value is > piv ( out of place ) 
                else if (array[j] > pivot && j >= partIdx)
                    j--; // decrement j going from right to partIdx for each value greater than pivot. Does not decrement if value is < piv ( out of place ) 
                else
                {
                    swap(i, j);
                    i++;
                    j--;
                } 
            }
            return partIdx;
        }


    }
}
