/***
 * 
 * @author jackie
 * 
 * You are given a 2D integer array groups of length n. You are also given an integer array nums.

You are asked if you can choose n disjoint subarrays from the array nums such that the ith subarray is equal to groups[i] (0-indexed), and if i > 0, the (i-1)th subarray appears before the ith subarray in nums (i.e. the subarrays must be in the same order as groups).

Return true if you can do this task, and false otherwise.

Note that the subarrays are disjoint if and only if there is no index k such that nums[k] belongs to more than one subarray. A subarray is a contiguous sequence of elements within an array.

 

Example 1:

Input: groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
Output: true
Explanation: You can choose the 0th subarray as [1,-1,0,1,-1,-1,3,-2,0] and the 1st one as [1,-1,0,1,-1,-1,3,-2,0].
These subarrays are disjoint as they share no common nums[k] element.
Example 2:

Input: groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
Output: false
Explanation: Note that choosing the subarrays [1,2,3,4,10,-2] and [1,2,3,4,10,-2] is incorrect because they are not in the same order as in groups.
[10,-2] must come before [1,2,3,4].
Example 3:

Input: groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
Output: false
Explanation: Note that choosing the subarrays [7,7,1,2,3,4,7,7] and [7,7,1,2,3,4,7,7] is invalid because they are not disjoint.
They share a common elements nums[4] (0-indexed).
 

Constraints:

groups.length == n
1 <= n <= 103
1 <= groups[i].length, sum(groups[i].length) <= 103
1 <= nums.length <= 103
-107 <= groups[i][j], nums[k] <= 107
 */
public class Q1764_Form_Array_by_Concatenating_Subarrays_of_Another_Array 
{
	public boolean canChoose(int[][] groups, int[] nums) 
    {
        if (groups == null || groups.length == 0 || nums == null || nums.length == 0) 
        {
            return false;
        }
        
        int nums_index = 0;
        int nums_size = nums.length;
        
        for (int[] group : groups)
        {
            if (group == null || group.length == 0)
            {
                continue;
            }
            
            boolean found = false;
            
            while (!found && group.length <= nums_size - nums_index)
            {
                int offset = 0;
                
                while (offset < group.length)
                {
                    if (group[offset] != nums[nums_index + offset])
                    {
                        nums_index++;
                        break;
                    }
                    
                    offset++;
                }
                
                found = (offset == group.length); 
            }
            
            if (!found)
            {
                return false;
            }
            
            nums_index += group.length;
        }
        
        return true;
    }
}
