

public class BST {

	protected BSTNode root;
	protected int size;

	public BST() {
		root = null;
		size = 0;
	}

	public BSTNode search(int key) { // complete this method
	}

	private BSTNode insert(int val) { // complete this method
	}

	public boolean remove(int val) {
		BSTNode delNode = search(val);
		if (delNode == null) {
			return false;
		} else if (delNode.hasLeftChild() && delNode.hasRightChild()) {
			// CASE 3: delNode has both left and right child
			BSTNode maxNodeInLeftSubTree = findMax(delNode.left);
			delNode.value = maxNodeInLeftSubTree.value;
			// reduced to case 1 or 2
			return deleteAtMostOneChild(maxNodeInLeftSubTree);
		} else {
			return deleteAtMostOneChild(delNode);
		}
	}
    
    public boolean insertAndSet(int val) {
        BSTNode newNode = insert(val);
        if (null == newNode)
            return false;
        while (newNode.parent != null) {
            setHeightAndSubtreeSize(newNode);
            newNode = newNode.parent;
        }
        return true;
    }

	public int getHeight() {
		return getHeight(root);
	}

	public BSTNode findMin() {
		return findMin(root);
	}

	public BSTNode findMax() {
		return findMax(root);
	}

	public static BSTNode findMin(BSTNode node) {
		if (null == node)
			return null;
		while (node.hasLeftChild()) {
			node = node.left;
		}
		return node;
	}

	public static BSTNode findMax(BSTNode node) {
		if (null == node)
			return null;
		while (node.hasRightChild()) {
			node = node.right;
		}
		return node;
	}

	public static int getHeight(BSTNode node) {
		if (node == null)
			return 0;
		else
			return node.height;
	}
	
	public int getSize() {
		return size;
	}

	public static int getSubtreeSize(BSTNode node) {
		if (node == null)
			return 0;
		else
			return node.subtreeSize;
	}

	public static void setHeightAndSubtreeSize(BSTNode node) {
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		node.subtreeSize = 1 + getSubtreeSize(node.left) + getSubtreeSize(node.right);
	}

	private boolean deleteAtMostOneChild(BSTNode delNode) { // delNode must have at most 1 child
		BSTNode parentOfDelNode = delNode.parent;
		if (delNode.isRoot()) {
			if (delNode.hasLeftChild()) {
				delNode.left.parent = null;
				root = delNode.left;
			} else if (delNode.hasRightChild()) {
				delNode.right.parent = null;
				root = delNode.right;
			} else {
				root = null;
			}
		} else if (delNode.isLeaf()) {
			// CASE 1: delNode is a leaf node
			if (delNode.isLeftChildOfParent())
				parentOfDelNode.left = null;
			else
				parentOfDelNode.right = null;
		} else {
			// CASE 2: delNode only has a left or a right child
			BSTNode correctChild;
			if (delNode.hasLeftChild())
				correctChild = delNode.left;
			else
				correctChild = delNode.right;
			if (delNode.isLeftChildOfParent())
				parentOfDelNode.left = correctChild;
			else
				parentOfDelNode.right = correctChild;
			correctChild.parent = parentOfDelNode;
		}
		while (parentOfDelNode != null) {
			setHeightAndSubtreeSize(parentOfDelNode);
			parentOfDelNode = parentOfDelNode.parent;
		}
		delNode = null;
		size--;
		return true;
	}

	private void print(BSTNode node) {
		if (null != node) {
			System.out.print(node.toString() + " ");
			print(node.left);
			print(node.right);
		}
	}

	public void print() {
		print(root);
	}
}
