

public class Hashing {

	LinkedList hashTable[];
	int TABLE_SIZE;

	public Hashing(int tableSize) {
		TABLE_SIZE = tableSize;
		hashTable = new LinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			hashTable[i] = new LinkedList();
	}

	protected int getHashValue(int val) {
		return (37 * val + 61) % TABLE_SIZE;
	}

	public boolean search(int key) { // complete this method
	}

	public boolean insert(int val) { // complete this method
	}

	public boolean remove(int val) { // complete this method
	}

	public void printStatistics() {
		int maxSize = hashTable[0].size;
		int minSize = maxSize, total = maxSize;
		for (int i = 1; i < TABLE_SIZE; i++) {
			int size = hashTable[i].size;
			if (size > maxSize)
				maxSize = size;
			else if (size < minSize)
				minSize = size;
			total += size;
		}
		System.out.printf(
				"Max length of a chain = %d%n" + "Min length of a chain = %d%n" + "Avg length of chains = %d%n",
				maxSize, minSize, total / TABLE_SIZE);
	}
}
