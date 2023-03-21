#include <unordered_map>
#include <string>
#include "PriorityQueue.h"
#include "BinaryTreeNode.h"

#ifndef HUFFMANENCODER_H_
#define HUFFMANENCODER_H_

struct BinaryTreeNodeComparator {
	int operator()(const BinaryTreeNode *arg1,
			const BinaryTreeNode *arg2) const {
		return arg1->value - arg2->value;
	}
};

class HuffmanEncoder {

	char *alphabet;
	int *frequencies;
	int sigma;
	int encodingLength;
	int tableSize;
	unordered_map<char, string> charToEncodingMapping;

public:

	HuffmanEncoder(char *alphabet, int *frequencies, int sigma) {
		this->alphabet = alphabet;
		this->sigma = sigma;
		this->frequencies = frequencies;
		encodingLength = tableSize = 0;
		encode();
	}

private:

	void encode() { // complete this method
	}

	BinaryTreeNode *buildTree() { // complete this method
	}

	void createTable(BinaryTreeNode *node, string encoding) { // complete this method
	}

public:

	string getEncoding(char c) {
		return charToEncodingMapping[c];
	}

	int getSigma() {
		return sigma;
	}

	int *getFrequencies() {
		return frequencies;
	}

	char *getAlphabet() {
		return alphabet;
	}

	int getTableSize() {
		return tableSize;
	}

	int getEncodingLength() {
		return encodingLength;
	}
};

#endif /* HUFFMANENCODER_H_ */
