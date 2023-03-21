

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class IntegerComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer arg1, Integer arg2) {
		return arg1 - arg2;
	}
}

public class GreedyIntervals {

	private static void sortIntervalsByStartTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.start - arg2.start);
	}

	private static void sortIntervalsByEndTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.end - arg2.end);
	}

	public static ArrayList<Interval> schedule(List<Interval> intervals) {
		// first sort intervals by end time
		sortIntervalsByEndTime(intervals);
		ArrayList<Interval> optimal = new ArrayList<Interval>();
		
		// add first of the sorted verison to optimal. This is the one that ends earliest
		
		optimal.add(intervals.get(0));
		Interval lastadded = intervals.get(0); // initialize this
		//re sort by start time
		sortIntervalsByStartTime(intervals);
		
		// if an interval starts after the end time of the last added, then add to list
		for (Interval i : intervals)
		{
			if (i.start > lastadded.end)
			{
				optimal.add(i);
				lastadded = i;
			}
		}
				
		return optimal; // complete this method
	}

	public static int color(List<Interval> intervals) {
		// first sort by start time
		sortIntervalsByStartTime(intervals);
		int ctotal = 0; // default to 0
		PriorityQueue<Integer> pq = new PriorityQueue<>(new IntegerComparator()); // priority queue of end times
		pq.add(intervals.get(0).end); // adds end time of first of the sorted intervals to pq
		
		for (Interval i : intervals)
		{
			if (i.start > pq.peek())
			{
				// if true then existing color can be used
				int p = pq.poll(); // returns and removes top priority item
				
			}else
			{
				ctotal++;
			}
			
			pq.add(i.end);
		}
		return ctotal; // complete this method
	}
}
