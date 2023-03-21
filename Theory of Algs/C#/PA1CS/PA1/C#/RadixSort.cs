using System;
using System.Collections;

namespace _433_PA1
{
    public class RadixSort
    {
        private readonly int[] array;
        private readonly int n;

        public RadixSort(int[] array, int length)
        {
            this.array = array;
            this.n = length;
        }

        private static void countSortOnDigits(int[] A, int n, int[] digits)
        { // complete this function
            int[] C = new int[10];
            int[] T = new int[n];

            for (int i = 0; i <= n - 1; i++)
                C[digits[i]]++;

            for (int i = 1; i <= 9; i++)
                C[i] = C[i - 1] + C[i];

            for (int i = n - 1; i >= 0; i--)
            {
                C[digits[i]]--;
                T[C[digits[i]]] = A[i];
            }

            for (int i = 0; i < A.Length; i++)
            {
                A[i] = T[i];
            }
            //Array.Copy(T, A, A.Length);
        }

        private static void radixSortNonNeg(int[] A, int n)
        { // complete this function
            int M = int.MinValue;
            foreach (int i in A) // O(n)
            {
                if (i > M)
                    M = i;
            }
            int [] D = new int[n]; //digits array
            int e = 1;
            while (M/e > 0 )
            {
                for (int i = 0; i <= n-1; i++)
                {
                    D[i] = (A[i] / e) % 10;
                }
                countSortOnDigits(A, n, D);
                e *= 10;
            }
        }

        //APPROACH 1
        public void radixSort()
        {
            int min = int.MaxValue;
            foreach(int i in array)
            {
                if (i < min)
                    min = i;
            }
            if (min > -1) // if the lowest num is non neg, just use unmodified sort
                radixSortNonNeg(array, n);
            else
            {
                // cancel out the lowest negative number by subtracting it from every other number. This will make that number = 0 and offsets the others evenly
                // after the sort, it can easily be converted back by adding that negative number back on, effectively re-applying the offest.

                for (int i = 0; i < array.Length; i++)// offset applied to make all numbers >= 0
                    array[i] -= min;
                
                radixSortNonNeg(array, n);

                for (int i = 0; i < array.Length; i++) // offset is undone
                    array[i] += min;
                
            }
        }

        /*
         * 
         * APPROACH 2
        public void radixSort()
        { // complete this function

            
            System.Collections.Stack NEG = new System.Collections.Stack();
            System.Collections.Stack NONEG = new System.Collections.Stack();
            int[] NEGARR;
            int[] POSARR;

            foreach(int i in array)
            {
                if (i < 0)
                    NEG.Push(i);
                else if (i >= 0)
                    NONEG.Push(i);
            }

            
            POSARR = new int[NONEG.Count]; 
            NONEG.CopyTo(POSARR, 0);
            radixSortNonNeg(POSARR, POSARR.Length);//n from class

            // flip sign of negative array
            NEGARR = new int[NEG.Count];
            NEG.CopyTo(NEGARR, 0);
            for (int i = 0; i < NEGARR.Length-1; i++)
            {
                NEGARR[i] *= -1;
            }
            // after signs are flipped to pos
            radixSortNonNeg(NEGARR, NEGARR.Length);
            // flip back to negative. this will make the order reverse in terms of negatives

            int idx = 0;
            // put negative array back in first in reverse order due to the positive sort algorithm
            for (int i = NEGARR.Length -1; i >=0 ; i--)
            {
                array[idx] = NEGARR[i];
                idx++;
            }

            // now fill rest of class array with the remaining positives that I sorted
            for (int i = 0; i < POSARR.Length; i++)
            {
                array[idx] = POSARR[i];
                idx++;
            }

        } */
    }
}
