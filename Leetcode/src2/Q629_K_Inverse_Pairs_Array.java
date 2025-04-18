/***
 * 
 * @author jackie
 * 
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].

Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.

 

Example 1:

Input: n = 3, k = 0
Output: 1
Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
Example 2:

Input: n = 3, k = 1
Output: 2
Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 

Constraints:

1 <= n <= 1000
0 <= k <= 1000
 */
public class Q629_K_Inverse_Pairs_Array 
{
	public int kInversePairs(int n, int k) 
    {
		// dp[n][k] = dp[n][k-1] + dp[n - 1][k] - dp[n - 1][k - n]
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;
        int mod = 1_000_000_007;
        
        for (int i = 1; i <= n; i++) 
        {
        	dp[i][0] = 1;
        	
            for (int j = 1; j <= k ; j++) 
            {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % mod;
                
                if (j >= i)
                {
                	dp[i][j] = (dp[i][j] - dp[i-1][j-i] + mod) % mod;
                }
            }
        }
        
        return dp[n][k];
    }
	
	public int kInversePairs2(int n, int k) 
    {
        int[] dp = new int[k + 1];
        int mod = 1_000_000_007;
        
        for (int i = 1; i <= n; i++) 
        {
            int[] temp = new int[k + 1];
            temp[0] = 1;
            
            for (int j = 1; j <= k ; j++) 
            {
                int val = (dp[j] + mod - ((j - i) >= 0 ? dp[j - i] : 0)) % mod;
                temp[j] = (temp[j - 1] + val) % mod;
            }
            
            dp = temp;
        }
        
        return ((dp[k] + mod - (k > 0 ? dp[k - 1] : 0)) % mod);
    }
}
