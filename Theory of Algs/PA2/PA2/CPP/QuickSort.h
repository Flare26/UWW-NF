#include "Partition.h"
#include <cstdlib>
using namespace std;

#ifndef QUICKSORT_H_
#define QUICKSORT_H_

class QuickSort {

public:
	static void quicksortRandom(int *array, int left, int right) {
		if (left <= right) {
			int pivot = array[left + rand() % (right - left + 1)];
			int *partitionIndex = Partition::partition(array, left, right, pivot);
			int lowerPartitionIndex = partitionIndex[0];
			int upperPartitionIndex = partitionIndex[1];
			delete[] partitionIndex;
			quicksortRandom(array, left, lowerPartitionIndex - 1);
			quicksortRandom(array, upperPartitionIndex + 1, right);
		}
	}
};

#endif /* QUICKSORT_H_ */
