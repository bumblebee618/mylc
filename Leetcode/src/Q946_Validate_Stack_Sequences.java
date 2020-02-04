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
public class Q946_Validate_Stack_Sequences {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length < popped.length)
        {
            return false;
        }
        
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int index1 = 0, index2 = 0;
        int size1 = pushed.length, size2 = popped.length;
        
        while (index1 < size1 || index2 < size2)
        {
            if (index1 < size1 && index2 < size2 && pushed[index1] == popped[index2])
            {
                index1++;
                index2++;
            }
            else if (index2 < size2 && visited.contains(popped[index2]))
            {
                if (stack.peek() != popped[index2])
                {
                    return false;
                }
                
                stack.pop();
                index2++;
            }
            else if (index1 < size1) 
            {
                stack.push(pushed[index1]);
                visited.add(pushed[index1]);
                index1++;
            }
        }
        
        return index1 == size1 && index2 == size2;
    }
}
