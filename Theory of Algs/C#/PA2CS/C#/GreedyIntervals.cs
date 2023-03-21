using System;
using System.Collections.Generic;
namespace _PA2
{

    using System;
    using System.Collections.Generic;

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
        {
            List<Interval> result = new List<Interval>();

            if (intervals == null || intervals.Count == 0)
            {
                return result;
            }

            sortIntervalsByEndTime(intervals);

            Interval current = intervals[0];
            result.Add(current);

            for (int i = 1; i < intervals.Count; i++)
            {
                if (intervals[i].start >= current.end)
                {
                    current = intervals[i];
                    result.Add(current);
                }
            }

            return result;
        }

        public static int color(List<Interval> intervals)
        {
            if (intervals == null || intervals.Count == 0)
            {
                return 0;
            }

            // sort the intervals by their start time
            sortIntervalsByStartTime(intervals);

            PriorityQueue<int> PQ = new PriorityQueue<int>(new IntegerComparator());

            int colcount = 1;

            // add the end time of the first interval to the priority queue
            PQ.add(intervals[0].end);

            for (int i = 1; i < intervals.Count; i++)
            {
                // if the current interval in the sorted list starts after the smallest end time among all intervals in the priority queue
                if (intervals[i].start >= PQ.peek())
                {
                    // remove the topmost entry in the priority queue
                    PQ.poll();
                }
                else
                {
                    // increment the counter
                    colcount++;
                }

                // insert the endtime of the current interval into the priority queue
                PQ.add(intervals[i].end);
            }

            return colcount;
        }


    /*class IntegerComparator : IComparer<int>
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
	}*/
}
