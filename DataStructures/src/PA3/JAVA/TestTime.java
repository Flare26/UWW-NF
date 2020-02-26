package PA3.JAVA;


import java.util.Random;

public class TestTime {

	public static void testTime() {
		System.out.println("****************** Time Test Dynamic Array vs LinkedList ****************** \n");
		double insTimeDA = 0, insTimeLL = 0, delTimeDA = 0, delTimeLL = 0, scanTimeDA = 0, scanTimeLL = 0,
				randAccessTimeDA = 0, randAccessTimeLL = 0;
		long totalInsDelScan = 0, totalRandAccess = 0;
		int numRandAccess = 1000;
		Random rand = new Random(System.currentTimeMillis());
		int round = 0;
		for (int num = 1000000; num <= 50000000; num *= 1.3) {
			DynamicArray da = new DynamicArray(1);
			LinkedList ll = new LinkedList();

			long start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.insertAtEnd(i);
			}
			insTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				ll.insertAtFront(i);
			}
			insTimeLL += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.access(i);
			}
			scanTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			ListNode tmp = ll.head;
			for (int i = 0; i < num; i++) {
				tmp = tmp.next;
			}
			scanTimeLL += (System.currentTimeMillis() - start);

			int[] randAccessIndexes = new int[numRandAccess];
			for (int j = 0; j < numRandAccess; j++)
				randAccessIndexes[j] = rand.nextInt(da.getSize());

			start = System.currentTimeMillis();
			for (int j = 0; j < numRandAccess; j++)
				da.access(randAccessIndexes[j]);
			randAccessTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int j = 0; j < numRandAccess; j++)
				ll.getNodeAt(randAccessIndexes[j]);
			randAccessTimeLL += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.deleteLast();
			}
			delTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				ll.deleteHead();
			}
			delTimeLL += (System.currentTimeMillis() - start);

			System.out.println("Round " + round + " Completed");
			round++;
			totalInsDelScan += num;
			totalRandAccess += numRandAccess;
		}
		System.out.println("\nTotal number of insertions, scans, and deletions (each) = " + totalInsDelScan);
		System.out.println("Total number of random accesses = " + totalRandAccess);
		System.out.printf("%nInsertion time of dynamic array = %.2fms%n", insTimeDA);
		System.out.printf("Insertion time of linked list = %.2fms%n", insTimeLL);
		System.out.println();
		System.out.printf("Scan time of dynamic array = %.2fms%n", scanTimeDA);
		System.out.printf("Scan time of linked list = %.2fms%n", scanTimeLL);
		System.out.println();
		System.out.printf("Deletion time of dynamic array = %.2fms%n", delTimeDA);
		System.out.printf("Deletion time of linked list = %.2fms%n", delTimeLL);
		System.out.println();
		System.out.printf("Random access time of dynamic array = %.2fms%n", randAccessTimeDA);
		System.out.printf("Random access time of linked list = %.2fms%n", randAccessTimeLL);
	}

	public static void main(String[] args) throws Exception {
		testTime();
	}
}
