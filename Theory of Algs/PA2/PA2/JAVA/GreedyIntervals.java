

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

	public static ArrayList<Interval> schedule(List<Interval> intervals) { // complete this method
	}

	public static int color(List<Interval> intervals) { // complete this method
	}
}
