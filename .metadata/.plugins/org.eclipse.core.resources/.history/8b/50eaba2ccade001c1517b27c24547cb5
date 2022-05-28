import java.util.Arrays;

/**
 * 
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 *
 */
public class Q611_Valid_Triangle_Number 
{
	public int triangleNumber(int[] nums) 
	{
        if (nums == null || nums.length == 0) 
        {
            return 0;
        }
        
        Arrays.sort(nums);
        int count = 0;
        
        for (int i = nums.length-1; i >= 2; i--) 
        {
            int left = 0, right=i-1;
            
            while (left < right) 
            {
                int result = nums[left]+nums[right]-nums[i];
                
                if (result > 0) 
                {
                    count += right-left;
                    right--;
                } 
                else 
                {
                    left++;
                }
            }
        }
        
        return count;
    }
}
