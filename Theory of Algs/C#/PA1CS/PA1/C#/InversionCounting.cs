using System;
namespace _433_PA1
{
    public class InversionCounting
    {
        private readonly int[] mergedArray;
        private readonly int[] array;
        private readonly int n;

        public InversionCounting(int[] array, int n)
        {
            this.array = array;
            this.mergedArray = new int[n];
            this.n = n;
        }

        public int bruteForce()
        {
            int count = 0;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (array[i] > array[j])
                        count++;
            return count;
        }

        public int countInversions()
        {
            return countInversions(0, n - 1);
        }

        public int countInversions(int left, int right)
        { // complete this function


            int count = 0;
            if (left == right)
                return count;
            int mid = (left + right) / 2;
            count += countInversions(left, mid);
            count += countInversions(mid + 1, right);
            int i = left;
            while (i <= right)
            {
                mergedArray[i] = array[i];
                i++;
            }
            i = left;
            int j = mid + 1, k = left;
            while (i <= mid && j <= right)
            {

                if (mergedArray[j] < mergedArray[i])
                {
                    count += mid - i + 1;
                    array[k] = mergedArray[j];
                    j++;
                    k++;
                }
                else
                {
                    array[k] = mergedArray[i];
                    i++;
                    k++;
                }
            }
           
            while (i <= mid)
                array[k++] = mergedArray[i++];

            return count;
        }
    }
}
