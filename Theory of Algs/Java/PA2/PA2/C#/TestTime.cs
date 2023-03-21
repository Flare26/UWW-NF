using System;
namespace _PA2
{
    public class TestTime
    {
        public TestTime()
        {
        }

        static int[] LARGE_STRING_SIZES = { 1000000, 5000000, 10000000, 15000000, 20000000, 25000000 };

        private static Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));

        private static long currentTimeMillis()
        {
            return DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
        }

        private static void testIfSorted(int[] A, int len, char s)
        {
            for (int i = 0; i < len - 1; i++)
                if (A[i] > A[i + 1])
                {
                    if (s == 'M')
                        throw new Exception("MergeSort code is incorrect");
                    else if (s == '3')
                        throw new Exception("QuickSort (median of 3) code is incorrect");
                    else if (s == 'Q')
                        throw new Exception("QuickSort (randomized) code is incorrect");
                    else if (s == 'R')
                        throw new Exception("RadixSort code is incorrect");
                }
        }

        private static void compareSorting()
        {
            Console.WriteLine("****************** Time Test Sorting ******************");
            double mergeAvg = 0, quickRandomAvg = 0;
            int numExec = 0;
            String[]
            duplicateText = {
        "Very Few Duplicates", "Few Duplicates", "More Duplicates", "Even More Duplicates",
                "Plenty of Duplicates" };
            for (int duplicate = 10, k = 0; duplicate <= 100000; duplicate *= 10, k++)
            {
                Console.WriteLine("\n**** " + duplicateText[k] + " ****\n");
                Console.WriteLine("Length,         MergeSort,         Randomized Quick-Sort");
                for (int num = 500000; num <= 50000000; num *= 2)
                {
                    int[] array = new int[num];
                    int[] temp = new int[num];
                    for (int i = 0; i < num; i++)
                        array[i] = rand.Next(num / duplicate);

                    double timeStart, timeEnd;

                    for (int i = 0; i < num; i++)
                        temp[i] = array[i];

                    timeStart = currentTimeMillis();
                    new MergeSort(array, num).mergesort();
                    timeEnd = currentTimeMillis();

                    testIfSorted(array, num, 'M');
                    mergeAvg += (timeEnd - timeStart);
                    Console.Write("{0,8},        {1,7:##.00}", num, (timeEnd - timeStart));

                    for (int i = 0; i < num; i++)
                        temp[i] = array[i];

                    timeStart = currentTimeMillis();
                    QuickSort.quicksortRandom(temp, 0, temp.Length - 1);
                    timeEnd = currentTimeMillis();

                    testIfSorted(temp, num, 'Q');
                    quickRandomAvg += (timeEnd - timeStart);
                    Console.Write(",             {0,7:##.00}\n", (timeEnd - timeStart));

                    numExec++;
                }
                Console.Write("\nMergeSort average time is: {0:F2} millisecs\n", mergeAvg / numExec);
                Console.Write("QuickSort (randomized) average time is: {0:F2} millisecs\n", quickRandomAvg / numExec);
            }
        }

        private static void compareSelection()
        {
            Console.WriteLine("\n****************** Time Test Selection ******************\n");
            double randomAvg = 0, momAvg = 0;
            int numExec = 0;
            Console.WriteLine("Length,      Median of Medians,      Randomized Selection");
            for (int num = 500000; num <= 50000000; num = num * 2)
            {
                int[] array = new int[num];
                for (int i = 0; i < num; i++)
                    array[i] = rand.Next(num / 10000);

                double timeStart;
                int[] K = new int[(int)Math.Log(num)];
                int lenK = K.Length;
                for (int i = 0; i < lenK; i++)
                    K[i] = rand.Next(num);

                double selTimeMOM = 0, selTimeRandom = 0;
                int[] selArray = new int[num];
                int[] answersMoM = new int[lenK];
                int[] answersRandom = new int[lenK];
                for (int i = 0; i < lenK; i++)
                {

                    for (int j = 0; j < num; j++)
                        selArray[j] = array[j];

                    timeStart = currentTimeMillis();
                    answersMoM[i] = SelectionMofM.select(selArray, 0, selArray.Length - 1, K[i]);
                    selTimeMOM += currentTimeMillis() - timeStart;

                    for (int j = 0; j < num; j++)
                        selArray[j] = array[j];

                    timeStart = currentTimeMillis();
                    answersRandom[i] = SelectionRandom.select(selArray, 0, selArray.Length - 1, K[i]);
                    selTimeRandom += currentTimeMillis() - timeStart;

                }
                Array.Sort(array);
                for (int i = 0; i < lenK; i++)
                {
                    if (answersMoM[i] != array[K[i] - 1])
                        throw new Exception("Code for Median of Medians is incorrect.");
                    if (answersRandom[i] != array[K[i] - 1])
                        throw new Exception("Code for Randomized Selection is incorrect.");
                }
                randomAvg += selTimeRandom;
                momAvg += selTimeMOM;
                numExec += lenK;
                Console.Write("{0,8}, {1,12:##.00}, {2,24:##.00}\n", num, selTimeMOM / lenK, selTimeRandom / lenK);
            }
            Console.Write("\nSelection using median of medians average time is: {0:F2} millisecs\n", momAvg / numExec);
            Console.Write("Selection using random pivot average time is: {0:F2} millisecs\n", randomAvg / numExec);
        }

        private static void testStringSorter()
        {
            Console.WriteLine("\n****************** Time Test String Sorting ******************\n");
            long timeMerge = 0, timeRadix = 0;
            Random rand = new Random((int)(currentTimeMillis()));
            int maxLength = 25;
            foreach (int size in LARGE_STRING_SIZES)
            {
                String[] radixSortStrings = new String[size];
                String[] mergeSortStrings = new String[size];
                for (int i = 0; i < size; i++)
                {
                    int stringLength = 1 + rand.Next() % maxLength;
                    string str = "";
                    for (int j = 0; j < stringLength; j++)
                    {
                        char c = (char)(97 + rand.Next() % 26);
                        str += c;
                    }
                    radixSortStrings[i] = mergeSortStrings[i] = str;
                }

                long startTime = currentTimeMillis();
                new StringMergeSort(mergeSortStrings, size).mergesort();
                timeMerge = currentTimeMillis() - startTime;

                startTime = currentTimeMillis();
                StringSorter.radixSort(radixSortStrings, size);
                timeRadix = currentTimeMillis() - startTime;

                for (int i = 0; i < radixSortStrings.Length - 1; i++)
                {
                    if (mergeSortStrings[i].CompareTo(mergeSortStrings[i + 1]) > 0)
                        throw new Exception("Something wrong with merge sort");
                    if (radixSortStrings[i].CompareTo(radixSortStrings[i + 1]) > 0)
                        throw new Exception("Something wrong with radix sort");
                }

                Console.WriteLine(
        "Merge-sort time for " + size + " strings = " + timeMerge + " (may vary with each execution)");
                Console.WriteLine(
        "Radix-sort time for " + size + " strings = " + timeRadix + " (may vary with each execution)");
                Console.WriteLine();
            }
        }

        public static void Main(String[] args)
        {
            Console.Clear();
            compareSorting();
            compareSelection();
            testStringSorter();
        }
    }
}
