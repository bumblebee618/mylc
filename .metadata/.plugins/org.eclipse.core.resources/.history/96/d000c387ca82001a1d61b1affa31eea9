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
public class Q678_Valid_Parenthesis_String {
	public boolean checkValidString(String s) {
        if (s == null || s.length() == 0)
        {
            return true;
        }
        
        int size = s.length();
        boolean[][] dp = new boolean[size][size];
        
        for (int i = 0; i < size; i++)
        {
            dp[i][i] = s.charAt(i) == '*' ? true : false;
        }
        
        for (int i = 0; i < size-1; i++)
        {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);
            
            if ((c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*')) 
            {
                dp[i][i+1] = true;
            }
        }
        
        for (int length = 2; length < size; length++)
        {
            for (int start = 0; start+length < size; start++)
            {
                int end = start+length;
            
                if (s.charAt(start) == '*' && dp[start+1][end] == true) 
                {
                    dp[start][end] = true;
                } 
                else if (s.charAt(start) == '(' || s.charAt(start) == '*') 
                {
                    for (int k = start+1; k <= end; k++) 
                    {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                                (k == start+1 || dp[start+1][k-1]) &&
                                (k == end || dp[k+1][end])) 
                        {
                            dp[start][end] = true;
                        }
                    }
                }
            }
        }
        
        return dp[0][size-1];
    }
}
