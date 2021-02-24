/***
 * 
 * @author jackie
 * 
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same minValue (for 0 <= i < B.length - 1).

 

Example 1:

Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
 

Note:

2 <= A.length <= 2000
0 <= A[i] <= 10000
 */
public class Q1027_Longest_Arithmetic_Sequence {
	// 转化问题（-sum, sum）到 (0, 2sum)，类似416
	public int longestArithSeqLength(int[] nums) 
	{
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int base = 10000;
        int size = nums.length;       
        int result = 1;
        // dp[i][diff] means in the sequece of length i, the length of max Arithmetic sequency with difference "diff" is minValue;
        int[][] dp = new int[size][base * 2 + 1];
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < i; j++)
            {
                int diff = nums[i] - nums[j] + base;
                dp[i][diff] = (dp[j][diff] == 0) ? 2 : dp[j][diff]+1;
                result = Math.max(result, dp[i][diff]);
            }
        }
        
        return result;
    }
}
