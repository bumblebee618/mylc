import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

 

Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
 */
public class Q1695_Maximum_Erasure_Value 
{
	public int maximumUniqueSubarray(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0, sum = 0, count = 0;
        
        for (int front = 0, back = 0; front < nums.length; front++)
        {
            map.put(nums[front], map.getOrDefault(nums[front], 0) + 1);
            sum += nums[front];
            
            if (map.get(nums[front]) == 2)
            {
                count++;
            }
            
            while (count > 0)
            {
                map.put(nums[back], map.get(nums[back])-1);
                sum -= nums[back];
                
                if (map.get(nums[back]) == 1)
                {
                    count--;
                }
                
                back++;
            }
            
            result = Math.max(result, sum);
        }
        
        return result;
    }
}
