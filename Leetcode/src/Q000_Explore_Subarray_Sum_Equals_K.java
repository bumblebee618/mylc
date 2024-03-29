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
public class Q000_Explore_Subarray_Sum_Equals_K {
	public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
        {
            return 0;   
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        
        for (int num : nums)
        {
            sum += num;
            count += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}
