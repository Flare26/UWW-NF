

public class Queue {

	private int maxQueueSize, front, currentSize;
	private int[] queue;

	public Queue(int maxQueueSize) throws Exception {
		if (maxQueueSize <= 0)
			throw new Exception("Queue size should be a positive integer.");
		this.maxQueueSize = maxQueueSize;
		front = 0;
		currentSize = 0;
		queue = new int[maxQueueSize];
	}

	public void enqueue(int val) throws Exception {
		if (currentSize == maxQueueSize)
			throw new Exception("Failed to enqueue! Queue is full.");
		else {
			int rear = (front + currentSize) % maxQueueSize;
			this.queue[rear] = val;
			currentSize++;
		}
	}

	public int dequeue() throws Exception {
		if (currentSize == 0)
			throw new Exception("Failed to dequeue! Queue is empty.");
		else {
			currentSize--;
			int x = this.queue[front++];
			if (front == maxQueueSize)
				front = 0;
			return x;
		}
	}

	public int size() {
		return currentSize;
	}

	public String toString() {
		if (size() == 0)
			return "[]";
		else {
			int rear = (front + currentSize) % maxQueueSize;
			String output = "[";
			if (rear > front) {
				for (int i = front; i < rear - 1; i++)
					output += queue[i] + ", ";
				output += queue[rear - 1] + "]";
			} else {
				for (int i = front; i < maxQueueSize - 1; i++)
					output += queue[i] + ", ";
				output += queue[maxQueueSize - 1];

				for (int i = 0; i < rear; i++)
					output += ", " + queue[i];
				output += "]";
			}
			return output;
		}
	}
}