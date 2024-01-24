/***
 * 
 * @author jackie
 * 
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
 */
public class Q678_Valid_Parenthesis_String 
{
	public boolean checkValidString(String s) 
	{
        if (s == null || s.length() == 0)
        {
            return true;
        }
        
        int size = s.length();
        boolean[][] dp = new boolean[size][size];

        for (int i = 0; i < size; i++)
        {
            dp[i][i] = s.charAt(i) == '*';
        }
        
        for (int i = 0; i < s.length()-1; i++)
        {
            dp[i][i+1] = isValid(s, i, i+1);
        }
        
        for (int length = 3; length <= size; length++)
        {
            for (int start = 0; start+length <= size; start++)
            {
                int end = start+length-1;
                
                if (dp[start+1][end-1] && isValid(s, start, end))
                {
                    dp[start][end] = true;
                }
                else
                {
                    for (int k = start; k < end; k++)
                    {
                        if (dp[start][k] && dp[k+1][end])
                        {
                            dp[start][end] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][size-1];
    }
    
    private boolean isValid(String s, int index1, int index2)
    {
        char c1 = s.charAt(index1);
        char c2 = s.charAt(index2);
        return (c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*') ? true : false;
    }
}
