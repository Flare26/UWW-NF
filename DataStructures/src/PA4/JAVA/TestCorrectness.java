

import java.util.Arrays;

public class TestCorrectness {

	final static int insertionArray[] = { 11, 12, 15, 17, 12, 19, 4, 5, 11, 19, 20, 32, 77, 65, 66, 88, 99, 10, 8, 19,
			15, 66, 11, 19 };
	static int numInsert = insertionArray.length;

	final static int searchArray[] = { 29, 3, 19, 27, 12, 34, 4, 5, 19, 20, 32, 45, 37, 25, 99, 25, 8, 24, 12, 16 };
	static int numSearch = searchArray.length;

	final static int deleteArray[] = { 16, 12, 15, 5, 17, 19, 4, 5, 19, 20, 32, 17, 19, 39, 99, 10, 8, 19, 15, 21 };
	static int numDelete = deleteArray.length;

	public static void testHashingCorrectness() throws Exception {

		System.out.println("****************** Test Hashing Correctness ******************\n");
		int TABLE_SIZE = 5;
		Hashing hChain = new Hashing(TABLE_SIZE);

		System.out.println("Inserting the following numbers: " + Arrays.toString(insertionArray));

		for (int i = 0; i < numInsert; i++) {
			hChain.insert(insertionArray[i]);
		}

		System.out.println("\n*** Hash Table Structure (after insertion) ***");
		int size = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			System.out.print("Slot " + i + ": ");
			hChain.hashTable[i].printList();
			size += hChain.hashTable[i].getSize();
		}
		System.out.println("\nSize of hash table: " + size);

		System.out.println("\n*** Searching Hash Table ***");
		LinkedList foundList = new LinkedList();
		LinkedList notFoundList = new LinkedList();

		for (int i = 0; i < numSearch; i++) {
			int val = searchArray[i];
			if (hChain.search(val))
				foundList.insertAtEnd(val);
			else
				notFoundList.insertAtEnd(val);
		}
		System.out.print("Found: ");
		foundList.printList();
		System.out.print("Did not find: ");
		notFoundList.printList();

		System.out.print("\n*** Deleting Hash Table ***");

		LinkedList deleteList = new LinkedList();
		notFoundList = new LinkedList();
		System.out.println();
		for (int i = 0; i < numDelete; i++) {
			int val = deleteArray[i];
			if (hChain.remove(val))
				deleteList.insertAtEnd(val);
			else
				notFoundList.insertAtEnd(val);
		}
		System.out.print("Deleted: ");
		deleteList.printList();
		System.out.print("Did not find: ");
		notFoundList.printList();

		System.out.println("\n*** Hash Table Structure (after deletion) ***");
		size = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			System.out.print("Slot " + i + ": ");
			hChain.hashTable[i].printList();
			size += hChain.hashTable[i].getSize();
		}
		System.out.println("\nSize of hash table: " + size);
	}

	private static void testBSTInsertAndSearch() {
		System.out.println("****************** Test BST Correctness ******************\n");

		BST bst = new BST();

		System.out.println("Inserting the following numbers: " + Arrays.toString(insertionArray));

		for (int i = 0; i < numInsert; i++) {
			bst.insertAndSet(insertionArray[i]);
		}

		System.out.println("\n*** BST Structure (after insertion) ***");
		bst.print();
		System.out.println("\n\nSize of BST: " + bst.getSize());

		System.out.println("\n*** Searching BST ***");
		LinkedList foundList = new LinkedList();
		LinkedList notFoundList = new LinkedList();

		for (int i = 0; i < numSearch; i++) {
			int val = searchArray[i];
			if (bst.search(val) != null)
				foundList.insertAtEnd(val);
			else
				notFoundList.insertAtEnd(val);
		}
		System.out.print("Found: ");
		foundList.printList();
		System.out.print("Did not find: ");
		notFoundList.printList();

		System.out.print("\n*** Deleting BST ***");

		LinkedList deleteList = new LinkedList();
		notFoundList = new LinkedList();
		System.out.println();
		for (int i = 0; i < numDelete; i++) {
			int val = deleteArray[i];
			if (bst.remove(val))
				deleteList.insertAtEnd(val);
			else
				notFoundList.insertAtEnd(val);
		}
		System.out.print("Deleted: ");
		deleteList.printList();
		System.out.print("Did not find: ");
		notFoundList.printList();

		System.out.println("\n*** BST Structure (after deletion) ***");
		bst.print();
		System.out.println("\n\nSize of BST: " + bst.getSize());
	}

	private static void testBSTApplications() throws Exception {
		System.out.println(
				"****************** Test Predecessor, Successor, Nearest Neighbour, and Range Counting Correctness ******************\n");
		int testArray[] = { 25, 17, 10, 23, 36, 34, 48, 40, 54 };
		System.out.println("Numbers in BST: " + Arrays.toString(testArray));
		System.out.println();
		BSTApplications nr = new BSTApplications(testArray);
		int KEY[] = { 10, 54, 34, 9, 57, 44, 47, 43 };
		for (int key : KEY) {
			BSTNode succNode = nr.getSuccessor(key);
			BSTNode predNode = nr.getPredecessor(key);
			int successor = succNode == null ? Integer.MAX_VALUE : succNode.value;
			int predecessor = predNode == null ? Integer.MIN_VALUE : predNode.value;
			System.out.print("key = " + key + ": ");
			if (predecessor == Integer.MIN_VALUE)
				System.out.print("predecessor = -infty; ");
			else
				System.out.print("predecessor = " + predecessor + "; ");
			if (successor == Integer.MAX_VALUE)
				System.out.print("successor = infty; ");
			else
				System.out.print("successor = " + successor + "; ");
			System.out.println("nearest neighbour = " + nr.nearestNeighbour(key));
		}
		// nr.bst.printBST();

		int L[] = { 15, 5, 5, 10, 55, 54, 54, 34, 48, 10, 22, -8, 10, 39, 25 };
		int R[] = { 14, 9, 10, 10, 57, 57, 54, 34, 48, 54, 40, 23, 26, 57, 41 };
		for (int i = 0; i < L.length; i++) {
			System.out.println();
			int l = L[i], r = R[i];
			BSTNode lca = nr.getLCA(l, r);
			System.out.println("LCA of " + l + " and " + r + " is " + (lca == null ? "null" : lca.value));
			System.out.println("Range Count for [" + l + ", " + r + "] = " + nr.rangeCount(l, r));
		}
	}

	public static void main(String[] args) throws Exception {

		testHashingCorrectness();
		System.out.println();
		testBSTInsertAndSearch();
		System.out.println();
		testBSTApplications();
	}
}
