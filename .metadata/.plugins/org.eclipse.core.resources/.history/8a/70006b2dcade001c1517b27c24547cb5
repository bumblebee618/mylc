import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */
public class Q525_Contiguous_Array {
	public int findMaxLength(int[] nums) 
	{
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0, count = 0;
        
        for (int i = 0; i < nums.length; i++) 
        {
            count += (nums[i] == 1 ? 1 : -1);
            
            if (map.containsKey(count)) 
            {
                maxLen = Math.max(maxLen, i - map.get(count));
            } 
            else 
            {
                map.put(count, i);
            }
        }
        
        return maxLen;
    }
}
