import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.

 

Example 1:

Input: nums = [1,2]
Output: 1
Explanation: The optimal choice of operations is:
(1 * gcd(1, 2)) = 1
Example 2:

Input: nums = [3,4,6,8]
Output: 11
Explanation: The optimal choice of operations is:
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
Example 3:

Input: nums = [1,2,3,4,5,6]
Output: 14
Explanation: The optimal choice of operations is:
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 

Constraints:

1 <= n <= 7
nums.length == 2 * n
1 <= nums[i] <= 106
 */
public class Q1799_Maximize_Score_After_N_Operations 
{
	// backtrack + memo
	private Map<Integer, Integer> memo;
    private int[] nums;
    private int size = 0;
    private int[][] gcd;
    
    public int maxScore(int[] nums) 
    {
        if (nums == null || nums.length == 0 || nums.length % 2 != 0)
        {
            return 0;
        }
        
        this.nums = nums;
        memo = new HashMap<>();
        size = nums.length;
        Arrays.sort(this.nums);
        initGcd();
        
        int finalStatus = 0;
        for (int i = 0; i < size; i++)
        {
            finalStatus |= (1 << i);
        }
        
        memo.put(finalStatus, 0);
        return backtrack(0, 1);
    }
    
    private int backtrack(int status, int operIndex)
    {
        if (memo.containsKey(status))
        {
            return memo.get(status);
        }
        
        int score = 0;
        
        for (int i = 0; i < size; i++)
        {
            if ( ((1 << i) & status) == 0 )
            {
                for (int j = i+1; j < size; j++)
                {
                    if ( ((1 << j) & status) == 0 )
                    {
                        int nextStatus = status;
                        nextStatus |= (1 << i);
                        nextStatus |= (1 << j);
                        
                        int nextScore = backtrack(nextStatus, operIndex+1);
                        score = Math.max(score, operIndex * gcd[i][j] + nextScore);
                    }
                }
            }
        }
        
        memo.put(status, score);
        return score;
    }
    
    private void initGcd()
    {
        gcd = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            for (int j = i+1; j < size; j++)
            {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }
    }
    
    private int gcd(int a, int b) 
    {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
