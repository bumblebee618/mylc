/***
 * 
 * @author jackie
 * 
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:

Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
 

Example 2:

Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 

Constraints:

1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.
 */

public class Q486_Predict_the_Winner 
{
	// solution 1: 自底向上 recursion + memo
	public boolean PredictTheWinner(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return false;
        }
        else if (nums.length == 1)
        {
            return true;
        }
        
        int size = nums.length;
        Integer[][] memo = new Integer[size][size];
        return search(nums, memo, 0, size-1) >= 0;
    }   
    
    private int search(int[] nums, Integer[][] memo, int left, int right)
    {
        if (left == right)
        {
            return nums[left];
        }
        else if (memo[left][right] != null)
        {
            return memo[left][right];
        }
        
        int takeLeft = nums[left] - search(nums, memo, left+1, right);
        int takeRight = nums[right] - search(nums, memo, left, right-1);
        memo[left][right] = Math.max(takeLeft, takeRight);
        return memo[left][right];
    }
	
    
	// solution 2: 区间DP
	public boolean PredictTheWinner2(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return false;
        }
        else if (nums.length == 1)
        {
            return true;
        }
        
        int size = nums.length;
        int[][] scores = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            scores[i][i] = nums[i];
        }
        
        for (int length = 2; length <= size; length++)
        {
            for (int start = 0; start + length <= size; start++)
            {
                int end = start + length - 1;
                scores[start][end] = Math.max(nums[start] - scores[start+1][end], nums[end] - scores[start][end-1]);
            }
        }
        
        return scores[0][size-1] >= 0;
    }
}
