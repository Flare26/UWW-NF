#include <iostream>
#include <vector>
#include <string>

#include "SubsetSum.h"
#include "Knapsack01.h"
#include "MaxSubarraySum.h"
#include "MWIS.h"
#include "LCS.h"
#include "BinaryStrings.h"
#include "LIS.h"

using namespace std;

const string MIS1_TREE_PATH = "mis1.txt";
const string MIS2_TREE_PATH = "mis2.txt";
const string MIS3_TREE_PATH = "mis3.txt";
const string MIS4_TREE_PATH = "mis4.txt";

static void printArray(int *A, int len) {
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

static void printVector(vector<int> &vect) {
	if (vect.size() == 0) {
		cout << "[]";
		return;
	}
	cout << "[";
	for (int i = 0; i < vect.size() - 1; i++) {
		cout << vect.at(i) << ", ";
	}
	cout << vect.at(vect.size() - 1) << "]";
}

static void testSubsetSumHelper(int elements[], int numElements, int targets[],
		int numTarget) {
	cout << "Elements are ";
	printArray(elements, numElements);
	SubsetSum dyn;
	vector<int> formed;
	vector<int> notFormed;
	for (int i = 0; i < numTarget; i++) {
		if (dyn.isSumPossible(elements, numElements, targets[i]))
			formed.push_back(targets[i]);
		else
			notFormed.push_back(targets[i]);
	}
	cout << endl << "Can be formed: ";
	printVector(formed);
	cout << endl << "Cannot be formed: ";
	printVector(notFormed);
	cout << endl;
}

static void testSubsetSum() {
	cout << "****************** Subset Sum ******************\n" << endl;
	int elements1[] = { 3, 34, 4, 12, 5, 2 };
	int targets1[] = { 3, 9, 12, 13, 20, 22, 26, 27, 38, 47, 50, 62 };
	testSubsetSumHelper(elements1, sizeof(elements1) / sizeof(int), targets1,
			sizeof(targets1) / sizeof(int));

	cout << "\n***\n" << endl;
	int elements2[] = { 15, 22, 14, 26, 32, 9, 16, 8 };
	int targets2[] = { 8, 12, 10, 40, 54, 80, 114, 118, 121, 125, 150 };
	testSubsetSumHelper(elements2, sizeof(elements2) / sizeof(int), targets2,
			sizeof(targets2) / sizeof(int));

	cout << "\n***\n" << endl;
	int elements3[] = { 41, 34, 21, 20, 8, 7, 7, 4, 3, 3 };
	int targets3[] = { 1, 4, 8, 9, 16, 22, 28, 50, 89, 122, 138, 139, 148, 150,
			183 };
	testSubsetSumHelper(elements3, sizeof(elements3) / sizeof(int), targets3,
			sizeof(targets3) / sizeof(int));
}

static void testKnapsackHelper(int weights[], int profits[], int numElements,
		int W[], int numW) {

	cout << "Weights are ";
	printArray(weights, numElements);
	cout << endl << "Profits are ";
	printArray(profits, numElements);
	cout << endl << endl;
	Knapsack01 dyn;
	for (int w = 0; w < numW; w++) {
		int opt = dyn.findOptimalProfit(profits, weights, numElements, W[w]);
		printf("Optimal profit for knapsack of capacity %2d is %3d\n", W[w],
				opt);
	}
}

static void testKnapsack() {
	cout << "\n****************** 0-1 Knapsack ******************\n" << endl;
	int weights1[] = { 5, 4, 6, 3, 2 };
	int profits1[] = { 10, 40, 30, 50, 25 };
	int W1[] = { 2, 3, 5, 10, 14, 15, 17, 20 };
	testKnapsackHelper(weights1, profits1, sizeof(weights1) / sizeof(int), W1,
			sizeof(W1) / sizeof(int));

	cout << "\n***\n" << endl;
	int weights2[] = { 12, 7, 11, 8, 9 };
	int profits2[] = { 24, 13, 23, 15, 16 };
	int W2[] = { 26, 46, 60, 80 };
	testKnapsackHelper(weights2, profits2, sizeof(weights2) / sizeof(int), W2,
			sizeof(W2) / sizeof(int));

	cout << "\n***\n" << endl;
	int weights3[] = { 23, 31, 29, 44, 53, 38, 63, 85, 89, 82 };
	int profits3[] = { 92, 57, 49, 68, 60, 43, 67, 84, 87, 72 };
	int W3[] = { 165, 310, 400, 410, 800 };
	testKnapsackHelper(weights3, profits3, sizeof(weights3) / sizeof(int), W3,
			sizeof(W3) / sizeof(int));
}

static void testMISHelper(string treePath) {
	vector<int> includedNodes;
	MWIS mis(treePath);
	int value = mis.computeSum(0);
	mis.computeSet(0);
	for (int i = 0; i < mis.numNodes; i++) {
		if (mis.isInSet[i])
			includedNodes.push_back(i);
	}
	cout << "Maximum Independent Set has value = " << value << endl;
	cout << "The included nodes are: ";
	printVector(includedNodes);
	cout << endl;
}

static void testMIS() {
	cout
			<< "\n****************** Maximum Weighted Independent Set ******************\n"
			<< endl;
	cout << "*** Tree 1 ***\n" << endl;
	testMISHelper(MIS1_TREE_PATH);
	cout << "\n*** Tree 2 ***\n" << endl;
	testMISHelper(MIS2_TREE_PATH);
	cout << "\n*** Tree 3 ***\n" << endl;
	testMISHelper(MIS3_TREE_PATH);
	cout << "\n*** Tree 4 ***\n" << endl;
	testMISHelper(MIS4_TREE_PATH);
}

static void testLCSHelper(string &str1, string &str2) {
	cout << "First String: " << str1 << endl;
	cout << "Another String: " << str2 << endl;
	LCS lcs;
	const string &answer = lcs.longestCommonSubsequence(str1, str2);
	cout << "Longest Common Subsequence is " << answer << " having length "
			<< answer.length() << endl;
}

static void testLCS() {
	cout
			<< "\n****************** Longest Common Subsequence ******************\n"
			<< endl;
	string str1 = "AGGTAB";
	string str2 = "GXTXAYB";
	testLCSHelper(str1, str2);

	cout << "\n***\n" << endl;
	str1 = "XMJYAUZ";
	str2 = "MZJAWXU";
	testLCSHelper(str1, str2);

	cout << "\n***\n" << endl;
	str1 = "AAACCGTGAGTTATTCGTTCTAGAA";
	str2 = "CACCCCTAAGGTACCTTTGGTTC";
	testLCSHelper(str1, str2);
}

static void testBinaryString() {
	cout << endl << "****************** Binary Strings ******************"
			<< endl << endl;
	BinaryStrings bs;
	for (int i = 1; i <= 15; i++)
		printf(
				"Number of %2d-length binary strings with no consecutive ones is %4d\n",
				i, bs.numberOfBinaryStringsWithNoConsecutiveOnes(i));

}
static void testLIS() {
	cout << endl
			<< "****************** Longest Increasing Subsequence ******************"
			<< endl << endl;
	int seq0[] = { 10, 22, 9, 33, 21, 50, 41, 60, 55, 57 };
	int seq1[] = { 10, 22, 9, 33, 21, 50, 41, 60, 7 };
	int seq2[] = { -1, 2, 0, 4, 8, 5, 7, 10, 3 };
	int seq3[] = { -30, 10, -20, 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11,
			7, 15 };
	int *sequences[4] = { seq0, seq1, seq2, seq3 };
	int lengths[] = { sizeof(seq0) / sizeof(int), sizeof(seq1) / sizeof(int),
			sizeof(seq2) / sizeof(int), sizeof(seq3) / sizeof(int) };

	LIS lisObj;
	for (int i = 0; i < 4; i++) {
		cout << "A Longest Increasing Subsequence of ";
		printArray(sequences[i], lengths[i]);
		cout << " is ";
		vector<int> lis = lisObj.longestIncreasingSubsequence(sequences[i],
				lengths[i]);
		printVector(lis);
		cout << endl;
	}
}

int main() {
	testSubsetSum();
	testKnapsack();
	testMIS();
	testLCS();
	testBinaryString();
	testLIS();
	return 1;
}
