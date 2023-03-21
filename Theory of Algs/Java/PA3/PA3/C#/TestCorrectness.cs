using System;
using System.Collections.Generic;
namespace _PA3
{
    public class TestCorrectness
    {
        private const String MIS1_TREE_PATH = "mis1.txt";
        private const String MIS2_TREE_PATH = "mis2.txt";
        private const String MIS3_TREE_PATH = "mis3.txt";
        private const String MIS4_TREE_PATH = "mis4.txt";

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
                if (A[i] == Int32.MaxValue)
                    Console.Write("inf, ");
                else
                    Console.Write(A[i] + ", ");
            }
            if (A[len - 1] == Int32.MaxValue)
                Console.Write("inf]");
            else
                Console.Write(A[len - 1] + "]");
        }

        private static void printList(List<int> A)
        {
            int len = A.Count;
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

        private static void testSubsetSumHelper(int[] elements, int numElements, int[] targets, int numTarget)
        {
            Console.Write("Elements are ");
            printArray(elements);
            List<int> formed = new List<int>();
            List<int> notFormed = new List<int>();
            for (int i = 0; i < numTarget; i++)
            {
                if (SubsetSum.isSumPossible(elements, numElements, targets[i]))
                    formed.Add(targets[i]);
                else
                    notFormed.Add(targets[i]);
            }
            Console.Write("\nCan be formed: ");
            printArray(formed.ToArray());
            Console.Write("\nCannot be formed: ");
            printArray(notFormed.ToArray());
            Console.WriteLine();
        }

        private static void testSubsetSum()
        {
            Console.WriteLine("****************** Subset Sum ******************\n");
            int[] elements = { 3, 34, 4, 12, 5, 2 };
            int[] targets = { 3, 9, 12, 13, 20, 22, 26, 27, 38, 47, 50, 62 };
            testSubsetSumHelper(elements, elements.Length, targets, targets.Length);

            Console.WriteLine("\n***\n");
            elements = new int[] { 15, 22, 14, 26, 32, 9, 16, 8 };
            targets = new int[] { 8, 12, 10, 40, 54, 80, 114, 118, 121, 125, 150 };
            testSubsetSumHelper(elements, elements.Length, targets, targets.Length);

            Console.WriteLine("\n***\n");
            elements = new int[] { 41, 34, 21, 20, 8, 7, 7, 4, 3, 3 };
            targets = new int[] { 1, 4, 8, 9, 16, 22, 28, 50, 89, 122, 138, 139, 148, 150, 183 };
            testSubsetSumHelper(elements, elements.Length, targets, targets.Length);
        }

        private static void testKnapsackHelper(int[] weights, int[] profits, int numElements, int[] W, int numW)
        {
            Console.Write("Weights are ");
            printArray(weights);
            Console.WriteLine();
            Console.Write("Profits are ");
            printArray(profits);
            Console.WriteLine("\n");
            for (int w = 0; w < numW; w++)
                Console.Write("Optimal profit for knapsack of capacity {0,2} is {1,3}\n", W[w],
                        Knapsack01.findOptimalProfit(profits, weights, weights.Length, W[w]));
        }

        private static void testKnapsack()
        {
            Console.WriteLine("\n****************** 0-1 Knapsack ******************\n");
            int[] weights = { 5, 4, 6, 3, 2 };
            int[] profits = { 10, 40, 30, 50, 25 };
            int[] W = { 2, 3, 5, 10, 14, 15, 17, 20 };
            testKnapsackHelper(weights, profits, weights.Length, W, W.Length);

            Console.WriteLine("\n***\n");
            weights = new int[] { 12, 7, 11, 8, 9 };
            profits = new int[] { 24, 13, 23, 15, 16 };
            W = new int[] { 26, 46, 60, 80 };
            testKnapsackHelper(weights, profits, weights.Length, W, W.Length);

            Console.WriteLine("\n***\n");
            weights = new int[] { 23, 31, 29, 44, 53, 38, 63, 85, 89, 82 };
            profits = new int[] { 92, 57, 49, 68, 60, 43, 67, 84, 87, 72 };
            W = new int[] { 165, 310, 400, 410, 800 };
            testKnapsackHelper(weights, profits, weights.Length, W, W.Length);
        }

        private static void testMISHelper(String treePath)
        {
            MWIS mis = new MWIS(treePath);
            int value = mis.computeSum(0);
            mis.computeSet(0);
            Console.WriteLine("Maximum Independent Set has value = " + value);
            List<int> includedNodes = new List<int>();
            for (int i = 0; i < mis.numNodes; i++)
            {
                if (mis.isInSet[i])
                    includedNodes.Add(i);
            }
            Console.Write("The included nodes are: ");
            printArray(includedNodes.ToArray());
            Console.WriteLine();
        }

        private static void testMIS()
        {
            Console.WriteLine("\n****************** Maximum Weighted Independent Set ******************\n");
            Console.WriteLine("*** Tree 1 ***\n");
            testMISHelper(MIS1_TREE_PATH);
            Console.WriteLine("\n*** Tree 2 ***\n");
            testMISHelper(MIS2_TREE_PATH);
            Console.WriteLine("\n*** Tree 3 ***\n");
            testMISHelper(MIS3_TREE_PATH);
            Console.WriteLine("\n*** Tree 4 ***\n");
            testMISHelper(MIS4_TREE_PATH);
        }

        private static void testLCSHelper(String str1, String str2)
        {
            Console.WriteLine("First String: " + str1);
            Console.WriteLine("Another String: " + str2);
            String lcs = LCS.longestCommonSubsequence(str1, str2);
            Console.Write("Longest Common Subsequence is {0} having length {1}\n", lcs, lcs.Length);
        }

        private static void testLCS()
        {
            Console.WriteLine("\n****************** Longest Common Subsequence ******************\n");
            String str1 = "AGGTAB";
            String str2 = "GXTXAYB";
            testLCSHelper(str1, str2);

            Console.WriteLine("\n***\n");
            str1 = "XMJYAUZ";
            str2 = "MZJAWXU";
            testLCSHelper(str1, str2);

            Console.WriteLine("\n***\n");
            str1 = "AAACCGTGAGTTATTCGTTCTAGAA";
            str2 = "CACCCCTAAGGTACCTTTGGTTC";
            testLCSHelper(str1, str2);
        }

        private static void testBinaryString()
        {
            Console.WriteLine("\n****************** Binary Strings ******************\n");
            for(int i = 1; i <= 15; i++)
				Console.WriteLine("Number of {0,2}-length binary strings with no consecutive ones is {1,4}", i,
                        BinaryStrings.numberOfBinaryStringsWithNoConsecutiveOnes(i));
        }

        private static void testLIS()
        {
            Console.WriteLine("\n****************** Longest Increasing Subsequence ******************\n");
            int[] seq0 = { 10, 22, 9, 33, 21, 50, 41, 60, 55, 57 };
            int[] seq1 = { 10, 22, 9, 33, 21, 50, 41, 60, 7 };
            int[] seq2 = { -1, 2, 0, 4, 8, 5, 7, 10, 3 };
            int[] seq3 = { -30, 10, -20, 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
            int[][] sequences = { seq0, seq1, seq2, seq3 };
            for (int i = 0; i < sequences.Length; i++)
            {
                Console.Write("A Longest Increasing Subsequence of ");
                printArray(sequences[i]);
                Console.Write(" is ");
                List<int> lis = LIS.longestIncreasingSequence(sequences[i],
                        sequences[i].Length);
                printList(lis);
                Console.WriteLine();
            }
        }

        public static void Main(String[] args)
        {
            testSubsetSum();
            testKnapsack();
            testMIS();
            testLCS();
            testBinaryString();
            testLIS();
        }
    }
}