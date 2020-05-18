import java.util.*;
/**
 * 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 *
 */
public class Q698_Partition_to_K_Equal_Sum_Subsets {
	public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return k == 0;
        } else if (k < 0) {
            return false;
        } else if (k == 0) {
            return true;
        }
        
        int sum = Arrays.stream(nums).sum();
        
        if (sum % k != 0) {
            return false;
        }
        
        int len = nums.length;
        boolean[] visited = new boolean[len];
        return backtrack(nums, visited, 0, 0, sum/k, k);        
    }
    
    private boolean backtrack(int[] nums, boolean[] visited, int start, int sum, int target, int k) {
        if (k == 1) {
            return true;
        } else if (sum == target) {
            return backtrack(nums, visited, 0, 0, target, k-1);
        }
        
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                if (backtrack(nums, visited, i+1, sum+nums[i], target, k)) {
                    return true;
                }
                
                visited[i] = false;
            }
        }
        
        return false;
    }
}
