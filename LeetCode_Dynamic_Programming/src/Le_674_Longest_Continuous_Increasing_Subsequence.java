/**
 * 
 * 
 * 
Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
Note: Length of the array will not exceed 10,000.
 *
 */

public class Le_674_Longest_Continuous_Increasing_Subsequence {
	public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int continuousLen = 1;
        int maxLen = 1;
        
        for (int i = 1; i < len; i++) {
            continuousLen = (nums[i] > nums[i-1]) ? continuousLen + 1 : 1;          
            maxLen = Math.max(maxLen, continuousLen);
        }
        
        return maxLen;
    }
}
