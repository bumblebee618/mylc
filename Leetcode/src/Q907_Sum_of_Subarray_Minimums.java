import java.util.Arrays;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
 

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
 */
public class Q907_Sum_of_Subarray_Minimums 
{
	// solution 1: 递增栈+DP，time O(n), space O(n)
	public int sumSubarrayMins(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int size = nums.length;
        long result = 0;
        int mod = 1_000_000_000 + 7;
        
        int[] right = new int[size];
        Arrays.fill(right, size);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < size; i++)
        {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()])
            {
                right[stack.pop()] = i;
            }
            
            stack.push(i);
        }
        
        int[] left = new int[size];
        Arrays.fill(left, -1);
        stack = new Stack<>();
        
        for (int i = size-1; i >= 0; i--)
        {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()])
            {
                left[stack.pop()] = i;
            }
            
            stack.push(i);
        }
        
        for (int i = 0; i < size; i++)
        {
            long leftLen = i - left[i];
            long rightLen = right[i] - i;
            result += (leftLen * rightLen % mod) * nums[i] % mod;
            result %= mod;
        }
        
        return (int) result;
    }
    
    
	// solution 2: time O(n^2), space O(1)
    public int sumSubarrayMins2(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int size = nums.length;
        int result = 0;
        int mod = 1_000_000_000 + 7;
        
        for (int i = 0; i < size; i++)
        {
            int min = Integer.MAX_VALUE;
            
            for (int j = i; j < size; j++)
            {
                min = Math.min(min, nums[j]);
                result = (result + min) % mod;
                result %= mod;
            }
        }
        
        return result;
    }
	
    
    
    
    
    
    /**************************************** main  ****************************************/
    
	public static void main(String[] args)
	{
		Q907_Sum_of_Subarray_Minimums test = new Q907_Sum_of_Subarray_Minimums();
		int[] nums1 = {3,1,2,4};
		int[] nums2 = {11,81,94,43,3};
		int[] nums3 = {71,55,82,55};
		System.out.println(test.sumSubarrayMins(nums1));
		System.out.println(test.sumSubarrayMins(nums2));
		System.out.println(test.sumSubarrayMins(nums3));
	}
	
	// 71, 55, 82, 55, 55, 55, 55, 55, 55, 55
}
