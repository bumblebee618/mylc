/***
 * 
 * @author jackie
 * 
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

 

Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10
 

Note: Your solution should run in O(log n) time and O(1) space.
 */
public class Q540_Single_Element_in_a_Sorted_Array {
	public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else if (nums.length == 1) {
            return nums[0];
        }
        
        int left = 0, right = nums.length-1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean isRightEven = (right - mid + 1) % 2 == 0 ? true : false;
            
            if (nums[mid] == nums[mid+1]) {
                if (isRightEven) {
                    right = mid-1;
                } else {
                    left = mid+2;
                }
            } else if (nums[mid] == nums[mid-1]) {
                if (isRightEven) {
                    left = mid+1;
                } else {
                    right = mid-2;
                }
            } else {
                return nums[mid];
            }
        }
        
        return (left-1 >= 0 && nums[left] != nums[left-1]) || (left+1 < nums.length && nums[left] != nums[left+1]) ? nums[left] : -1;
    }
}
