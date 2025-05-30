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
	public int removeDuplicates(int[] nums) {
        if(nums == null) {
            return 0;
        } else if(nums.length <= 1) {
            return nums.length;
        }
        
        int front = 0, back = 0;
        int len = nums.length;
        
        while(front < len) {
            if(nums[front] != nums[back]) {
                nums[++back] = nums[front];
            }
            
            front++;
        }
        
        return back + 1;
    }
}
