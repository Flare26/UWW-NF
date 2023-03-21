#include "Partition.h"
#include <cmath>
#include <cstdlib>
using namespace std;

#ifndef SELECTIONRANDOM_H_
#define SELECTIONRANDOM_H_

class SelectionRandom {

private:

	static int generateRandomPivot(int *array, int left, int right) {
		int pivotIndex = left + rand() % (right - left + 1);
		return array[pivotIndex];
	}

public:
	static int select(int *arr, int left, int right, int k) { // complete this method
	}
};

#endif /* SELECTIONRANDOM_H_ */
