import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given an array nums​​​ and an integer k​​​​​. The XOR of a segment [left, right] where left <= right is the XOR of all the elements with indices between left and right, inclusive: nums[left] XOR nums[left+1] XOR ... XOR nums[right].

Return the minimum number of elements to change in the array such that the XOR of all segments of size k​​​​​​ is equal to zero.

 

Example 1:

Input: nums = [1,2,0,3,0], k = 1
Output: 3
Explanation: Modify the array from [1,2,0,3,0] to from [0,0,0,0,0].
Example 2:

Input: nums = [3,4,5,2,1,7,3,4,7], k = 3
Output: 3
Explanation: Modify the array from [3,4,5,2,1,7,3,4,7] to [3,4,7,3,4,7,3,4,7].
Example 3:

Input: nums = [1,2,4,1,2,5,1,2,6], k = 3
Output: 3
Explanation: Modify the array from [1,2,4,1,2,5,1,2,6] to [1,2,3,1,2,3,1,2,3].
 

Constraints:

1 <= k <= nums.length <= 2000
​​​​​​0 <= nums[i] < 210
 */
public class Q1787_Make_the_XOR_of_All_Segments_Equal_to_Zero 
{
	public int minChanges(int[] nums, int k) 
	{
		if (k == 1) 
		{
			int count0 = 0;
			
			for (int i = 0; i < nums.length; i++) 
			{
				if (nums[i] == 0) 
				{
					count0++;
				}
			}
			
			return nums.length - count0;
		}

		// Count the occurance of each item i % k
		List<Map<Integer, Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < k; i++) 
		{
			list.add(new HashMap<Integer, Integer>());
		}
		
		for (int i = 0; i < nums.length; i++) 
		{
			Map<Integer, Integer> map = list.get(i % k);
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		// BFS to iterate every layer
		Map<Integer, Integer> preMap = new HashMap<>();
		preMap.put(0, 0);

		// The max numbers that don't need to be changed.
		int max = 0;
		
		for (int kk = 0; kk < k; kk++) 
		{
			Map<Integer, Integer> currentMap = new HashMap<>();
			Map<Integer, Integer> tempMap = list.get(kk);
			
			for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) 
			{
				int key1 = entry.getKey();
				int value1 = entry.getValue();
				
				for (Map.Entry<Integer, Integer> entry1 : preMap.entrySet()) 
				{
					int key = entry1.getKey();
					int value = entry1.getValue();
					int xor = key ^ key1;
					currentMap.put(xor, Math.max(currentMap.getOrDefault(xor, 0), value1 + value));
					
					// for the last layer only the xor is equal to 0 you can
					// update the max value
					if (kk == k - 1 && xor == 0 || kk < k - 1) 
					{
						max = Math.max(currentMap.get(xor), max);
					}
				}
				
				currentMap.put(key1, Math.max(currentMap.getOrDefault(key1, 0), value1));
				max = Math.max(value1, max);
			}
			
			preMap = currentMap;
		}

		return nums.length - max;
    }
}
