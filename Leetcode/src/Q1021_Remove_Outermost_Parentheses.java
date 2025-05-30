/***
 * 
 * @author jackie
 * 
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

 

Example 1:

Input: "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
 

Note:

S.length <= 10000
S[i] is "(" or ")"
S is a valid parentheses string

 */
public class Q1021_Remove_Outermost_Parentheses {
	public String removeOuterParentheses(String s) 
	{
        if (s == null || s.length() == 0)
        {
            return s;
        }
        
        int index = 0;
        int size = s.length();
        StringBuilder builder = new StringBuilder();
        
        while (index < size)
        {
            if (s.charAt(index) != '(')
            {
                index++;
                continue;
            }
            
            int pos = findPosition(s, index);
                
            if (pos == -1)
            {
                return "";
            }
            
            builder.append(s.substring(index+1, pos));
            index = pos+1;
        }
        
        return builder.toString();
    }
    
    private int findPosition(String s, int start)
    {
        int count = 0;
        
        for (int i = start; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
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
