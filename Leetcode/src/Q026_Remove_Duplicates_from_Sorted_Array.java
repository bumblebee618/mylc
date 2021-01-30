/**********************
 * 
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.

 * 
 * 
 * */



public class Q026_Remove_Duplicates_from_Sorted_Array {
	public int removeDuplicates(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int front = 0, back = 0;
        
        while (front < nums.length)
        {
            int target = nums[front];
            
            while (front < nums.length && nums[front] == target)
            {
                front++;
            }
            
            nums[back++] = target;
        }
        
        return back;
    }
}
