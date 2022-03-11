#include <iostream>
#include <vector>
#include <string>
#include <fstream>

#include "Partition.h"
#include "QuickSort.h"
#include "SelectionRandom.h"
#include "SelectionMofM.h"
#include "Interval.h"
#include "StringSorter.h"
#include "HuffmanEncoder.h"
#include "HuffmanDecoder.h"
#include "GreedyIntervals.h"

using namespace std;

static void printArray(char *A, int len) {
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

static void printArray(int *A, int len) {
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

static void printArray(string **A, int len) {
	if (0 == len)
		cout << "[]" << endl;
	else {
		cout << "[";
		for (int i = 0; i < len - 1; i++)
			cout << *A[i] << ", ";

		cout << *A[len - 1] << "]";
	}
}

static void printIntervals(vector<Interval> intervals) {
	if (0 == intervals.size())
		cout << "[]" << endl;
	else {
		cout << "[";
		for (int i = 0; i < intervals.size() - 1; i++)
			cout << intervals[i].toString() << ", ";

		cout << intervals[intervals.size() - 1].toString() << "]";
	}
}

static void testPartition() {
	cout << "****************** Improved Partition ******************\n"
			<< endl;
	int array[] = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7,
			100, 56, 67, 13, 12, 45, 4, 4, 44, 8, 4, -10, 14, 4, -1, 97, -1009,
			4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
	int n = sizeof(array) / sizeof(int);
	cout << "Original Array: ";
	printArray(array, n);
	cout << endl;
	int pivot = 4;
	int *partitionIndex = Partition::partition(array, 0, n - 1, pivot);
	cout << "\npivot = " << pivot << endl;
	cout << "Lower Partition Index = " << partitionIndex[0];
	cout << endl << "Upper Partition Index = " << partitionIndex[1];
	cout << endl << "\nAfter Partitioning: ";
	printArray(array, n);
	cout << endl;
}

static void testRandomizedQuickSort() {
	cout << "\n****************** Randomized Quick-Sort ******************\n"
			<< endl;
	int array[] = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7,
			100, 56, 67, 13, 12, 45, 4, 4, 44, 8, 4, -10, 14, 4, -1, 97, -1009,
			4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
	int n = sizeof(array) / sizeof(int);
	cout << "Original Array: ";
	printArray(array, n);
	cout << endl;
	QuickSort::quicksortRandom(array, 0, n - 1);
	cout << "After Sorting:  ";
	printArray(array, n);
	cout << endl;
}

static void testRandomizedSelect() {
	cout << "\n****************** Randomized Quick-Select ******************\n"
			<< endl;
	int array[] = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7,
			100, 56, 67, 13, 12, 45, 4, 4, 44, 8, 4, -10, 14, 4, -1, 97, -1009,
			4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
	int n = sizeof(array) / sizeof(int);
	cout << "Original Array: ";
	printArray(array, n);
	cout << endl;
	for (int k = 1; k <= n; k++)
		printf("%dth smallest number is %d\n", k,
				SelectionRandom::select(array, 0, n - 1, k));
}

static void testMedianOfMediansSelect() {
	cout << "\n****************** Median of Medians ******************\n"
			<< endl;
	int array[] = { -13, -174, 19, 1, 4, 12, 100, 7, 4, 4, 10, 12, 4, 5, 6, 7,
			100, 56, 67, 13, 12, 45, 4, 4, 44, 8, 4, -10, 14, 4, -1, 97, -1009,
			4210, 4, 4, 1, 9, 17, 45, 4, -99, -845, -90, -9, 13, -13 };
	int n = sizeof(array) / sizeof(int);
	cout << "Original Array: ";
	printArray(array, n);
	cout << endl;
	for (int k = 1; k <= n; k++)
		printf("%dth smallest number is %d\n", k,
				SelectionMofM::select(array, 0, n - 1, k));
}

static void testStringSorter() {
	cout << "\n****************** Sorting Strings ******************\n" << endl;
	string strings[] = { "abc", "xyzw", "xyzab", "abcdx", "wxcdx", "abcxy",
			"aac", "wxcdx", "abcx", "abc" };
	int n = 10;
	string **stringsToBeSorted = new string*[n];
	for (int i = 0; i < n; i++)
		stringsToBeSorted[i] = &strings[i];
	cout << "Original order of strings: ";
	printArray(stringsToBeSorted, n);
	cout << endl;
	StringSorter().radixSort(stringsToBeSorted, n);
	cout << "Radix-sorted order of strings: ";
	printArray(stringsToBeSorted, n);
}

static int log2(int n) {
	if (n <= 2)
		return 1;
	int x = 1;
	int count = 0;
	while (x < n) {
		x = x * 2;
		count++;
	}
	return count;
}

static void printStatistics(HuffmanEncoder *huffObj) {
	int sigma = huffObj->getSigma();
	int *frequencies = huffObj->getFrequencies();
	char *alphabet = huffObj->getAlphabet();
	int msgLength = 0;
	for (int i = 0; i < sigma; i++)
		msgLength += frequencies[i];
	printf("ASCII encoding needs %d bits.\n", msgLength * 8);
	printf("Fixed length encoding needs %d bits.\n",
			msgLength * log2(sigma) + sigma * (8 + log2(sigma)));
	printf("Huffman encoding needs %d bits.\n",
			(huffObj->getEncodingLength() + huffObj->getTableSize()));
	printf("Huffman Encoding: ");
	char c;
	for (int i = 0; i < sigma - 1; i++) {
		c = alphabet[i];
		cout << c << "(" << huffObj->getEncoding(c) << "); ";
	}
	c = alphabet[sigma - 1];
	cout << c << "(" << huffObj->getEncoding(c) << ")";
}

static HuffmanEncoder *testHuffmanEncoderHelper(int n, int sigma,
		double *probabilities) {
	int *freq = new int[sigma];
	char *alphabet = new char[sigma];
	for (int i = 0; i < sigma; i++) {
		freq[i] = (int) (n * probabilities[i]);
		alphabet[i] = (char) (i + 65);
	}
	cout << "alphabet: ";
	printArray(alphabet, sigma);
	cout << endl;
	cout << "frequencies: ";
	printArray(freq, sigma);
	cout << endl;
	cout << endl;

	HuffmanEncoder *huffObj = new HuffmanEncoder(alphabet, freq, sigma);
	printStatistics(huffObj);
	cout << endl << endl;
	return huffObj;
}

static void testHuffmanEncoder() {
	cout << "\n\n****************** Huffman Encoding ******************\n"
			<< endl;
	int sigma = 8;
	int n = 3280;
	double probabilities[] = { 0.06, 0.2, 0.15, 0.3, 0.05, 0.02, 0.08, 0.14 };
	delete testHuffmanEncoderHelper(n, sigma, probabilities);

	sigma = 6;
	n = 2000;
	double probabilities2[] = { 0.04, 0.07, 0.14, 0.2, 0.24, 0.31 };
	delete testHuffmanEncoderHelper(n, sigma, probabilities2);

	sigma = 8;
	n = 200000;
	double probabilities3[] = { 0.04, 0.07, 0.12, 0.2, 0.18, 0.26, 0.11, 0.02 };
	delete testHuffmanEncoderHelper(n, sigma, probabilities3);

	sigma = 8;
	n = 200000;
	double probabilities4[] = { 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125,
			0.125 };
	delete testHuffmanEncoderHelper(n, sigma, probabilities4);
}

static void testHuffmanDecoder() {
	cout << "****************** Huffman Decoding ******************\n" << endl;
	string msg = "BCCABBDDAECCBBAEDDCC";
	cout << "Original Message: " << msg << endl << endl;
	char alphabet[] = { 'A', 'B', 'C', 'D', 'E' };
	double probabilities[] = { 0.15, 0.25, 0.3, 0.2, 0.1 };
	int sigma = sizeof(alphabet) / sizeof(char);
	HuffmanEncoder *huffObj = testHuffmanEncoderHelper(20, sigma,
			probabilities);

	string encodedMsg = "";
	for (int i = 0; i < msg.length(); i++)
		encodedMsg += huffObj->getEncoding(msg.at(i));
	cout << "Encoded Message: " << encodedMsg << endl;
	unordered_map<string, char> encodingToChar;
	for (int i = 0; i < sigma; i++)
		encodingToChar[huffObj->getEncoding(alphabet[i])] = alphabet[i];
	HuffmanDecoder huffDecoder;
	string decodedMsg = huffDecoder.decode(encodedMsg, encodingToChar);
	cout << "Decoded Message: " << decodedMsg << endl;
	delete huffObj;
}

static vector<Interval> readIntervals(string fileName) {
	ifstream fileReader(fileName, ios::in);
	vector<Interval> intervals;

	while (!fileReader.eof()) {
		char name;
		int start, end;
		fileReader >> name;
		fileReader >> start;
		fileReader >> end;
		intervals.push_back(Interval(name, start, end));
	}
	fileReader.close();
	return intervals;
}

static void testIntervalScheduling() {
	cout << "\n****************** Interval Scheduling ******************\n"
			<< endl;
	vector<Interval> intervals = readIntervals("intervalScheduling1.txt");
	cout << "The intervals are: ";
	printIntervals(intervals);
	cout << endl << "Selected intervals are: ";
	vector<Interval> schedule = GreedyIntervals::schedule(intervals);
	printIntervals(schedule);

	cout << endl;
	cout << endl;
	intervals = readIntervals("intervalScheduling2.txt");
	cout << "The intervals are: ";
	printIntervals(intervals);
	cout << endl << "Selected intervals are: ";
	schedule = GreedyIntervals::schedule(intervals);
	printIntervals(schedule);

	cout << endl;
	cout << endl;
	intervals = readIntervals("intervalScheduling3.txt");
	cout << "The intervals are: ";
	printIntervals(intervals);
	cout << endl << "Selected intervals are: ";
	schedule = GreedyIntervals::schedule(intervals);
	printIntervals(schedule);
	cout << endl;
}

static void testIntervalColoring() {
	cout << "\n****************** Interval Coloring ******************\n"
			<< endl;
	vector<Interval> intervals = readIntervals("intervalColoring1.txt");
	cout << "The intervals are: ";
	printIntervals(intervals);
	cout
			<< "\nNumber of colors needed to paint the interval with no overlapping colors: "
			<< GreedyIntervals::color(intervals) << endl;

	intervals = readIntervals("intervalColoring2.txt");
	cout << endl << "The intervals are: ";
	printIntervals(intervals);
	cout
			<< "\nNumber of colors needed to paint the interval with no overlapping colors: "
			<< GreedyIntervals::color(intervals) << endl;

	intervals = readIntervals("intervalColoring3.txt");
	cout << endl << "The intervals are: ";
	printIntervals(intervals);
	cout
			<< "\nNumber of colors needed to paint the interval with no overlapping colors: "
			<< GreedyIntervals::color(intervals) << endl;
}

int main() {
	testPartition();
	testRandomizedQuickSort();
	testRandomizedSelect();
	testMedianOfMediansSelect();
	testStringSorter();
	testHuffmanEncoder();
	testHuffmanDecoder();
	testIntervalScheduling();
	testIntervalColoring();
}
