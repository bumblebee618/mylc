import java.util.*;
/**
 * 
Design a max stack that supports push, pop, top, peekMax and popMax.

push(row) -- Push element row onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= row <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 *
 */
public class Q716_Max_Stack 
{
	private Stack<Integer> stack;
    private Stack<Integer> maxStack;
    
    /** initialize your data structure here. */
    public Q716_Max_Stack() 
    {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) 
    {
        stack.push(x);
        
        if (maxStack.isEmpty()) 
        {
            maxStack.push(x);
        } 
        else 
        {
            maxStack.push(Math.max(x, maxStack.peek()));
        }
    }
    
    public int pop() 
    {
        if (stack.isEmpty()) 
        {
            return -1;
        } 
        else 
        {
            maxStack.pop();
            return stack.pop();
        }
    }
    
    public int top() 
    {
        if (stack.isEmpty()) 
        {
            return -1;
        } 
        else 
        {
            return stack.peek();
        }
    }
    
    public int peekMax() 
    {
        if (maxStack.isEmpty()) 
        {
            return -1;
        } 
        else 
        {
            return maxStack.peek();
        }
    }
    
    public int popMax() 
    {
        int max = maxStack.peek();
        Stack<Integer> buffer = new Stack<>();
        
        while (top() != max) 
        {
            buffer.push(pop());
        }
        
        pop();
        
        while (!buffer.isEmpty()) 
        {
            push(buffer.pop());
        }
        
        return max;
    }
}
