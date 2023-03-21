#include "Interval.h"
#include "PriorityQueue.h"
#include <vector>
using namespace std;

#ifndef GREEDYINTERVALS_H_
#define GREEDYINTERVALS_H_

class GreedyIntervals {

private:

	struct sort_by_start {
		bool operator()(const Interval &arg1, const Interval &arg2) const {
			return arg1.start <= arg2.start;
		}
	};

	struct sort_by_end {
		bool operator()(const Interval &arg1, const Interval &arg2) const {
			return arg1.end <= arg2.end;
		}
	};

	struct compare_int {
		int operator()(const int &arg1, const int &arg2) const {
			return arg1 - arg2;
		}
	};

	static void sortIntervalsByStartTime(vector<Interval> &intervals) {
		sort(intervals.begin(), intervals.end(), sort_by_start());
	}

	static void sortIntervalsByEndTime(vector<Interval> &intervals) {
		sort(intervals.begin(), intervals.end(), sort_by_end());
	}

public:

	static vector<Interval> schedule(vector<Interval> &intervals) { // complete this method
	}

	static int color(vector<Interval> &intervals) { // complete this method
	}
};

#endif /* GREEDYINTERVALS_H_ */
