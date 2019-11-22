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

public class Le_647_Palindromic_Substrings {
	// time complexity is O(n^2)
	public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        int count = 0;
        
        for (int i = 0; i < len; i++) {
            count += getpalindromicStrCount(s, i, i);
            count += getpalindromicStrCount(s, i, i+1);
        }
        
        return count;
    }
    
    private int getpalindromicStrCount(String s, int start, int end) {
        int count = 0;
       
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            count++;
        }
        
        return count;
    }
    
    // solution 2: use DP, time complexity is O(n^2)
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        int result = len;
        boolean[][] dp = new boolean[len][len];
        
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        
        for (int i = 0; i < len-1; i++) {
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1);
            result += dp[i][i+1] == true ? 1 : 0;
        }
        
        for (int length = 2; length < len; length++) {
            for (int start = 0; start + length < len; start++) {
                int end = start + length;
                dp[start][end] = dp[start+1][end-1] && s.charAt(start) == s.charAt(end);
                result += dp[start][end] == true ? 1 : 0;
            }
        }
        
        return result;
    }
}
