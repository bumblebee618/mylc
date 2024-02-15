/***
 * 
 * @author jackie
 * 
 * Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
 * 
 */
public class Q1043_Partition_Array_for_Maximum_Sum {
	public int maxSumAfterPartitioning(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return 0;
        }

        int[] dp = new int[nums.length+1];
        int maxNum = Integer.MIN_VALUE;
        
        for (int i = 1; i <= k; i++) {
            maxNum = Math.max(maxNum, nums[i-1]);
            dp[i] = maxNum * i;
        }

        for (int i = k+1; i <= nums.length; i++) {
            maxNum = Integer.MIN_VALUE;
            
            for (int len = 1; len <= k; len++) {
                maxNum = Math.max(maxNum, nums[i-len]);
                dp[i] = Math.max(dp[i], dp[i-len] + maxNum * len);
            }
        }

        return dp[nums.length];
    }
}
