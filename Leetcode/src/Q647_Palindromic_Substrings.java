/**
 * 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
 *
 */
public class Q647_Palindromic_Substrings {
	public int countSubstrings(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int size = s.length();
        int[][] dp = new int[size][size];
        int result = size;
        
        for (int i = 0; i < size; i++)
        {
            dp[i][i] = 1;
        }
        
        for (int i = 0; i < size-1; i++)
        {
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1) ? 1 : 0;
            result += dp[i][i+1];
        }
        
        for (int length = 2; length < size; length++)
        {
            for (int start = 0; start+length < size; start++)
            {
                int end = start + length;
                
                if (dp[start+1][end-1] == 1 && s.charAt(start) == s.charAt(end))
                {
                    dp[start][end] = 1;
                }
                
                result += dp[start][end];
            }
        }
        
        return result;
    }
}
