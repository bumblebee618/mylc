/***
 * 
 * @author jackie
 *
 *Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
 */
public class Q977_Squares_of_a_Sorted_Array {
	public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return new int[0];
        }
        
        int size = nums.length;
        int[] result = new int[size];
        int index = size-1;
        int left = 0, right = size-1;
        
        while (left <= right)
        {
            if (Math.abs(nums[left]) > Math.abs(nums[right]))
            {
                result[index--] = nums[left]*nums[left];
                left++;
            }
            else
            {
                result[index--] = nums[right]*nums[right];
                right--;
            }
        }
        
        return result;
    }
}
