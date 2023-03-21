#include <iostream>
#include <time.h>
#include "MaxSubarraySum.h"
using namespace std;

const int CLOCKS_PER_MILLISEC = CLOCKS_PER_SEC / 1000;

static void printArray2(int *A, int len) {
	if (0 == len)
		cout << "null";
	else {
		cout << "[";
		for (int i = 0; i < len - 1; i++) {
			if (A[i] == INT_MAX)
				cout << "infty, ";
			else
				cout << A[i] << ", ";
		}
		if (A[len - 1] == INT_MAX)
			cout << "infty]";
		else
			cout << A[len - 1] << "]";
	}
}

static void testMaxSumSubarrayHelper(int A[], int length) {
	cout << "Array: ";
	printArray2(A, length);
	int *answer = MaxSubarraySum().method3(A, length);
	printf(
			"\nMaximum sum subarray has sum = %d. It starts at index = %d and ends at index = %d\n",
			answer[0], answer[1], answer[2]);
}

static void testMaxSumSubarray() {
	cout << "\n****************** Maximum Subarray Sum ******************\n"
			<< endl;
	int A[] = { 10, -5, 15, -10, 20, -15 };
	int length = sizeof(A) / sizeof(int);
	testMaxSumSubarrayHelper(A, length);

	cout << "\n***\n" << endl;
	int B[] = { 70, -80, 60, 80, -90, 100, -150, 50 };
	length = sizeof(B) / sizeof(int);
	testMaxSumSubarrayHelper(B, length);

	cout << "\n***\n" << endl;
	int C[] = { 70, -50, 40, -70, 20, 40, -10, 15 };
	length = sizeof(C) / sizeof(int);
	testMaxSumSubarrayHelper(C, length);
}

static void loadTestMaxSumSubArray() {

	MaxSubarraySum testObj;
	cout << "\n*** Method 1 vs Method 2 vs Method 3 ***\n"
			<< endl;
	for (int maxArrayLen = 1000; maxArrayLen <= 150000;
			maxArrayLen = (int) (maxArrayLen * 1.2)) {
		int *A = new int[maxArrayLen];
		for (int i = 0; i < maxArrayLen; i++) {
			A[i] = 2500 - rand() % 5000;
		}
		double startTime_1, startTime_2, startTime_3;
		double timeDiff_1, timeDiff_2, timeDiff_3;
		startTime_1 = clock();
		int *answer_1 = testObj.method1(A, maxArrayLen);
		timeDiff_1 = clock() - startTime_1;

		startTime_2 = clock();
		int *answer_2 = testObj.method2(A, maxArrayLen);
		timeDiff_2 = clock() - startTime_2;

		startTime_3 = clock();
		int *answer_3 = testObj.method3(A, maxArrayLen);
		timeDiff_3 = clock() - startTime_3;

		if (answer_1[0] != answer_2[0] || answer_2[0] != answer_3[0])
			throw("Okay, we are screwed!");

		printf(
				"Length = %6d, Method 1 Time = %8.2fms, Method 2 Time = %5.2fms, Method 3 Time = %.2fms\n",
				maxArrayLen, timeDiff_1 / CLOCKS_PER_MILLISEC,
				timeDiff_2 / CLOCKS_PER_MILLISEC,
				timeDiff_3 / CLOCKS_PER_MILLISEC);
		delete[] A;
	}

	cout << "\n*** Method 2 vs Method 3 ***\n" << endl;
	for (int maxArrayLen = 150000; maxArrayLen <= 50000000; maxArrayLen =
			(int) (maxArrayLen * 1.2)) {
		int *A = new int[maxArrayLen];
		for (int i = 0; i < maxArrayLen; i++) {
			A[i] = 2500 - rand() % 5000;
		}
		double startTime_2, startTime_3;
		double timeDiff_2, timeDiff_3;

		startTime_2 = clock();
		int *answer_2 = testObj.method2(A, maxArrayLen);
		timeDiff_2 = clock() - startTime_2;

		startTime_3 = clock();
		int *answer_3 = testObj.method3(A, maxArrayLen);
		timeDiff_3 = clock() - startTime_3;

		if (answer_2[0] != answer_3[0])
			throw("Okay, we are screwed!");

		printf(
				"Length = %8d, Method 2 Time = %7.2fms, Method 3 Time = %5.2fms\n",
				maxArrayLen, timeDiff_2 / CLOCKS_PER_MILLISEC,
				timeDiff_3 / CLOCKS_PER_MILLISEC);
		delete[] A;
	}
}

int main() {
	srand(clock());
	testMaxSumSubarray();
	loadTestMaxSumSubArray();
	return 1;
}
