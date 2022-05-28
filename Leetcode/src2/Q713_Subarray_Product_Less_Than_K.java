/**
 * 
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.

 *
 */
public class Q713_Subarray_Product_Less_Than_K {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        
        int len = nums.length;
        long sum = 1;
        int back = 0;
        int count = 0;
        
        for (int front = 0; front < len; front++) {
            sum *= nums[front];
            
            while (back <= front && sum >= k) {
            	sum /= nums[back++];
            }
            
            count += (front - back + 1);
        }
        
        return count;
    }
    
    
    public static void main(String[] args) {
    	Q713_Subarray_Product_Less_Than_K test = new Q713_Subarray_Product_Less_Than_K();
    	int[] nums = {10, 5, 2, 6};
    	System.out.println(test.numSubarrayProductLessThanK(nums, 100));
    }
}
