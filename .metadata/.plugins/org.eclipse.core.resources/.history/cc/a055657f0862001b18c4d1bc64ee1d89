/*****
 * 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).
 * 
 * */


public class Q123_Best_Time_to_Buy_and_Sell_Stock_III {
	public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right;              // min: 截至到当前的最低价格， prices[i]. 当日卖出价格
        left[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
        	minPrice = Math.min(prices[i], minPrice);
        	left[i] = Math.max(left[i-1], prices[i] - minPrice);
        }

        // DP from right to left;              // max: 截至到当前的最高价格， prices[i].  买入价格
        right[prices.length - 1] = 0;
        int maxPrice = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
        	maxPrice = Math.max(prices[i], maxPrice);
        	right[i] = Math.max(right[i+1], maxPrice - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);  
        }

        return profit;
    }
	
	public static void main(String[] args) {
		Q123_Best_Time_to_Buy_and_Sell_Stock_III test = new Q123_Best_Time_to_Buy_and_Sell_Stock_III();
		int[] prices = {3,3,5,0,0,3,1,4};
		System.out.println(test.maxProfit(prices));
	}
	
}
