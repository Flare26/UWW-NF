


public class BSTNode {

	int value, height, subtreeSize;
	BSTNode left, right, parent;

	BSTNode(int val) {
		// a node is always inserted as a leaf
		value = val;
		height = subtreeSize = 1;
		left = null;
		right = null;
		parent = null;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean hasRightChild() {
		return (right != null);
	}

	public boolean hasLeftChild() {
		return (left != null);
	}

	public boolean isLeftChildOfParent() {
		if (isRoot())
			return false;
		else
			return parent.left == this;
	}

	public boolean isRoot() {
		return (parent == null);
	}

	public String toString() {
		if (isRoot())
			return "<" + value + ", null>";
		else {
			return "<" + value + ", " + parent.value + ">";
		}
	}
}