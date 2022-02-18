#ifndef RADIXSORT_H_
#define RADIXSORT_H_

#include <iostream>
using namespace std;

class RadixSort {
private:
	int *array;
	int n;

public:

	RadixSort(int *array, int length) {
		this->array = array;
		n = length;
	}

private:

	void countSortOnDigits(int A[], int n, int digits[]) { // complete this function
	}

	void radixSortNonNeg(int A[], int n) { // complete this function
	}

public:

	void radixSort() { // complete this function
	}
};

#endif /* RADIXSORT_H_ */
