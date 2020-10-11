import java.util.*;
/*******
 * 
Given an unsorted integer array, find the first missing positive integer.

For example,
	Given [1,2,0] return 3,
	and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

 * 
 * */

public class Q041_First_Missing_Positive {
	/*************************************************/
	// corner case: [3,4,-1,1]
	
	// solution 1: using hashset, time is O(n), space is O(n)
	public int firstMissingPositive(int[] nums) 
	{
		if (nums == null || nums.length == 0)
		{
            return 1;
        }
        
        Set<Integer> set = new HashSet<Integer>();
        int maxBound = 1;
        
        for (int num : nums)
        {
            set.add(num);
            maxBound = Math.max(maxBound, num);
        }
        
        for (int i = 1; i <= maxBound; i++)
        {
            if (!set.contains(i))
            {
                return i;
            }
        }
        
        return maxBound + 1;
    }
	
	
	// solution 2: using sort, time is O(n), space is O(1)
	public int firstMissingPositive2(int[] nums) 
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
                return i + 1;
            }
        }
        
        return len + 1;
    }
	
	
	
	
	
	
	
	
	
	/*********************** main function **************************/
	
	public static void main(String[] args){
		Q041_First_Missing_Positive test = new Q041_First_Missing_Positive();
//		int[] array = {3,4,-1,1};
		int[] array = {2, 1};
		System.out.println(test.firstMissingPositive(array));
	} 
}
