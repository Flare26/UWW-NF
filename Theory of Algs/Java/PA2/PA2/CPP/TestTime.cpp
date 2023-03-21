#include <iostream>
#include <vector>
#include <string>
#include <cmath>

#include "SelectionMofM.h"
#include "QuickSort.h"
#include "SelectionRandom.h"
#include "StringSorter.h"
#include "MergeSort.h"
#include "StringMergeSort.h"

using namespace std;

const int CLOCKS_PER_MILLISEC = CLOCKS_PER_SEC / 1000;

const static int LARGE_STRING_SIZES[] = { 1000000, 5000000, 10000000, 15000000,
		20000000, 25000000 };

void testIfSorted(int A[], int len, char s) {
	for (int i = 0; i < len - 1; i++)
		if (A[i] > A[i + 1]) {
			if (s == 'M')
				throw("MergeSort code is incorrect");
			else if (s == '3')
				throw("QuickSort (median of 3) code is incorrect");
			else if (s == 'Q')
				throw("QuickSort (randomized) code is incorrect");
			else if (s == 'R')
				throw("RadixSort code is incorrect");
		}
}

static void compareSorting() {
	cout << "****************** Time Test Sorting ******************" << endl;
	double mergeAvg = 0, quickRandomAvg = 0;
	int numExec = 0;
	string duplicateText[] = { "Very Few Duplicates", "Few Duplicates",
			"More Duplicates", "Even More Duplicates", "Plenty of Duplicates" };
	for (int duplicate = 10, k = 0; duplicate <= 100000; duplicate *= 10, k++) {
		cout << "\n**** " + duplicateText[k] + " ****\n" << endl;
		cout << "Length,         MergeSort,         Randomized Quick-Sort"
				<< endl;
		for (int num = 500000; num <= 50000000; num *= 2) {
			int *array = new int[num];
			int *temp = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = rand() % (num / duplicate);

			double timeStart, timeEnd;

			for (int i = 0; i < num; i++)
				temp[i] = array[i];

			timeStart = clock();
			MergeSort(array, num).mergesort();
			timeEnd = clock();

			testIfSorted(array, num, 'M');
			mergeAvg += (timeEnd - timeStart);
			printf("%8d,        %7.2f", num,
					(timeEnd - timeStart) / CLOCKS_PER_MILLISEC);

			for (int i = 0; i < num; i++)
				temp[i] = array[i];

			timeStart = clock();
			QuickSort().quicksortRandom(temp, 0, num - 1);
			timeEnd = clock();

			testIfSorted(temp, num, 'Q');
			quickRandomAvg += (timeEnd - timeStart);
			printf(",             %7.2f\n",
					(timeEnd - timeStart) / CLOCKS_PER_MILLISEC);

			numExec++;
		}
		printf("\nMergeSort average time is: %.2f millisecs\n",
				mergeAvg / (numExec * CLOCKS_PER_MILLISEC));
		printf("QuickSort (randomized) average time is: %.2f millisecs\n",
				quickRandomAvg / (numExec * CLOCKS_PER_MILLISEC));
	}
}

static void compareSelection() {
	cout << "\n****************** Time Test Selection ******************\n";
	double randomAvg = 0, momAvg = 0;
	int numExec = 0;
	cout << "\nLength,      Median of Medians,      Randomized Selection"
			<< endl;
	for (int num = 500000; num <= 50000000; num *= 2) {
		int *array = new int[num];
		for (int i = 0; i < num; i++)
			array[i] = rand() % (num / 10000);

		double timeStart;
		int lenK = (int) log(num);
		int *K = new int[lenK];
		for (int i = 0; i < lenK; i++)
			K[i] = rand() % num;

		double selTimeMOM = 0, selTimeRandom = 0;
		int *selArray = new int[num];
		int *answersMoM = new int[lenK];
		int *answersRandom = new int[lenK];
		for (int i = 0; i < lenK; i++) {

			for (int j = 0; j < num; j++)
				selArray[j] = array[j];

			timeStart = clock();
			answersMoM[i] = SelectionMofM::select(selArray, 0, num - 1, K[i]);
			selTimeMOM += clock() - timeStart;

			for (int j = 0; j < num; j++)
				selArray[j] = array[j];

			timeStart = clock();
			answersRandom[i] = SelectionRandom::select(selArray, 0, num - 1,
					K[i]);
			selTimeRandom += clock() - timeStart;

		}
		std::sort(array, array + num);
		for (int i = 0; i < lenK; i++) {
			if (answersMoM[i] != array[K[i] - 1])
				throw("Code for Median of Medians is incorrect.");
			if (answersRandom[i] != array[K[i] - 1])
				throw("Code for Randomized Selection is incorrect.");
		}
		delete[] answersMoM;
		delete[] answersRandom;
		delete[] selArray;
		randomAvg += selTimeRandom / CLOCKS_PER_MILLISEC;
		momAvg += selTimeMOM / CLOCKS_PER_MILLISEC;
		numExec += lenK;
		printf("%8d, %12.2f, %24.2f\n", num,
				selTimeMOM / (CLOCKS_PER_MILLISEC * lenK),
				selTimeRandom / (CLOCKS_PER_MILLISEC * lenK));
	}
	printf(
			"\nSelection using median of medians average time is: %.2f millisecs\n",
			momAvg / numExec);
	printf("Selection using random pivot average time is: %.2f millisecs\n",
			randomAvg / numExec);
}

static void testStringSorter() {
	cout << "\n****************** Time Test String Sorting ******************\n"
			<< endl;
	long timeMerge = 0, timeRadix = 0;
	int maxLength = 25;
	for (int r = 0; r < sizeof(LARGE_STRING_SIZES) / sizeof(int); r++) {
		int size = LARGE_STRING_SIZES[r];
		string **radixSortStrings = new string*[size];
		string **mergeSortStrings = new string*[size];
		for (int i = 0; i < size; i++) {
			int stringLength = 1 + rand() % maxLength;
			string str = "";
			for (int j = 0; j < stringLength; j++) {
				char c = 97 + (rand() % 26);
				str += c;
			}
			radixSortStrings[i] = mergeSortStrings[i] = &str;
		}

		long startTime = clock();
		StringMergeSort(mergeSortStrings, size).mergesort();
		timeMerge = clock() - startTime;

		startTime = clock();
		StringSorter().radixSort(radixSortStrings, size);
		timeRadix = clock() - startTime;

		for (int i = 0; i < size - 1; i++) {
			if (mergeSortStrings[i]->compare(*mergeSortStrings[i + 1]) > 0)
				throw("Something wrong with merge sort");
			if (radixSortStrings[i]->compare(*radixSortStrings[i + 1]) > 0)
				throw("Something wrong with radix sort");
		}
		delete[] radixSortStrings;
		delete[] mergeSortStrings;
		cout << "Merge-sort time for " << size << " strings = "
				<< timeMerge / CLOCKS_PER_MILLISEC
				<< " (may vary with each execution)" << endl;
		cout << "Radix-sort time for " << size << " strings = "
				<< timeRadix / CLOCKS_PER_MILLISEC
				<< " (may vary with each execution)" << endl << endl;
	}
}

int main() {
	compareSorting();
	compareSelection();
	testStringSorter();
	return 1;
}
