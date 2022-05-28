/***
 * 
 * @author jackie
 * 
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
 */
public class Q583_Delete_Operation_for_Two_Strings 
{
	public int minDistance(String word1, String word2) 
    {
        if (word1 == null || word1.length() == 0)
        {
            return word2 == null ? 0 : word2.length();
        }
        else if (word2 == null || word2.length() == 0)
        {
            return word1 == null ? 0 : word1.length();
        }
        
        int size1 = word1.length(), size2 = word2.length();
        int[][] dp = new int[size1+1][size2+1];
        
        for (int i = 1; i <= size1; i++)
        {
            for (int j = 1; j <= size2; j++)
            {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                
                if (word1.charAt(i-1) == word2.charAt(j-1))
                {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        
        return (size1-dp[size1][size2]) + (size2-dp[size1][size2]);
    }
}
