/***
 * 
 * @author jackie
 * 
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

 

Example 1:

Input: piles = [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 

Constraints:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles) is odd.
 */
public class Q877_Stone_Game {
	public boolean stoneGame(int[] piles) {
        int size = piles.length;
        int[][] dp = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            dp[i][i] = (size-1) % 2 == 0 ? piles[i] : -piles[i];
        }
        
        for (int length = 2; length <= size; length++) {
            for (int start = 0; start+length <= size; start++) {
                int end = start+length-1;
                int leftScore = (size-length) % 2 == 0 ? piles[start] : -piles[start];
                int rightScore = (size-length) % 2 == 0 ? piles[end] : -piles[end];
                dp[start][end] = Math.max(dp[start+1][end]+leftScore, dp[start][end-1]+rightScore);
            }
        }
        
        return dp[0][size-1] > 0;
    }
}