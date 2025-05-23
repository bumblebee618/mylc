/*****
 * 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 * 
 * */


public class Le_188_Best_Time_to_Buy_and_Sell_Stock_IV {
	/********************************************************************************************************
	 * State:
	 * 		mustSell[i][j] 表示前i天,至多进行j次交易,第i天必须sell的最大获益 
	 * 		globalbest[i][j] 表示前i天,至多进行j次交易,第i天可以不sell的最大获益
	 * Function: 
	 * 		gain = prices[i] - prices[i - 1];
	 *		mustsell[i][j] = Max(globalbest[(i - 1)][j - 1] + gain, mustsell[(i - 1)][j] + gain);
	 *		globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i][j]); 
	 * Intialization:
	 * 		mustsell[0][i] = globalbest[0][i] = 0; 
	 * Answer:
	 * 		globalbest[(n - 1)][k]
	 * 
	 ********************************************************************************************************/
	
	public int maxProfit(int k, int[] prices) {
        if (k == 0) {
			return 0;
		} else if (k >= prices.length / 2) {
			int profit = 0;
			for (int i = 1; i < prices.length; i++) {
				if (prices[i] > prices[i - 1]) {
					profit += prices[i] - prices[i - 1];
				}
			}
			return profit;
		}
		
		int n = prices.length;
		int[][] mustsell = new int[n][k + 1];   // mustSell[i][j]:
													// 表示前i天，至多进行j次交易，第i天必须sell的最大获益
		int[][] globalbest = new int[n][k + 1]; // globalbest[i][j]:
													// 表示前i天，至多进行j次交易，第i天可以不sell的最大获益

		mustsell[0][0] = globalbest[0][0] = 0;  
		
		for (int i = 1; i <= k; i++) {
			mustsell[0][i] = globalbest[0][i] = 0;
		}

		for (int i = 1; i < n; i++) {
			int gainorlose = prices[i] - prices[i-1];
			mustsell[i][0] = 0;
			
			for (int j = 1; j <= k; j++) {   // mustsell[(i-1)][j]： 第i-1天进行j次操作，第i-1天卖出，之后当天再买入，第i天卖出，相当于前i天进行j次操作
				mustsell[i][j] = Math.max(globalbest[(i - 1)][(j - 1)] + gainorlose, mustsell[(i - 1)][j] + gainorlose);
				globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i][j]);
			}
		}
		
		return globalbest[(n - 1)][k];
    }
}
