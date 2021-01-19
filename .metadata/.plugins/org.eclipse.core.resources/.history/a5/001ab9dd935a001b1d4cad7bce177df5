import java.util.Stack;
/*
入队时，将元素压入s1。
出队时，判断s2是否为空，如不为空，则直接弹出顶元素；如为空，则将s1的元素逐个“倒入”s2，把最后一个元素弹出并出队。
这个思路，避免了反复“倒”栈，仅在需要时才“倒”一次。
*/

public class Q232_Implement_Queue_using_Stacks {
	private Stack<Integer> s1;
	private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public Q232_Implement_Queue_using_Stacks() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s1.isEmpty() && s2.isEmpty()) {
    		return -1;
    	}
        
        if (s2.isEmpty()) {
            tranfer();
        }
        
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(s1.isEmpty() && s2.isEmpty()) {
    		return -1;
    	}
        
        if (s2.isEmpty()) {
            tranfer();
        }
        
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    private void tranfer() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
    
    
    
    
    
    
    
    
    
    public static void main(String[] args){
    	Q232_Implement_Queue_using_Stacks s = new Q232_Implement_Queue_using_Stacks();
    	s.push(1);
    	s.push(2);  
    	s.pop();
    	s.push(3);
    	s.push(4);
    	s.pop();
    	System.out.println("result: " + s.peek());
    }
}
