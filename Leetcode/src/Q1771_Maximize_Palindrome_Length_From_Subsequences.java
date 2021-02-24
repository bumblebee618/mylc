/***
 * 
 * @author jackie
 * 
 * You are given two strings, word1 and word2. You want to construct a string in the following manner:

Choose some non-empty subsequence subsequence1 from word1.
Choose some non-empty subsequence subsequence2 from word2.
Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.

A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.

A palindrome is a string that reads the same forward as well as backward.

 

Example 1:

Input: word1 = "cacb", word2 = "cbba"
Output: 5
Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
Example 2:

Input: word1 = "ab", word2 = "ab"
Output: 3
Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
Example 3:

Input: word1 = "aa", word2 = "bb"
Output: 0
Explanation: You cannot construct a palindrome from the described method, so return 0.
 

Constraints:

1 <= word1.length, word2.length <= 1000
word1 and word2 consist of lowercase English letters.
 */
public class Q1771_Maximize_Palindrome_Length_From_Subsequences 
{
	public int longestPalindrome(String word1, String word2) 
    {
        if (word1 == null || word1.length() == 0 || word2 == null || word2.length() == 0)
        {
            return 0;
        }
        
        int len1 = word1.length();
        String source = word1 + word2;

        int size = source.length();
        int[][] dp = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            dp[i][i] = 1;
        }
        
        for (int i = 0; i < size-1; i++)
        {
            dp[i][i+1] = source.charAt(i) == source.charAt(i+1) ? 2 : 1;
        }
        
        int maxLen = dp[len1-1][len1] == 2 ? 2 : 0;
        
        for (int length = 3; length <= size; length++)
        {
            for (int start = 0; start + length <= size; start++)
            {
                int end = start + length - 1;
                
                if (source.charAt(start) == source.charAt(end))
                {
                    dp[start][end] = dp[start+1][end-1] + 2;
                    
                    if (start < len1 && end >= len1 && maxLen < dp[start][end])
                    {
                        maxLen = dp[start][end];
                    }
                }
                else
                {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
        
        return maxLen;
    }
}
