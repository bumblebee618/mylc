/*****
 * 
Find the contiguous subarray within an array (containing at least one number) 
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * */


public class Q053_Maximum_Subarray 
{
	// solution 1: using DP, time O(n), space O(1)
    public int maxSubArray(int[] nums) 
    {
        if (nums == null || nums.length == 0) 
        {
            return 0;
        }
        
        int curSum = nums[0];
        int globalMaxSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) 
        {
            curSum = Math.max(curSum + nums[i], nums[i]);
            globalMaxSum = Math.max(globalMaxSum, curSum);
        }
        
        return globalMaxSum;
    }
    
    
    
    
    // solution 2: using DP, time O(n), space O(n)
 	public int maxSubArray2(int[] nums) 
 	{
         if (nums == null || nums.length == 0) 
         {
             return 0;
         }
         
         int[] sum = new int[nums.length];
         sum[0] = nums[0];
         int maxSum = nums[0];
         
         for (int i = 1; i < nums.length; i++) 
         {
             sum[i] = Math.max(sum[i-1] + nums[i], nums[i]);
             maxSum = Math.max(maxSum, sum[i]);
         }
         
         return maxSum;
     }
	
    
    
    // solution 3:
	public int maxSubArray3(int[] nums) 
	{
        if (nums == null || nums.length == 0) 
        {
            return 0;
        }
        
        int len = nums.length;
        int localMax = nums[0]; 
        int globalMax = nums[0];
        
        for (int i = 1; i < len; i++) 
        {
            localMax = Math.max(localMax + nums[i], nums[i]);
            globalMax = Math.max(globalMax, localMax);
        }
        
        return globalMax;
    }
}
