import java.util.*;
/***
 * 
 * @author jackie
 * 
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

public class Q442_Find_All_Duplicates_in_an_Array 
{
	public List<Integer> findDuplicates(int[] nums) 
	{
        List<Integer> result = new ArrayList<Integer>();
		
		if (nums == null || nums.length == 0)
		{
			return result;
		}
		
		int len = nums.length;
		
		for (int i = 0; i < len; i++)
		{
			while (nums[i] > 0 && nums[i] <= len && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1])
			{
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		
		for (int i = 0; i < len; i++)
		{
			if (nums[i] != i + 1)
			{
				result.add(nums[i]);
			}
		}
		
		return result;
    }
}
