/******
 * 
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.

 * 
 * 
 * */


public class Le_027_Remove_Element {
	public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int front = 0, nextInsertPos = 0;
        
        while(front < len) {
            if(nums[front] != val) {
                nums[nextInsertPos++] = nums[front];
            }
            
            front++;
        }
        
        return nextInsertPos;
    }
}
