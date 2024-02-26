/***
 * 
 * @author jackie
 * 
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

 

Example 1:

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
Example 2:

Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.
Example 3:

Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 

Constraints:

1 <= arr.length <= 105
-104 <= arr[i] <= 104
 */
public class Q1186_Maximum_Subarray_Sum_with_One_Deletion {
	public int maximumSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = Math.max(nums[0], nums[nums.length - 1]);
        int[] forward = new int[nums.length];
        forward[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            forward[i] = Math.max(forward[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, forward[i]);
        }

        int[] backward = new int[nums.length];
        backward[nums.length-1] = nums[nums.length-1];

        for (int i = nums.length - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i+1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, backward[i]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            maxSum = Math.max(maxSum, forward[i-1] + backward[i+1]);
        }

        return maxSum;
    }
}
