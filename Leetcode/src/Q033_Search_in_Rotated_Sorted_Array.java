/*******
 * 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target minValue to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.

 * 
 * */

public class Q033_Search_in_Rotated_Sorted_Array {
	/*******************************************************/
	// using binary search, classified discussion
	public int search(int[] nums, int target) 
	{
		if (nums == null || nums.length == 0)
        {
            return -1;
        }
        
        int left = 0, right = nums.length-1;
        
        while (left+1 < right)
        {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target)
            {
                 return mid;
            }
            else if (nums[left] == target)
            {
                return left;
            }
            else if (nums[right] == target)
            {
                return right;
            }
            
            if (nums[mid] > nums[left] || nums[mid] > nums[right])
            {
                if (nums[left] < target && target < nums[mid])
                {
                    right = mid;
                }
                else
                {
                    left = mid;
                }
            }
            else
            {
                if (nums[mid] < target && target < nums[right])
                {
                    left = mid;
                }
                else
                {
                    right = mid;
                }
            }
        }
        
        if (nums[left] == target)
        {
            return left;
        }
        else if (nums[right] == target)
        {
            return right;
        }
        else
        {
            return -1;
        }
    }
}
