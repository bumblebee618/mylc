/***
 * 
 * @author jackie
 * 
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

 

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]
 

Constraints:

2 <= nums.length <= 104
1 <= nums[i] <= 104
 */

public class Q645_Set_Mismatch 
{
	// indexing sort, 使用当前的nums作为visited 数组，mark num为负数，表示已经访问过
	// time O(n), space O(1)
	public int[] findErrorNums(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return new int[] {-1, -1};
        }
        
        int dup = -1, missing = 1;

        for (int num : nums)
        {
            if (nums[Math.abs(num)-1] < 0)
            {
                dup = Math.abs(num);
            }
            else
            {
                nums[Math.abs(num)-1] *= -1;
            }
        }
        
        for (int i = 1; i < nums.length; i++) 
        {
            if (nums[i] > 0)
            {
                missing = i + 1;
            }
                
        }
        
        return new int[]{dup, missing};
    }
}
