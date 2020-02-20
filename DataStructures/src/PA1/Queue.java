//nathan frazier
public class Queue {
    
    private int maxQueueSize, front, rear, currentSize;
    private int[] queue;
    
    public Queue(int maxQueueSize) {
        if (maxQueueSize <= 0)
            System.out.println("Queue size should be a positive integer.");
        else {
            this.maxQueueSize = maxQueueSize;
            front = rear = 0;
            currentSize = 0;
            queue = new int[maxQueueSize];
        }
    }
    
    public void enqueue(int val) { // complete this function
    	if ( currentSize == maxQueueSize ) {
    		System.out.println("Cannot queue!");
    	} else {
    		queue[rear] = val;
    		rear++;
    		currentSize++;
    		if (rear == maxQueueSize)
    			rear = 0; //reset the rear when end is reached
    		
    	}
    }
    
    public int dequeue() { // complete this function
    	int val = -1;
    	if (currentSize == 0) {
    		System.out.println("Queue already empty!");
    	} else {
    		val = queue[front];
    		front++;
    		currentSize--;
    		if (front == maxQueueSize) {
    			front = 0; // reset front when end is reached
    		
    		}
    	}
		
		return val;
    }
    
    public int getSize() {// complete this function
    	return currentSize;
    }
    
    public String toString() {
        if (getSize() == 0)
            return "[]";
        else {
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
