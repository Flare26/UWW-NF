#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

#ifndef MERGESORT_H_
#define MERGESORT_H_

class MergeSort {

private:

	int *array;
	int *mergedArray;
	int n;

public:

	MergeSort(int *array, int n) {
		this->array = array;
		this->n = n;
		this->mergedArray = new int[n];
	}

	~MergeSort() {
		delete[] mergedArray;
	}

	void mergesort() {
		mergesort(0, n - 1);
	}

private:

	void mergesort(int left, int right) {
		if (left == right)
			return;
		int mid = (left + right) / 2;
		mergesort(left, mid);
		mergesort(mid + 1, right);
		int i = left;
		while (i <= right) {
			mergedArray[i] = array[i];
			i++;
		}
		i = left;
		int j = mid + 1, k = left;
		while (i <= mid && j <= right)
			array[k++] =
					mergedArray[j] < mergedArray[i] ?
							mergedArray[j++] : mergedArray[i++];
		while (i <= mid)
			array[k++] = mergedArray[i++];
	}
};

#endif /* MERGESORT_H_ */
