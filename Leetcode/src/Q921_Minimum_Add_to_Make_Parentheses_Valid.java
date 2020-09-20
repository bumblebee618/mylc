/***
 * 
 * @author jackie
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.
 *
 */
public class Q921_Minimum_Add_to_Make_Parentheses_Valid {
	public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int stackSize = 0;
        int modifyCount = 0;
        
        for (char c : s.toCharArray())
        {
            if (c == '(')
            {
                stackSize++;
            }
            else if (c == ')')
            {
                if (stackSize == 0)
                {
                    modifyCount++;
                }
                else
                {
                    stackSize--;
                }
            }
        }
        
        return modifyCount + stackSize;
    }
}
