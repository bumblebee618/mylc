/***
 * 
 * 
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 

Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 

 *
 ***/


public class Q1004_Max_Consecutive_Ones_III {
	public int longestOnes(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0 || k < 0)
        {
        	return 0;
        }
        
        int count = 0;
        int maxLen = 0;
        
        for (int front = 0, back = 0; front < nums.length; front++)
        {
        	if (nums[front] == 0)
        	{
        		count++;
        	}
        	
        	while (count > k)
        	{
        		if (nums[back++] == 0)
        		{
        			count--;
        		}
        	}
        	
        	maxLen = Math.max(maxLen, front-back+1);
        }
        
        return maxLen;
    }
}
