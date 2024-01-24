import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

 

Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
Example 2:

Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.
 

Note:

0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed is a permutation of popped.
pushed and popped have distinct values.
 */
public class Q946_Validate_Stack_Sequences 
{
	/***
	 * test case1: pushed=[2,1,3,0],  popped=[1,0,3,2]
	 * test case2: pushed=[2,1,0],  popped=[1,2,0]
	 * test case3: pushed=[0,1,2,3],  popped=[0,1,2,3]
	 */
	
	// solution 1: use stack, time is O(n) and space is O(n)
	public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) {
            return pushed == null && popped == null;
        } else if (pushed.length != popped.length) {
            return false;
        }
        
        int index1 = 0, index2 = 0;
        Stack<Integer> stack = new Stack<>();
        
        while (index1 < pushed.length || index2 < popped.length) {
            if (!stack.isEmpty() && index2 < popped.length && stack.peek() == popped[index2]) {
                stack.pop();
                index2++;
            } else if (index1 < pushed.length) {
                stack.push(pushed[index1++]);
            } else {
                return false;
            }
        }
        
        return index1 == pushed.length && index2 == popped.length && stack.isEmpty();
    }
	
	
	// use stack, time is O(n) and space is O(n)
	public boolean validateStackSequences(int[] pushed, int[] popped) 
    {
        if (pushed == null || pushed.length == 0)
        {
            return (popped == null || popped.length == 0);
        }
        else if (pushed.length != popped.length)
        {
            return false;
        }
        
        Stack<Integer> stack = new Stack<>();
        int size = pushed.length;
        int index1 = 0, index2 = 0;
        
        while (index2 < size && index1 < size)
        {
            if (pushed[index1] != popped[index2])
            {
                if (!stack.isEmpty() && popped[index2] == stack.peek())
                {
                    stack.pop();
                    index2++;
                }
                else
                {
                    stack.push(pushed[index1]);
                    index1++;
                }
            }
            else
            {
                index1++;
                index2++;
            }
        }
        
        while (index2 < size && !stack.isEmpty())
        {
            if (popped[index2++] != stack.pop())
            {
                return false;
            }
        }
        
        return index2 == size && stack.isEmpty();
    }
	
	
	
	
	// solution 2: use two pointers, time is O(n^2) and space is O(n)
	public boolean validateStackSequences2(int[] pushed, int[] popped) 
    {
        if (pushed == null || pushed.length == 0)
        {
            return (popped == null || popped.length == 0);
        }
        else if (pushed.length != popped.length)
        {
            return false;
        }
        
        int index1 = 0, stackTop = -1;
        int size = pushed.length;
        Set<Integer> visited = new HashSet<>();
        
        for (int index2 = 0; index2 < size; index2++)
        {
            while (stackTop != -1 && visited.contains(pushed[stackTop]))
            {
                stackTop--;
            }
            
            if (stackTop != -1 && pushed[stackTop] == popped[index2])
            {
                visited.add(popped[index2]);
                stackTop--;
                continue;
            }
                
            while (index1 < size && pushed[index1] != popped[index2])
            {
                index1++;
            }
            
            if (index1 == size)
            {
                return false;
            }
            
            stackTop = index1-1;
            visited.add(popped[index2]);
            index1++;
        }
        
        return true;
    }
}
