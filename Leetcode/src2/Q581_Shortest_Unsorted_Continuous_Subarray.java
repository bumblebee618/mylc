import java.util.Arrays;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

Return the shortest such subarray and output its length.

 

Example 1:

Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Example 2:

Input: nums = [1,2,3,4]
Output: 0
Example 3:

Input: nums = [1]
Output: 0
 

Constraints:

1 <= nums.length <= 104
-105 <= nums[i] <= 105
 

Follow up: Can you solve it in O(n) time complexity?
 */

public class Q581_Shortest_Unsorted_Continuous_Subarray 
{
	// solution 1: use stack, time O(n), space O(n)
	public int findUnsortedSubarray(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int left = nums.length, right = -1;
        
        for (int i = 0; i < nums.length; i++)
        {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()])
            {
                left = Math.min(left, stack.pop());
            }
            
            stack.push(i);
        }
        
        stack.clear();
        
        for (int i = nums.length-1; i >= 0; i--)
        {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()])
            {
                right = Math.max(right, stack.pop());
            }
            
            stack.push(i);
        }
        
        return left == nums.length ? 0 : right-left+1;
    }
    
    
	// solution 2: use sort, time O(nlogn), space O(n)
    public int findUnsortedSubarray2(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return 0;
        }
        
        int[] tmp = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++)
        {
            tmp[i] = nums[i];
        }
        
        Arrays.sort(tmp);
        int left = -1, right = -1;
        
        for (int i = 0; i < nums.length; i++)
        {
            if (tmp[i] != nums[i])
            {
                left = (left == -1) ? i : left;
                right = i;
            }
        }
        
        return left == -1 ? 0 : right-left+1;
    }
}
