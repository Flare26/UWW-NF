//nathan frazier

public class QueueUsingStack {
    
    Stack mainStack;
    int maxQueueSize;
    
    public QueueUsingStack(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
        mainStack = new Stack(maxQueueSize);
    }
    
    public void enqueue(int val) { // complete this function
    	
    	/* SELF NOTE FOR FUTURE: THIS WAS KEY
    	 * Getting this mechanism correct was the root of all frustration. 
    	 * What is happening is basically a less practical way to order a stack, due to the nature of how stacks work.
    	 * It's getting stacks to behave like a queue even though stacks by themselves do not behave that way.
    	 * It's like array copying, but it allows insertion of numbers at the bottom of a stack instead of the top.
    	 * */
    	
    	Stack tempStack = new Stack(maxQueueSize);
    	for (int i = 0; i < mainStack.getSize(); i++) {
    		tempStack.push( mainStack.pop() );
    	}
    	
    	mainStack.push(val);
    	
    	for (int i = 0; i < tempStack.getSize(); i++) {
    		mainStack.push( tempStack.pop() );
    	}
    	
    }
    
    public int dequeue() { // complete this function
    	return mainStack.pop();
    }
    
    public int getSize() {
    	return mainStack.getSize();// complete this function
    }
    
    public String toString() {
        if (getSize() == 0) {
            return "[]";
        } else {
            String output = "[";
            int n = getSize();
            for (int i = 0; i < n - 1; i++) {
                int x = dequeue();
                output += x + ", ";
                enqueue(x);
            }
            int x = dequeue();
            output += x + "]";
            enqueue(x);
            return output;
        }
    }
}


