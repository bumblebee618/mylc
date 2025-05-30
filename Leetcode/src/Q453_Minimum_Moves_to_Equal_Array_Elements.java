import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

 

Example 1:

Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
Example 2:

Input: nums = [1,1,1]
Output: 0
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
The answer is guaranteed to fit in a 32-bit integer.
 */
public class Q453_Minimum_Moves_to_Equal_Array_Elements 
{
	// solution 1: O(n)
	public int minMoves(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return 0;
        }
        
        long sum = 0;
        int min = Integer.MAX_VALUE;
        
        for (int num : nums)
        {
            sum += (long) num;
            min = Math.min(min, num);
        }
        
        return (int) (sum - min * nums.length);
    }
    
	
	// solutin 2: O(nlogn)
    public int minMoves2(int[] nums) 
    {
        if (nums == null || nums.length <= 1)
        {
            return 0;
        }
        
        int result = 0;
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++)
        {
            result += nums[i] - nums[0];
        }
        
        return result;
    }
}
