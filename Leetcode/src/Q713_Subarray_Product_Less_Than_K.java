/***
 * 
 * @author jackie
 * 
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

 

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 *
 */
public class Q713_Subarray_Product_Less_Than_K {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }

        int sum = 1, result = 0;

        for (int front = 0, back = 0; front < nums.length; front++) {
            if (nums[front] >= k) {
                sum = 1;
                back = front+1;
                continue;
            }

            sum *= nums[front];

            while (sum >= k) {
                sum /= nums[back++];
            }

            result += front-back+1;
        }

        return result;
    }

}
