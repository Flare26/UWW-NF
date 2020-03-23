public class LinkedList {

	private ListNode head, tail;
	private int size;
	private int mergedArray[];

	public LinkedList() {
		head = tail = null;
		size = 0;
	}

	public void mergesort() {
		mergedArray = new int[size()];
		mergesort(0, size() - 1, head, tail);
	}

	private void mergesort(int left, int right, ListNode leftNode, ListNode rightNode) { // complete this function
	}

	public ListNode insertAtFront(int value) {
		ListNode newNode = new ListNode(value);
		if (size == 0) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
		return newNode;
	}

	public ListNode insertAtEnd(int value) {
		ListNode newNode = new ListNode(value);
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return newNode;
	}

	public void printList() {
		if (size == 0)
			System.out.println("[]");
		else {
			ListNode tmp = head;
			String output = "[";
			for (int i = 0; i < size - 1; i++) {
				output += tmp.value + " -> ";
				tmp = tmp.next;
			}
			output += tail.value + "]";
			System.out.println(output);
		}
	}

	public int size() {
		return size;
	}
}
