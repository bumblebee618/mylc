/***
 * 
 * @author jackie
 * 
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10] 
Output: 1
 

Note:

You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 * 
 */
public class Q518_Coin_Change_2 {
	public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0)
        {
            return amount == 0 ? 1 : 0;
        }
        
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) 
        {
        	// 如果元素可以重复利用，则必须从小到大遍历; test case: [1,2,5]; 反之不可以重复利用，则从大到小遍历，例如416题
            for (int x = coin; x <= amount; x++) 
            {
                dp[x] += dp[x - coin];
            }
        }
        
        return dp[amount];
    }
}
