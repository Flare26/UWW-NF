public class TestTime {

	public static void main(String[] args) throws Exception {
		for (int maxSize = 10000; maxSize <= 50000; maxSize += 10000) {

			QueueUsingStack qViaStack = new QueueUsingStack(maxSize);
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < maxSize; i++) {
				qViaStack.enqueue(i);
			}
			for (int i = 0; i < maxSize; i++) {
				qViaStack.dequeue();
			}
			long endTime = System.currentTimeMillis();
			System.out.printf("Time taken for %d enqueue/dequeue operations, when using a stack implementation: %d\n",
					2 * maxSize, (endTime - startTime));
		}
		System.out.println();

		for (int maxSize = 1000000; maxSize <= 5000000; maxSize += 1000000) {

			Queue queue = new Queue(maxSize);
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < maxSize; i++) {
				queue.enqueue(i);
			}
			for (int i = 0; i < maxSize; i++) {
				queue.dequeue();
			}
			long endTime = System.currentTimeMillis();
			System.out.printf("Time taken for %d enqueue/dequeue operations, when using an array implementation: %d\n",
					2 * maxSize, (endTime - startTime));
		}
	}
}
