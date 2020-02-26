package PA3.JAVA;



public class LinkedList {

	// all nodes contain information about the node that comes after but they do not use an array at all
	// All nodes point to the next node! But you cannot go backwards! 
	
	public ListNode head, tail;
	public int size;

	public LinkedList() {
		head = tail = null;
		size = 0;
	}

	public void insertAfter(ListNode argNode, int value) { // complete this function
		ListNode newNode = new ListNode(value); // create a new node to insert into the list
		
		if (argNode == head) {
			ListNode temp = head.next;
			head.next = newNode;
			newNode.next = temp;
			size++;
		} else if ( argNode == tail ) {
			tail.next = newNode; // set old tail pointer to new node
			tail = newNode; // tail to new node
			size++;
		} else {
		ListNode temp = argNode.next;
		newNode.next = temp;
		argNode.next = newNode;
		//  ^the new node will point to what the arg node used to, and the arg will now point to new node.
		size++;
		}
	}

	public void deleteAfter(ListNode argNode) { // complete this function
		if ( tail == argNode ) 
		{
			return; // if arg node is tail do nothing
			
		} else if ( argNode.next == tail ) {
			// if arg node next is tail
			tail = null;
			tail = argNode;
			argNode.next = null;
			size--; // decrement size after delete
		} else {
			ListNode temp = argNode.next.next; // skip ahead
			argNode.next = temp;
			
			size--; // decrement size after delete
		}
	}

	public void selectionSort() { // complete this function
		
		ListNode iNode = head;
		ListNode jNode;
		ListNode minNode; // start at head
		
		while ( iNode.next != null ) {
			minNode = iNode; // move to the next node in the sequence as the min. Check onwards from there.
			jNode = iNode.next;
			
			while (jNode != null ) {
				if (jNode.value < minNode.value)
					minNode = jNode; // goes through the rest of the nodes after the current iNode. Finds next smallest
				jNode = jNode.next; // advance to next node
			}
			// checked reminaing nodes, swap out the current node and increment it
			if ( minNode.value != iNode.value )
			{
				int temp = iNode.value;
				iNode.value = minNode.value;
				minNode.value = temp;
			}
			iNode = iNode.next;
		}
		
	}

	public boolean removeDuplicatesSorted() { // complete this function
		if ( head == null )
			return false;
		
		// check order
		ListNode placehold = head;
		for (int i = 1; i < size; i++) {
			// have i = 1 to ignore the last node but still compare it!
			if ( placehold.value > placehold.next.value)
			{
				return false;
			}
			placehold = placehold.next;
		}//after this, garunteed sorted
		//System.out.println("LIST IS SORTED! :)");
		//reset the placeholder node to the beginning
		
		ListNode currentNode = head;
		
		while ( currentNode.next != null ) {
			 
			//while garuntees that .next.vale will exist
			
			if ( currentNode.value == currentNode.next.value ) {
				// duplicate found current = next so delete one after current and bridge the pointer
				ListNode deletionBridge = currentNode.next.next; // will be current links to after deletion
				deleteAfter(currentNode);
				currentNode.next = deletionBridge; // re-establish the bridge
			} else {
				// if no deletion was necessary advance placeholder
				currentNode = currentNode.next;
			}
			
		}
		
		return true;
		
		
	}

	public void pushOddIndexesToTheBack() { // complete this function
		// Use % operator, if % 2 comes out nicely it's even. if not its odd
		//System.out.println("size = " + size);
		
		
		ListNode placeholder = head;
		for ( int i = 0 ; i < size - 1 ; i++ ) {
				if (i % 2 == 0 ) {
					int val = placeholder.next.value;
					insertAtEnd(val);
					deleteAfter(placeholder);
				}
			
		}
	}

	ListNode insertAtFront(int value) {
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

	ListNode insertAtEnd(int value) {
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

	void deleteHead() {
		if (0 == size) {
			System.out.println("Cannot delete from an empty list");
		} else if (1 == size) {
			head = tail = null;
			size--;
		} else {
			size--;
			ListNode tmp = head;
			head = head.next;
			tmp.next = null;
			tmp = null;
		}
	}

	public ListNode getNodeAt(int pos) {
		if (pos < 0 || pos >= size || 0 == size) {
			System.out.println("No such position exists");
			return null;
		} else {
			ListNode tmp = head;
			for (int i = 0; i < pos; i++)
				tmp = tmp.next;
			return tmp;
		}
	}

	void printList() {
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

	public int getSize() {
		return size;
	}
}
