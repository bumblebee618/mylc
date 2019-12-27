import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 *
 *Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

 

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 

Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
Accepted
 */
public class Q523_Continuous_Subarray_Sum {
	// Time complexity O(n^2), space complexity O(n)
	public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
        {
            return k == 0 ? true : false;
        }
        
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        
        for (int i = 1; i < len; i++)
        {
            sum[i] = sum[i-1] + nums[i];
        }
        
        for (int start = 0; start < len-1; start++)
        {
            for (int end = start+1; end < len; end++)
            {
                int tempSum = sum[end]-sum[start]+nums[start];
                
                if (tempSum == k || (k != 0 && tempSum % k == 0))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
	
	// time complexity is O(n), space complexity is O(n)
	public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0)
        {
            return k == 0 ? true : false;
        }
        
        int len = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        for (int i = 0; i < len; i++)
        {
            sum += nums[i];
            
            if (k != 0)
            {
                sum = sum % k;
            }
            
            if (map.containsKey(sum))
            {
                if (i - map.get(sum) > 1)
                {
                    return true;
                }
            }
            else
            {
                map.put(sum, i);
            }
        }
        
        return false;
    }
}
