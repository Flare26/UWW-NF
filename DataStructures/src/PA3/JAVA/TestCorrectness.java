package PA3.JAVA;


public class TestCorrectness {

	private static LinkedList part1LinkedList() {
		
		LinkedList list = new LinkedList();

		list.insertAtFront(5);
		System.out.print("Inserting 5 at front. Current list: ");
		list.printList();

		list.insertAtEnd(32);
		System.out.print("Inserting 32 at end. Current list: ");
		list.printList();

		list.insertAtFront(16);
		System.out.print("Inserting 16 at front. Current list: ");
		list.printList();

		list.insertAfter(list.getNodeAt(1), 61);
		System.out.print("Inserting 61 after position 1. Current list: ");
		list.printList();

		list.insertAfter(list.tail, 99);
		System.out.print("Inserting 99 after tail. Current list: ");
		list.printList();

		list.deleteAfter(list.getNodeAt(1));
		System.out.print("Deleting after position 1. Current list: ");
		list.printList();

		list.deleteAfter(list.getNodeAt(2));
		System.out.print("Deleting after position 2. Current list: ");
		list.printList();

		System.out.print("Trying to delete after position 2: ");
		list.deleteAfter(list.getNodeAt(2));

		list.insertAtFront(5);
		System.out.print("Inserting 5 at front. Current list: ");
		list.printList();

		list.insertAtEnd(32);
		System.out.print("Inserting 32 at end. Current list: ");
		list.printList();

		list.insertAtFront(16);
		System.out.print("Inserting 16 at front. Current list: ");
		list.printList();

		list.insertAtFront(8);
		System.out.print("Inserting 8 at front. Current list: ");
		list.printList();

		list.insertAtEnd(21);
		System.out.print("Inserting 21 at end. Current list: ");
		list.printList();

		list.insertAtEnd(50);
		System.out.print("Inserting 50 at end. Current list: ");
		list.printList();

		list.insertAtEnd(32);
		System.out.print("Inserting 32 at end. Current list: ");
		list.printList();

		list.insertAtFront(66);
		System.out.print("Inserting 66 at front. Current list: ");
		list.printList();

		list.insertAtFront(66);
		System.out.print("Inserting 66 at front. Current list: ");
		list.printList();

		return list;
	}

	private static void part2LinkedList(LinkedList list) {
		System.out.print("Trying to remove duplicates from: ");
		if (!list.removeDuplicatesSorted())
			System.out.println("Cannot remove duplicates unless sorted!");
		else
			System.out.println("You have messed up something!");

		System.out.print("Sorted List: ");
		list.selectionSort();
		list.printList();

		System.out.print("Remove duplicates: ");
		if (list.removeDuplicatesSorted())
			list.printList();
		else
			System.out.println("You have messed up something!");

		System.out.print("Push odd indexes to the back: ");
		list.pushOddIndexesToTheBack();
		list.printList();

		list.insertAtFront(-12);
		System.out.print("Inserting -12 at front. Current list: ");
		list.printList();

		System.out.print("Push odd indexes to the back: ");
		list.pushOddIndexesToTheBack();
		list.printList();
	}

	private static void testLinkedListCorrectness() {
		System.out.println("****************** Test Linked List Correctness ****************** \n");
		System.out.println("*** Testing Part 1 ***\n");
		LinkedList list = part1LinkedList();

		System.out.println("\n*** Testing Part 2 ***\n");
		part2LinkedList(list);
	}

	private static void testDynamicArrayCorrectness() {
		DynamicArray da = new DynamicArray(1);
		System.out.println("****************** Test Dynamic Array Correctness ****************** \n");
		for (int i = 0; i < 17; i++) {
			da.insertAtEnd(i * 5);
			System.out.print(da.toString());
			System.out.println(" Num elements: " + da.numElements + ", Length: " + da.length);
		}
		System.out.println();
		for (int i = 0; i < 17; i++) {
			da.deleteLast();
			System.out.print(da.toString());
			System.out.println(" Num elements: " + da.numElements + ", Length: " + da.length);
		}
	}

	public static void main(String[] args) throws Exception {

		testLinkedListCorrectness();
		System.out.println();
		testDynamicArrayCorrectness();
	}
}
