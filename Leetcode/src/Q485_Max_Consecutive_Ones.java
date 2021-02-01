/*****
 * 
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 * 
 * */

public class Q485_Max_Consecutive_Ones {
	// test case: 
    //      nums = null, nums = []
    //      nums = [1], nums = [0]
    //      nums = [0,0], nums = [1,0]
    //      nums = [1,0,1,1,0,1]
    
    // two pointers, time is O(n)
	public int findMaxConsecutiveOnes(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int front = 0, back = 0;
        int maxLen = 0;
        
        while (front < nums.length)
        {
        	// skip all "0"
            while (front < nums.length && nums[front] == 0)
            {
                front++;
            }
            
            back = front;
            
            // count all "1"
            while (front < nums.length && nums[front] == 1)
            {
                front++;
            }
            
            maxLen = Math.max(maxLen, front-back);
        }
        
        return maxLen;
    }
    
    
	
	// solution 2: sliding window, time is O(n)
    public int findMaxConsecutiveOnes2(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int zeroCount = 0;
        int maxLen = 0;
        
        for (int front = 0, back = 0; front < nums.length; front++)
        {
            if (nums[front] == 0)
            {
                zeroCount++;
            }
            
            while (zeroCount > 0)
            {
                if (nums[back++] == 0)
                {
                    zeroCount--;
                }
            }
            
            maxLen = Math.max(maxLen, front-back+1);
        }
        
        return maxLen;
    }
}
