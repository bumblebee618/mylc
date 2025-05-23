import java.util.HashSet;
import java.util.Set;

/****
 * 
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
find the duplicate one.

Note:
	You must not modify the array (assume the array is read only).
	You must use only constant, O(1) extra space.
	Your runtime complexity should be less than O(n2).
	There is only one duplicate number in the array, but it could be repeated more than once.
 * 
 * */

public class Q287_Find_the_Duplicate_Number {
	// brute force method is to sort the array, and then travel the array, time O(nlogn)
	
	// solution 1: time O(n), space O(n)
	public int findDuplicate(int[] nums) 
	{
        if (nums == null || nums.length <= 1) 
        {
            return -1;
        }
        
        Set<Integer> visited = new HashSet<>();
       
        for (int num : nums) 
        {
            if (!visited.add(num)) 
            {
                return num;
            }
        }
        
        return -1;
    }
	
	// 使用indexing sort, time O(n), space O(1)
	public int findDuplicate2(int[] nums) 
    {
        if (nums == null || nums.length == 0) 
        {
            return 1;
        }
        
        int len = nums.length;
        
        for (int i = 0; i < len; i++) 
        {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) 
            {
                int temp = nums[nums[i] - 1];   // nums[nums[i]-1] 必须先来置换 
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        for (int i = 0; i < len; i++) 
        {
            if (nums[i] != i + 1) 
            {
                return nums[i];
            }
        }
        
        return -1;
    }
	
	
	/*****************************************************************************
	 * 	按照链表找圆的方法来处理
	 * 	(1).可以套用此法的条件为：每个nums[i]数值为1-n, 因此不会超出数组的取值范围i.
	 *  (2).将i看作listnode, nums[i]视为listnode.next
	 *   
	 *****************************************************************************/
	
	// solution 2: time O(n), space O(1)
	public int findDuplicate3(int[] nums) 
	{
		if (nums == null || nums.length <= 1) 
		{
            return -1;
        }
        
        int faster = nums[nums[0]];
        int slower = nums[0];
        
        while (faster != slower) 
        {
            faster = nums[nums[faster]];
            slower = nums[slower];
        }
        
        faster = nums[0];
        slower = nums[slower];
        
        while (faster != slower) 
        {
            faster = nums[faster];
            slower = nums[slower];
        }
        
        return faster;
    }
}
