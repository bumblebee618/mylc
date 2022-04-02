/***
 * 
 * @author jackie
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
 */
public class Q1143_Longest_Common_Subsequence {
	public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        
        int size1 = text1.length(), size2 = text2.length();
        int[][] dp = new int[size1+1][size2+1];
        
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i-1][j-1]+1, Math.max(dp[i-1][j], dp[i][j-1]));
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[size1][size2];
    }

	
	
	
	// follow up: find maximum length of continuous subsequence 
	public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        
        int size1 = text1.length(), size2 = text2.length();
        int[][] dp = new int[size1+1][size2+1];
        int result = 0;
        
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                
                result = Math.max(result, dp[i][j]);
            }
        }
        
        return result;
    }
	
	
	
	
	/*********************************** main ***********************************/
	
	public static void main(String[] args) {
		Q1143_Longest_Common_Subsequence test = new Q1143_Longest_Common_Subsequence();
		String text1 = "abcde";
		String text2 = "123abc";
		System.out.println(test.longestCommonSubsequence2(text1, text2));
	}
}
