import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 

 

Note:

S contains only lowercase letters.
1 <= S.length <= 2000
 * 
 */
public class Q940_Distinct_Subsequences_II 
{
	/***
	dp[0] = 2, as it counts ("", "a")
	dp[1] = 4, as it counts ("", "a", "b", "ab");
	dp[2] = 7 as it counts ("", "a", "b", "aa", "ab", "ba", "aba");
	dp[3] = 12, as it counts ("", "a", "b", "aa", "ab", "ba", "bb", "aab", "aba", "abb", "bab", "abab")
	 **/
	public int distinctSubseqII(String str) 
    {
        if (str == null || str.length() == 0)
        {
            return 0;
        }
        
        int MOD = 1_000_000_007;
        int size = str.length();
        int[] dp = new int[size+1];
        dp[0] = 1;  // ""

        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        for (int i = 1; i <= size; ++i) 
        {
            dp[i] = dp[i-1] * 2 % MOD;
            int pos = str.charAt(i-1) - 'a';
            
            if (lastIndex[pos] >= 0)
            {
            	// remove some duplicated subarrays
                dp[i] -= dp[lastIndex[pos]-1];
            }
            
            dp[i] %= MOD;
            lastIndex[pos] = i;
        }

        dp[size]--;   // remove "";
        
        if (dp[size] < 0) 
        {   
            dp[size] += MOD;
        }
        
        return dp[size];
    }
}
