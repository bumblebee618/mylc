import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
 

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50
 */

public class Q856_Score_of_Parentheses 
{
	// solution 1: stack
	public int scoreOfParentheses(String S) 
    {
        if (S == null || S.length() == 0)
        {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int currentValue = 0;
        
        for (char c : S.toCharArray())
        {
            if (c == '(')
            {
                stack.push(currentValue);
                currentValue = 0;
            }
            else
            {
               currentValue = stack.pop() + Math.max(2 * currentValue, 1);
            }
        }
        
        return currentValue;
    }
	
	
	// solution 2: divide and conquer
	public int scoreOfParentheses2(String S) 
    {
        if (S == null || S.length() == 0)
        {
            return 0;
        }
        
        int start = 0;
        int size = S.length();
        int result = 0;
        
        while (start < size)
        {
            int end = findClosePos(S, start);
            
            if (end == -1)
            {
                return 0;
            }
            else if (end == start+1)
            {
                result += 1;
            }
            else
            {
                result += 2 * scoreOfParentheses(S.substring(start+1, end));
            }
            
            start = end+1;
        }
        
        return result;
    }
    
    private int findClosePos(String S, int start)
    {
        int count = 0;
        
        for (int i = start; i < S.length(); i++)
        {
            char c = S.charAt(i);
            
            if (c == '(')
            {
                count++;
            }
            else if (c == ')')
            {
                count--;
            }
            
            if (count == 0)
            {
                return i;
            }
        }
        
        return -1;
    }
}
