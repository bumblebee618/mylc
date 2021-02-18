/***
 * 
 * @author jackie
 * 
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 */
public class Q516_Longest_Palindromic_Subsequence {
	public int longestPalindromeSubseq(String s) 
	{
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int len = s.length();
        int[][] dp = new int[len][len];
        
        for (int i = 0; i < len; i++)
        {
            dp[i][i] = 1;
        }
        
        for (int i = 0; i < len-1; i++)
        {
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1) ? 2 : 1;
        }
        
        for (int length = 3; length <= len; length++)
        {
            for (int start = 0; start+length <= len; start++)
            {
                int end = start + length - 1;
                
                if (s.charAt(start) == s.charAt(end))
                {
                    dp[start][end] = dp[start+1][end-1] + 2;
                }
                else
                {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
        
        return dp[0][len-1];
    }
}
