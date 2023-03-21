using System;
using System.Collections;
using System.Collections.Generic;

namespace _PA2
{
    public class TestCorrectness
    {
        public TestCorrectness()
        {
        }

        private static void printArray(int[] A)
        {
            int len = A.Length;
            if (0 == len)
            {
                Console.Write("[]");
                return;
            }
            Console.Write("[");
            for (int i = 0; i < len - 1; i++)
            {
                Console.Write(A[i] + ", ");
            }
            Console.Write(A[len - 1] + "]");
        }

        private static void printArray(char[] A)
        {
            int len = A.Length;
            if (0 == len)
            {
                Console.Write("[]");
                return;
            }
            Console.Write("[");
            for (int i = 0; i < len - 1; i++)
            {
                Console.Write(A[i] + ", ");
            }
            Console.Write(A[len - 1] + "]");
        }

        private static void printArray(string[] A)
        {
            int len = A.Length;
            if (0 == len)
            {
                Console.Write("[]");
                return;
            }
            Console.Write("[");
            for (int i = 0; i < len - 1; i++)
            {
                Console.Write(A[i] + ", ");
            }
            Console.Write(A[len - 1] + "]");
        }

        private static void printIntervals(List<Interval> intervals)
        {
            if (0 == intervals.Count)
                Console.WriteLine("[]");
            else
            {
                Console.Write("[");
                for (int i = 0; i < intervals.Count - 1; i++)
                    Console.Write(intervals[i] + ", ");

                Console.WriteLine(intervals[intervals.Count - 1] + "]");
            }
        }

        private static void testPartition()
        {
            Console.WriteLine("****************** Improved Partition ******************\n");
            int[] array = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7, 100, 56, 67, 13, 12, 45, 4, 4,
                44, 8, 4, -10, 14, 4, -1, 97, -1009, 4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
            int n = array.Length;
            Console.Write("Original Array: ");
            printArray(array);
            Console.WriteLine();
            int pivot = 4;
            int[] partitionIndex = Partition.partition(array, 0, n - 1, pivot);
            Console.WriteLine("\npivot = " + pivot);
            Console.WriteLine("Lower Partition Index = " + partitionIndex[0]);
            Console.WriteLine("Upper Partition Index = " + partitionIndex[1]);
            Console.Write("\nAfter Partitioning: ");
            printArray(array);
            Console.WriteLine();
        }

        private static void testRandomizedQuickSort()
        {
            Console.WriteLine("\n****************** Randomized Quick-Sort ******************\n");
            int[] array = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7, 100, 56, 67, 13, 12, 45, 4, 4,
                44, 8, 4, -10, 14, 4, -1, 97, -1009, 4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
            int n = array.Length;
            Console.Write("Original Array: ");
            printArray(array);
            QuickSort.quicksortRandom(array, 0, n - 1);
            Console.Write("\nAfter Sorting:  ");
            printArray(array);
            Console.WriteLine();
        }

        private static void testRandomizedSelect()
        {
            Console.WriteLine("\n****************** Randomized Quick-Select ******************\n");
            int[] array = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7, 100, 56, 67, 13, 12, 45, 4, 4,
                44, 8, 4, -10, 14, 4, -1, 97, -1009, 4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
            int n = array.Length;
            Console.Write("Original Array: ");
            printArray(array);
            Console.WriteLine();
            for (int k = 1; k <= n; k++)
                Console.Write("{0}th smallest number is {1}\n", k, SelectionRandom.select(array, 0, n - 1, k));
        }

        private static void testMedianOfMediansSelect()
        {
            Console.WriteLine("\n****************** Median of Medians ******************\n");
            int[] array = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7, 100, 56, 67, 13, 12, 45, 4, 4,
                44, 8, 4, -10, 14, 4, -1, 97, -1009, 4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
            int n = array.Length;
            Console.Write("Original Array: ");
            printArray(array);
            Console.WriteLine();
            for (int k = 1; k <= n; k++)
                Console.Write("{0}th smallest number is {1}\n", k, SelectionMofM.select(array, 0, n - 1, k));
        }

        private static void testStringSorter()
        {

            Console.WriteLine("\n****************** Sorting Strings ******************\n");
            String[] strings = { "abc", "xyzw", "xyzab", "abcdx", "wxcdx", "abcxy", "aac", "wxcdx", "abcx", "abc" };
            Console.Write("Original order of strings: ");
            printArray(strings);
            StringSorter.radixSort(strings, strings.Length);
            Console.Write("\nRadix-sorted order of strings: ");
            printArray(strings);
            Console.WriteLine();
        }

        private static int log2(int n)
        {
            if (n <= 2)
                return 1;
            int x = 1;
            int count = 0;
            while (x < n)
            {
                x = x * 2;
                count++;
            }
            return count;
        }

        private static void printStatistics(HuffmanEncoder huffObj)
        {
            int msgLength = 0;
            int sigma = huffObj.getSigma();
            int[] frequencies = huffObj.getFrequencies();
            char[] alphabet = huffObj.getAlphabet();
            for (int i = 0; i < sigma; i++)
                msgLength += frequencies[i];
            Console.WriteLine("ASCII encoding needs " + msgLength * 8 + " bits.");
            Console.WriteLine(
                    "Fixed length encoding needs " + (msgLength * log2(sigma) + sigma * (8 + log2(sigma))) + " bits.");
            Console.WriteLine("Huffman encoding needs " + (huffObj.getTableSize() + huffObj.getEncodingLength()) + " bits.");
            Console.Write("Huffman Encoding: ");
            char c;
            for (int i = 0; i < sigma - 1; i++)
            {
                c = alphabet[i];
                Console.Write(c + "(" + huffObj.getEncoding(c) + "); ");
            }
            c = alphabet[sigma - 1];
            Console.Write(c + "(" + huffObj.getEncoding(c) + ")");
        }

        private static HuffmanEncoder testHuffmanEncoderHelper(int n, int sigma, double[] probabilities)
        {
            int[] freq = new int[sigma];
            char[] alphabet = new char[sigma];
            for (int i = 0; i < sigma; i++)
            {
                freq[i] = (int)(n * probabilities[i]);
                alphabet[i] = (char)(i + 65);
            }
            Console.Write("alphabet: ");
            printArray(alphabet);
            Console.WriteLine();
            Console.Write("frequencies: ");
            printArray(freq);
            Console.WriteLine("\n");
            HuffmanEncoder huffObj = new HuffmanEncoder(alphabet, freq, sigma);
            printStatistics(huffObj);
            Console.WriteLine("\n");
            return huffObj;
        }

        private static void testHuffmanEncoder()
        {

            Console.WriteLine("\n****************** Huffman Encoding ******************\n");
            int sigma = 8;
            int n = 3280;
            double[] probabilities = { 0.06, 0.2, 0.15, 0.3, 0.05, 0.02, 0.08, 0.14 };
            testHuffmanEncoderHelper(n, sigma, probabilities);

            sigma = 6;
            n = 2000;
            probabilities = new double[] { 0.04, 0.07, 0.14, 0.2, 0.24, 0.31 };
            testHuffmanEncoderHelper(n, sigma, probabilities);

            sigma = 8;
            n = 200000;
            probabilities = new double[] { 0.04, 0.07, 0.12, 0.2, 0.18, 0.26, 0.11, 0.02 };
            testHuffmanEncoderHelper(n, sigma, probabilities);

            sigma = 8;
            n = 200000;
            probabilities = new double[] { 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125 };
            testHuffmanEncoderHelper(n, sigma, probabilities);
        }

        private static void testHuffmanDecoder()
        {
            Console.WriteLine("****************** Huffman Decoding ******************\n");
            String msg = "BCCABBDDAECCBBAEDDCC";
            char[] alphabet = { 'A', 'B', 'C', 'D', 'E' };
            Console.WriteLine("Original Message: " + msg + "\n");

            double[] probabilities = { 0.15, 0.25, 0.3, 0.2, 0.1 };
            int sigma = alphabet.Length;
            HuffmanEncoder huffObj = testHuffmanEncoderHelper(20, sigma, probabilities);

            String encodedMsg = "";
            for (int i = 0; i < msg.Length; i++)
                encodedMsg += huffObj.getEncoding(msg[i]);
            Console.WriteLine("Encoded Message: " + encodedMsg);
            Hashtable encodingToChar = new Hashtable();
            for (int i = 0; i < sigma; i++)
                encodingToChar.Add(huffObj.getEncoding(alphabet[i]), alphabet[i]);
            String decodedMsg = HuffmanDecoder.decode(encodedMsg, encodingToChar);
            Console.WriteLine("Decoded Message: " + decodedMsg);
        }

        private static List<Interval> readIntervals(String fileName)
        {
            List<Interval> intervals = new List<Interval>();
            string line;
            System.IO.StreamReader fileReader =
                new System.IO.StreamReader(fileName);

            String[] tokens;
            while (!fileReader.EndOfStream)
            {
                line = fileReader.ReadLine().Trim();
                tokens = line.Split(' ');
                char name = tokens[0][0];
                int start = Int32.Parse(tokens[1]);
                int end = Int32.Parse(tokens[2]);
                intervals.Add(new Interval(name, start, end));
            }
            fileReader.Close();
            return intervals;
        }

        private static void testIntervalScheduling()
        {
            Console.WriteLine("\n****************** Interval Scheduling ******************\n");
            List<Interval> intervals = readIntervals("intervalScheduling1.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.Write("Selected intervals are: ");
            printIntervals(GreedyIntervals.schedule(intervals));

            Console.WriteLine();
            intervals = readIntervals("intervalScheduling2.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.Write("Selected intervals are: ");
            printIntervals(GreedyIntervals.schedule(intervals));

            Console.WriteLine();
            intervals = readIntervals("intervalScheduling3.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.Write("Selected intervals are: ");
            printIntervals(GreedyIntervals.schedule(intervals));
        }

        private static void testIntervalColoring()
        {
            Console.WriteLine("\n****************** Interval Coloring ******************\n");
            List<Interval> intervals = readIntervals("intervalColoring1.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.WriteLine("Number of colors needed to paint the interval with no overlapping colors: "
                    + GreedyIntervals.color(intervals));

            Console.WriteLine();
            intervals = readIntervals("intervalColoring2.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.WriteLine("Number of colors needed to paint the interval with no overlapping colors: "
                    + GreedyIntervals.color(intervals));

            Console.WriteLine();
            intervals = readIntervals("intervalColoring3.txt");
            Console.Write("The intervals are: ");
            printIntervals(intervals);
            Console.WriteLine("Number of colors needed to paint the interval with no overlapping colors: "
                    + GreedyIntervals.color(intervals));
        }

        public static void Main(String[] args)
        {
            Console.Clear();
            testPartition();
            testRandomizedQuickSort();
            testRandomizedSelect();
            testMedianOfMediansSelect();
            testStringSorter();
            testHuffmanEncoder();
            testHuffmanDecoder();
            testIntervalScheduling();
            testIntervalColoring();
        }
    }
}
