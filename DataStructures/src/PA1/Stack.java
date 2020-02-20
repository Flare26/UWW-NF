//nathan frazier
public class Stack {
    
    private int maxStackSize, topOfStack; 
    private int[] stack;
    
    public Stack(int maxStackSize) {
        if (maxStackSize <= 0)
            System.out.println("Stack size should be a positive integer.");
        else {
            this.maxStackSize = maxStackSize;
            topOfStack = -1;
            stack = new int[maxStackSize];
        }
    }
    
    public void push(int val) { // complete this function
    	if (topOfStack == maxStackSize - 1) {
    		System.out.println("Cannot push to an already full stack!");
    	} else {
    		stack[++topOfStack] = val;
    	}
    }
    
    public int pop() { // complete this function
    	if (topOfStack == -1) {
    		System.out.println("Error cannot pop: stack empty!!");
    		return -1;
    	} else {
    	
    	System.out.println("Currently pop-ing index" + topOfStack);
    		return stack[topOfStack--];
    	}
    }
    
    public int getSize() { // complete this function
    	
    	return topOfStack+1;
    }
}


