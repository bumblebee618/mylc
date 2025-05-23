/***
 * 
 * @author jackie
 * 
 * There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that exactly k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.

For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 109 + 7.

 

Example 1:

Input: n = 3, k = 2
Output: 3
Explanation: [1,3,2], [2,3,1], and [2,1,3] are the only arrangements such that exactly 2 sticks are visible.
The visible sticks are underlined.
Example 2:

Input: n = 5, k = 5
Output: 1
Explanation: [1,2,3,4,5] is the only arrangement such that all 5 sticks are visible.
The visible sticks are underlined.
Example 3:

Input: n = 20, k = 11
Output: 647427950
Explanation: There are 647427950 (mod 109 + 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
 

Constraints:

1 <= n <= 1000
1 <= k <= n
 */

public class Q1866_Number_of_Ways_to_Rearrange_Sticks_With_K_Sticks_Visible 
{
	//Let's use DP to solve, dp[i][j]: we have i sticks and j left visible
    //imagine for i+1, we add a smallest stick into the group
    //so for dp[i+1][j],when we put this smallest stick not in the first place, 
    //this stick will have no impact on left visible
    //and there are i places to put, so i * dp[i][j]
    //when this smallest stick is put in the first place, then we can only have j-1 visible for the remaining
    //so dp[i][j-1]
    //dp[i+1][j] = dp[i][j] * i + dp[i][j-1]
    //dp[0][0] = 1
	
	public int rearrangeSticks(int n, int k) 
	{
		if (n <= 0 || k <= 0 || k > n)
        {
        	return 0;
        }
        
        int mod = 1_000_000_007;
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1L;
  
        for (int i = 1; i <= n; i++) 
        {
            for (int j = 1; j <= Math.min(i, k); j++) 
            {
                dp[i][j] = (dp[i-1][j] * (long)(i-1)+ dp[i-1][j-1]) % mod;
                dp[i][j] %= mod;
            }
        }
        
        return (int) dp[n][k];
    }
	
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		Q1866_Number_of_Ways_to_Rearrange_Sticks_With_K_Sticks_Visible test = new Q1866_Number_of_Ways_to_Rearrange_Sticks_With_K_Sticks_Visible();
		
		int n1= 3, k1 = 2;
		System.out.println(test.rearrangeSticks(n1, k1));
		
	}
}
