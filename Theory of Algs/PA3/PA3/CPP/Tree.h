#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;

#ifndef TREE_H_
#define TREE_H_

class Tree {

public:
	vector<vector<int> > adjList;
	int *weights;
	int numNodes;

	Tree(const string &filePath) {

		numNodes = 0;
		ifstream fileReader;
		fileReader.open(filePath);
		fileReader >> numNodes;
		weights = new int[numNodes];
		adjList.reserve(numNodes);

		for (int i = 0; i < numNodes; i++) {
			fileReader >> weights[i];
			adjList.push_back(vector<int>());
		}

		for (int i = 0; i < numNodes - 1; i++) {
			int src, dest;
			fileReader >> src;
			fileReader >> dest;
			adjList.at(src).push_back(dest);
		}
		fileReader.close();
	}
};

#endif /* TREE_H_ */
