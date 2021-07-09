/***
 * 
 * @author jackie
 * 
 * Given an array nums, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 

Example 1:

Input: nums = [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: nums = [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Note:

2 <= nums.length <= 30000
0 <= nums[i] <= 106
It is guaranteed there is at least one way to partition nums as described.
 */
public class Q915_Partition_Array_into_Disjoint_Intervals {
	public int partitionDisjoint(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int[] leftMax = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++)
        {
            leftMax[i] = (i == 0) ? nums[0] : Math.max(leftMax[i-1], nums[i]);
        }
        
        int result = -1, rightMin = nums[nums.length-1];
        
        for (int i = nums.length-1; i > 0; i--)
        {
            rightMin = Math.min(rightMin, nums[i]);
            
            if (leftMax[i-1] <= rightMin)
            {
                System.out.println(i);
                result = i;
            }
        }
        
        return result;
    }
}
