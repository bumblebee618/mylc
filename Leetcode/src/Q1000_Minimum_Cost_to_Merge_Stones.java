/***
 * 
 * @author jackie
 * 
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.

 

Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 2:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Example 3:

Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation: 
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.
 

Note:

1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
 */
public class Q1000_Minimum_Cost_to_Merge_Stones {
	// 区间DP
	public int mergeStones(int[] stones, int K) 
    {
        if (stones == null || stones.length == 0 || K <= 0)
        {
            return -1;
        }
        else if ((stones.length-1) % (K-1) > 0)
        {
            return -1;
        }

        int len = stones.length;
        
        // dp[i][j] means the minimum cost needNum to merge stones[i] ~ stones[j]
        int[][] dp = new int[len][len];
        
        // stoneSum[i] is the total cost to remove stones[0] ~ stones[i] together
        int[] stonesCost = new int[len];
        stonesCost[0] = stones[0];
        
        for (int i = 1; i < len; i++)
        {
            stonesCost[i] = stonesCost[i-1] + stones[i];
        }
        
        for (int length = K; length <= len; length++)
        {
            for (int start = 0; start + length <= len; start++) 
            {
                int end = start+length-1;
                dp[start][end] = Integer.MAX_VALUE;
                
                // we don't need to check any subarray within [start, start+(K-1)]
                // for example: [start, t] and [t, start+(k-1)] will never be merged together to form
                // an K-length array [start, start+(k-1)], so we can skip K-1 step
                for (int mid = start; mid < end; mid += K-1)
                {
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end]);
                }
                
                // when the current subarray [start, end] can be merged:
                if ((end-start) % (K-1) == 0)
                {
                    int cost = start > 0 ? stonesCost[end] - stonesCost[start-1] : stonesCost[end];
                    dp[start][end] += cost;
                }
            }
        }
        
        return dp[0][len-1];
    }
}
