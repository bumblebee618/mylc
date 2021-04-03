import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
Example 2:

Input: nums = [13,10,35,24,76]
Output: 4
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
 */
public class Q1814_Count_Nice_Pairs_in_an_Array 
{
	public int countNicePairs(int[] nums) 
	{
		if (nums == null || nums.length <= 1)
		{
			return 0;
		}
		
		int mod = 1_000_000_007;
		Map<Long, Long> map = new HashMap<>();
		
		for (int num : nums)
		{
			long diff = rev(num);
			map.put(diff, map.getOrDefault(diff, 0L)+1);
		}
		
		long result = 0;
		
		for (Map.Entry<Long, Long> entry : map.entrySet())
		{
			if (entry.getValue() > 1)
			{
				result = (result + entry.getValue() * (entry.getValue()-1) / 2) % mod;
			}
		}
		
		return (int) result;
    }
	
	private long rev(int num)
	{
		long originalNum = num, revNum = 0;
		
		while (num > 0)
		{
			revNum = revNum * 10 + num % 10;
			num /= 10;
		}
		
		return originalNum - revNum;
	}
}
