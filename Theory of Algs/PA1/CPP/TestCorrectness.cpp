#include "InversionCounting.h"
#include "MergeSort.h"
#include "QuickSort.h"
#include "RadixSort.h"
#include "Selection.h"
#include "IntegerComparator.h"

using namespace std;

void printArray(int *A, int len) {
	if (0 == len) {
		cout << "[]";
		return;
	}
	cout << "[";
	for (int i = 0; i < len - 1; i++) {
		cout << A[i] << ", ";
	}
	cout << A[len - 1] << "]";
}

void testSorting(int *array, int n) {
	int *temp = new int[n];

	cout << "Original array:                  ";
	printArray(array, n);
	cout << endl;

	for (int i = 0; i < n; i++)
		temp[i] = array[i];
	cout << "MergeSorted array:               ";
	MergeSort<int>(temp, n).mergesort(IntegerComparator());
	printArray(temp, n);
	cout << endl;

	for (int i = 0; i < n; i++)
		temp[i] = array[i];
	cout << "QuickSorted (median of 3) array: ";
	QuickSort(temp, n).quicksortMedianOf3();
	printArray(temp, n);
	cout << endl;

	for (int i = 0; i < n; i++)
		temp[i] = array[i];
	cout << "QuickSorted (random) array:      ";
	QuickSort(temp, n).quicksortRandom();
	printArray(temp, n);
	cout << endl;

	for (int i = 0; i < n; i++)
		temp[i] = array[i];
	cout << "RadixSorted array:               ";
	RadixSort(temp, n).radixSort();
	printArray(temp, n);
	cout << endl;
}

void testSelection(int *array, int n) {
	int *mergeArray = new int[n];
	for (int i = 0; i < n; i++)
		mergeArray[i] = array[i];
	MergeSort<int>(mergeArray, n).mergesort(IntegerComparator());

	int *selArray = new int[n];
	for (int k = 1; k <= n; k++) {
		for (int j = 0; j < n; j++)
			selArray[j] = array[j];
		Selection *selection = new Selection(selArray, n);
		int answer = selection->select(k);
		cout << k << "th smallest: " << answer;
		if (answer != mergeArray[k - 1])
			cout << "; Selection code is incorrect for k = " << k << endl;
		cout << endl;
		delete selection;
	}
	delete[] selArray;
	delete[] mergeArray;
}

void testInversions(int *array, int n) {
	cout << "Array is: ";
	printArray(array, n);
	InversionCounting invCount(array, n);
	cout << endl << "Number of inversions is: " << invCount.countInversions()
			<< endl;
}

int main() {
	srand(clock());
	cout << "*** Correctness Test ***\n" << endl;
	int sorting[] = { 19, 1, 12, 100, 7, 8, 4, -10, 14, -1, 97, -1009, 4210 };
	int n = sizeof(sorting) / sizeof(int);
	int *selection = new int[n];
	for (int i = 0; i < n; i++)
		selection[i] = sorting[i];

	testSorting(sorting, n);
	cout << endl;

	testSelection(selection, n);
	delete[] selection;

	cout << endl;
	testInversions(sorting, n);
}
