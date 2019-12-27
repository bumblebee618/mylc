/***
 * 
 * @author jackie
 * 
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 

Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
 */
public class Q1216_Valid_Palindrome_III {
	public boolean isValidPalindrome(String s, int k) {
        if (s == null || s.length() == 0)
        {
            return false;
        }
        
        return s.length() - longestPalindromeSubseq(s) <= k;
    }
    
    private int longestPalindromeSubseq(String s) {
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
        
        for (int length = 2; length < len; length++)
        {
            for (int start = 0; start+length < len; start++)
            {
                int end = start + length;
                
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
