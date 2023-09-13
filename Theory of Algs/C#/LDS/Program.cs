using System;

namespace LDS
{
    class Program
    {
        static int lds(int[] arr, int n, out int[] ldsArray, out int[] predArray)
        {
            int[] lds = new int[n];
            int[] pred = new int[n];
            int i, j, max = 0;

            // Initialize LDS with 1
            // for all index. The minimum
            // LDS starting with any
            // element is always 1
            for (i = 0; i < n; i++)
            {
                lds[i] = 1;
                pred[i] = -1;
            }

            // Compute LDS from every
            // index in bottom-up manner
            for (i = 1; i < n; i++)
                for (j = 0; j < i; j++)
                    if (arr[i] < arr[j] && lds[i] < lds[j] + 1)
                    {
                        lds[i] = lds[j] + 1;
                        pred[i] = j;
                    }

            // Select the maximum
            // of all the LDS values
            for (i = 0; i < n; i++)
                if (max < lds[i])
                    max = lds[i];

            ldsArray = lds;
            predArray = pred;

            // returns the length
            // of the LDS
            return max;
        }

        public static void Main()
        {
            int[] arr = { 130, 90, 60, 150, 80, 40, 70, 0, 120, 95 };
            int n = arr.Length;

            int[] length, pred;
            int ldsLength = lds(arr, n, out length, out pred);

            Console.Write("Length of LDS is " + ldsLength);
            Console.WriteLine();

            Console.Write("length[]: ");
            for (int i = 0; i < n; i++)
            {
                Console.Write(length[i] + " ");
            }
            Console.WriteLine();

            Console.Write("pred[]: ");
            for (int i = 0; i < n; i++)
            {
                Console.Write(pred[i] + " ");
            }
            Console.WriteLine();
        }
    }
}
