/***
 * 
 * @author jackie
 * 
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

 

Example 1:

Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:

Input: nums = [4,2,1]
Output: false
Explanation: You can't get a non-decreasing array by modify at most one element.
 

Constraints:

1 <= n <= 10 ^ 4
- 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class Q665_Non_decreasing_Array 
{
	// solution 1: don't need to modify the array
	public boolean checkPossibility(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return true;
        }
        
        int count = 0;
        
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i-1] > nums[i])
            {
                if ( i-1 == 0 || (i-2 >= 0 && nums[i-2] <= nums[i]) )
                {
                    count++;
                }
                else if ( i == nums.length-1 || (i+1 < nums.length && nums[i-1] <= nums[i+1]) )
                {
                    count++;
                }
                else
                {
                    return false;
                }
            }
            
            if (count > 1)
            {
                return false;
            }
        }
        
        return true;       
    }
	
	// solution 2: Modify the input array
	public boolean checkPossibility2(int[] nums) 
	{
		if (nums == null || nums.length == 1)
	    {
	        return true;
	    }
	        
	    int count = 0;
	        
	    for (int i = 1; i < nums.length; i++)
	    {
	    	if (nums[i] < nums[i-1])
	        {
	    		if (count++ > 0)
	            {
	                return false;
	            }
	                
	            if (i - 2 < 0 || nums[i - 2] <= nums[i])
	            {
	                nums[i - 1] = nums[i]; // lower a[i - 1]
	            }
	            else 
	            {
	                nums[i] = nums[i - 1]; // rise a[i]
	            }
	        }
	    }
	        
	    return true;
    }

	
	// solution 3:
	/***	
	Without modify the input array
	We can also do it without modifying the input by using a variable prev to hold the a[i-1]; if we have to lower a[i] to match a[i-1] instead of raising a[i-1], simply skip updating prev; 

	Two situtations to modify the array element, current one or previous one. perv is used to keep record  minValue of last array element
	***/
	public boolean checkPossibility3(int[] nums) 
	{
	        if (nums == null || nums.length == 1)
	        {
	            return true;
	        }
	        
	        int count = 0;
	        int prev = nums[0];
	        
	        for (int i = 0; i < nums.length; i++)
	        {
	            if (nums[i] < prev)
	            {
	                if (count++ == 1)
	                {
	                    return false;
	                }
	                
	                if (i >= 2 && nums[i-2] > nums[i])
	                {
	                    continue;
	                }
	            }
	            
	            prev = nums[i];
	        }
	        
	        return true;
	    }
}
