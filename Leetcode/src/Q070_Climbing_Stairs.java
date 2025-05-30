/*******
 * 
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top? 
 * 
 **/


public class Q070_Climbing_Stairs {
	// solution 1: using DP, time O(n), space O(n)
	public int climbStairs(int n) {
        if(n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2]; 
        }
        
        return dp[n];
    }
    
	
	
	
	// solution 2: follow up, improve the space, time O(n), space O(n)
    public int climbStairs2(int n) {
        if(n <= 0) {
            return 0;
        }
        
        int prevOne = 1;
        int prevTwo = 1;
        int curStep = 1;
        
        for(int i = 2; i <= n; i++) {
            curStep = prevOne + prevTwo;
            prevOne = prevTwo;
            prevTwo = curStep;
        }
        
        return curStep;
    }
}
