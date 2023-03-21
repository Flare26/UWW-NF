#include <iostream>
#include <algorithm>
#include <climits>
#include <string>
#include <vector>
#include "Tree.h"
using namespace std;

#ifndef MWIS_H_
#define MWIS_H_

class MWIS: public Tree {

public:

	int *computedSum;
	bool *isIncludedSumLarger;
	bool *isInSet;

	MWIS(const string &filePath) :
			Tree(filePath) {

		computedSum = new int[numNodes];
		isIncludedSumLarger = new bool[numNodes];
		isInSet = new bool[numNodes];
		for (int i = 0; i < numNodes; i++) {
			computedSum[i] = INT_MIN;
			isIncludedSumLarger[i] = false;
			isInSet[i] = false;
		}
	}

	int computeSum(int node) { // complete this function
	}

	void computeSet(int root) { // complete this function
	}

private:

	void computeSetHelper(int node, int parent) { // complete this function
	}
};

#endif /* MWIS_H_ */
