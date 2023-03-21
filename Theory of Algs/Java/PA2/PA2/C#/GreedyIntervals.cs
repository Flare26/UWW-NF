using System;
using System.Collections.Generic;
namespace _PA2
{
	class IntegerComparator : IComparer<int>
	{
		public int Compare(int arg1, int arg2)
		{
			return arg1 - arg2;
		}
	}

	public class GreedyIntervals
	{
		private static void sortIntervalsByStartTime(List<Interval> intervals)
		{
			intervals.Sort((arg1, arg2) => arg1.start - arg2.start);
		}

		private static void sortIntervalsByEndTime(List<Interval> intervals)
		{
			intervals.Sort((arg1, arg2) => arg1.end - arg2.end);
		}

		public static List<Interval> schedule(List<Interval> intervals)
		{ // complete this method
		}

		public static int color(List<Interval> intervals)
		{ // complete this method
		}
	}
}
