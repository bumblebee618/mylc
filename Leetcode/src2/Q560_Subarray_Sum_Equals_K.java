import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class Q560_Subarray_Sum_Equals_K {
	public int subarraySum(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        long sum = 0;
        int result = 0;
        
        for (int num : nums) 
        {
            sum += num;
            result += map.getOrDefault(sum-k, 0);
            // result += map.getOrDefault(sum+k, 0);  // follow up: 全负数的时候加上此句
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        
        return result;
    }
	
	
	
	
	/***************************** main *****************************/
	
	public static void main(String[] args)
	{
		Q560_Subarray_Sum_Equals_K test = new Q560_Subarray_Sum_Equals_K();
		int[] nums1 = {-1, -2, -3, -4, -5};
		int[] nums2 = {1, 2, 3};
		int[] nums3 = {1, 1, 1};
		System.out.println(test.subarraySum(nums1, 7));
		System.out.println(test.subarraySum(nums2, 3));
		System.out.println(test.subarraySum(nums3, 2));
	}
}
