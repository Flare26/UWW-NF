import java.util.Hashtable;

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
		// at each hash val index rests the root node of a linked list
		int keyHash = getHashValue(key);
		boolean contains = false;
		LinkedList i = hashTable[keyHash];
		ListNode placehold = i.head;
		if (i.size == 0)
			return false;
		
		if ( placehold.value == key )
			return true;
		
		while ( placehold.next != null ) {
			//System.out.println("in search loop");
			if (placehold.next.value == key ) {
				contains = true;
				break;
			}
			placehold = placehold.next;
		}
		return contains;
	}

	public boolean insert(int val) { // complete this method
		//System.out.println("Inserting " + val + " to hashTable");
		boolean isUnique = ! search(val);
		//System.out.println("Val is unique = " + isUnique);
		// if contains is FALSE
		if (isUnique) {
			int hv = getHashValue(val); // hash function
			 hashTable[hv].insertAtEnd(val);
			return true;
		}
		return false;
	}

	public boolean remove(int val) { // complete this method
		LinkedList tmp = hashTable[getHashValue(val)];
		for(ListNode currentNode = tmp.head ; currentNode != null ; currentNode = currentNode.next) {
			
			if (currentNode.value == val) {
				
				tmp.deleteHead();
				return true;
			}
			
		}
		return false;
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
