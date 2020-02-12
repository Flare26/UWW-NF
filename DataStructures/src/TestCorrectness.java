public class TestCorrectness {

	public static void main(String[] args) throws Exception {
		int queueSize = 7;
		QueueUsingStack qViaStack = new QueueUsingStack(queueSize);
		Queue queue = new Queue(queueSize);
		System.out.println("**** Enqueue Test ****");
		System.out.println();
		for (int i = 1; i <= 4; i++) {
			int x = i * 5;
			qViaStack.enqueue(x);
			queue.enqueue(x);
			System.out.println("Enqueue " + x);
			System.out.println("Stack implementation: " + qViaStack.toString());
			System.out.println("Standard implementation: " + queue.toString());
			System.out.println();
		}
		System.out.println("**** Dequeue Test ****");
		System.out.println();
		for (int i = 1; i <= 2; i++) {
			System.out.println("Stack implementation: (Dequeued " + qViaStack.dequeue() + ") " + qViaStack.toString());
			System.out.println("Standard implementation: (Dequeued " + queue.dequeue() + ") " + queue.toString());
			System.out.println();
		}
		System.out.println("**** Enqueue Test ****");
		System.out.println();
		for (int i = 1; i <= 5; i++) {
			int x = i * 7;
			qViaStack.enqueue(x);
			queue.enqueue(x);
			System.out.println("Enqueue " + x);
			System.out.println("Stack implementation: " + qViaStack.toString());
			System.out.println("Standard implementation: " + queue.toString());
			System.out.println();
		}
		System.out.println("**** Dequeue Test ****");
		System.out.println();
		for (int i = 1; i <= 7; i++) {
			System.out.println("Stack implementation: (Dequeued " + qViaStack.dequeue() + ") " + qViaStack.toString());
			System.out.println("Standard implementation: (Dequeued " + queue.dequeue() + ") " + queue.toString());
			System.out.println();
		}
	}
}
