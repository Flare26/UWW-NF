#ifndef QUICKSORT_H_
#define QUICKSORT_H_

#include "Partition.h"

class QuickSort: public Partition {

public:

	QuickSort(int *array, int n) :
			Partition(array, n) {
	}

	void quicksortMedianOf3() {
		quicksortMedianOf3(0, n - 1);
	}

	void quicksortRandom() {
		quicksortRandom(0, n - 1);
	}

private:

	void quicksortMedianOf3(int left, int right) { // complete this function
	}

	void quicksortRandom(int left, int right) { // complete this function
	}
};

#endif /* QUICKSORT_H_ */
