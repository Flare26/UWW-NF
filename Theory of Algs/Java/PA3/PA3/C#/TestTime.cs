using System;
namespace _PA3
{
	public class TestTime
	{
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

		private static void testMaxSumSubarrayHelper(int[] A, int length)
		{
			Console.Write("Array: ");
			printArray(A);
			Console.Write("\n");
			int[] answer = MaxSubarraySum.method1(A, length);
			Console.Write("Maximum sum subarray has sum = {0}. It starts at index = {1} and ends at index = {2}\n",
					answer[0], answer[1], answer[2]);
		}

		private static void testMaxSumSubarray()
		{
			Console.WriteLine("\n****************** Maximum Subarray Sum ******************\n");
			int[] A = { 10, -5, 15, -10, 20, -15 };
			int length = A.Length;
			testMaxSumSubarrayHelper(A, length);

			Console.WriteLine("\n***\n");
			int[] B = { 70, -80, 60, 80, -90, 100, -150, 50 };
			length = B.Length;
			testMaxSumSubarrayHelper(B, length);

			Console.WriteLine("\n***\n");
			int[] C = { 70, -50, 40, -70, 20, 40, -10, 15 };
			length = C.Length;
			testMaxSumSubarrayHelper(C, length);
		}

		private static void loadTestMaxSumSubArray()
		{
			Console.WriteLine("\n*** Method 1 vs Method 2 vs Method 3 ***\n");
			for (int maxArrayLen = 1000; maxArrayLen <= 150000; maxArrayLen = (int)(maxArrayLen * 1.2))
			{
				Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));
				int[] A = new int[maxArrayLen];
				for (int i = 0; i < maxArrayLen; i++)
				{
					A[i] = 2500 - rand.Next() % 5000;
				}
				long startTime_1, startTime_2, startTime_3;
				long timeDiff_1, timeDiff_2, timeDiff_3;
				startTime_1 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
				int[] answer_1 = MaxSubarraySum.method1(A, maxArrayLen);
				timeDiff_1 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - startTime_1;

				startTime_2 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
				int[] answer_2 = MaxSubarraySum.method2(A, maxArrayLen);
				timeDiff_2 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - startTime_2;

				startTime_3 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
				int[] answer_3 = MaxSubarraySum.method3(A, maxArrayLen);
				timeDiff_3 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - startTime_3;

				if (answer_1[0] != answer_2[0] || answer_2[0] != answer_3[0])
					throw new Exception("Okay, we are screwed!");

				Console.Write("Length = {0,6}, Method 1 Time = {1,5}ms, Method 2 Time = {2,2}ms, Method 3 Time = {3}ms\n",
						maxArrayLen, timeDiff_1, timeDiff_2, timeDiff_3);
			}

			Console.WriteLine("\n*** Method 2 vs Method 3 ***\n");
			for (int maxArrayLen = 150000; maxArrayLen <= 50000000; maxArrayLen = (int)(maxArrayLen * 1.2))
			{
				Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));
				int[] A = new int[maxArrayLen];
				for (int i = 0; i < maxArrayLen; i++)
				{
					A[i] = 2500 - rand.Next() % 5000;
				}
				long startTime_2, startTime_3;
				long timeDiff_2, timeDiff_3;

				startTime_2 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
				int[] answer_2 = MaxSubarraySum.method2(A, maxArrayLen);
				timeDiff_2 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - startTime_2;

				startTime_3 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
				int[] answer_3 = MaxSubarraySum.method3(A, maxArrayLen);
				timeDiff_3 = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - startTime_3;

				if (answer_2[0] != answer_3[0])
					throw new Exception("Okay, we are screwed!");

				Console.Write("Length = {0,8}d, Method 2 Time = {1,5}ms, Method 3 Time = {2,3}ms\n",
		maxArrayLen, timeDiff_2, timeDiff_3);
			}
		}

        public static void Main(String[] args)
        {
            testMaxSumSubarray();
            loadTestMaxSumSubArray();
        }
    }
}
