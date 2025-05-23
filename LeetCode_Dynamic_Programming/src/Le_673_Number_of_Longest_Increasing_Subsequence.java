/**
 * 
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 *
 */
public class Le_673_Number_of_Longest_Increasing_Subsequence {
	public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int[] curMaxLen = new int[len];
        int[] times = new int[len];
        int globalMaxLen = 1;
        int count = 0;
        
        for (int i = 0; i < len; i++) {
        	curMaxLen[i] = 1;
            times[i] = 1;
            
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (curMaxLen[i] < curMaxLen[j] + 1) {
                    	curMaxLen[i] = curMaxLen[j] + 1;
                        times[i] = times[j];
                    } else if (curMaxLen[i] == curMaxLen[j] + 1) {
                        times[i] += times[j];
                    }
                    curMaxLen[i] = Math.max(curMaxLen[i], curMaxLen[j] + 1);
                } 
            }
            
            globalMaxLen = Math.max(globalMaxLen, curMaxLen[i]);
        }
        
        for (int i = 0; i < len; i++) {
        	System.out.println(curMaxLen[i] + ",     " + times[i]);
            if (globalMaxLen == curMaxLen[i]) {
                count += times[i];
            }
        }
        
        return count;
    }
	
	
	
	public static void main(String[] args) {
		Le_673_Number_of_Longest_Increasing_Subsequence test = new Le_673_Number_of_Longest_Increasing_Subsequence();
//		int[] nums = {1,2,4,3,5,4,7,2};
		int[] nums = {1,1,1,2,2,2,3,3,3};
		System.out.println(test.findNumberOfLIS(nums));
	}
}
