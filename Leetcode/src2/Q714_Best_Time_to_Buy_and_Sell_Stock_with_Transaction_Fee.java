/***
 * 
 * @author jackie
 * 
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
 */
public class Q714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee 
{
	public int maxProfit(int[] prices, int fee) 
	{
        if (prices == null || prices.length <= 1) 
        {
            return 0;
        }
        
        int size = prices.length;
        
        // this is the profit after buying operation, not the cost. 
        // 即当天持股时的最大获利
        int[] buy = new int[size];
        buy[0] = -prices[0];
        
        // 当天不持股时的最大获利
        int[] sell = new int[size];
        
        for (int i = 1; i < prices.length; i++) 
        {
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);
        }
        
        return sell[size-1];
    }
}
