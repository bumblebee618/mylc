/***
 * 
 * @author jackie
 * 
 * You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

 

Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3
 

Constraints:

1 <= n <= maxSum <= 109
0 <= index < n
 */
public class Q1802_Maximum_Value_at_a_Given_Index_in_a_Bounded_Array 
{
	public int maxValue(int n, int index, int maxSum) 
    {
        if (n == 1)
        {
            return maxSum;
        }
        else if (n == maxSum)
        {
            return 1;
        }
        
        int leftCount = index;
        int rightCount = n - index - 1;
        
        int left = 1;
        int right = maxSum;
        int result = 0;
        
        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
            long leftSum = findSum(mid-1, leftCount); 
            long rightSum = findSum(mid-1, rightCount);
            
            if (leftSum + rightSum + (long) mid > maxSum) 
            {
                right = mid - 1;
            }
            else 
            {
                result = mid;
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    private long findSum(int num, int count) 
    {
        if (num >= count) 
        {
            return ((long) num + (long) (num - count + 1)) * (long) count / 2;
        } 
        else
        {
            return ((long) num + 1) * (long) num / 2 + (long) (count-num);
        }
    }
}
