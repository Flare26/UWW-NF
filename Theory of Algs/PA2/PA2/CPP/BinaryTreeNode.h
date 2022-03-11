#ifndef BINARYTREENODE_H_
#define BINARYTREENODE_H_

class BinaryTreeNode {

public:

	char c;
	int value;
	BinaryTreeNode *left, *right;

	BinaryTreeNode(char c, int val) {
		this->c = c;
		value = val;
		left = nullptr;
		right = nullptr;
	}
};

#endif /* BINARYTREENODE_H_ */
