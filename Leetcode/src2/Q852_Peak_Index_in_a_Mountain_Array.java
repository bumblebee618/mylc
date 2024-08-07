/***
 * 
 * @author jackie
 * 
 * Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1
Note:

3 <= A.length <= 10000
0 <= A[i] <= 10^6
A is a mountain, as defined above.

 */
public class Q852_Peak_Index_in_a_Mountain_Array {
	// solution 1, time complexity is O(logn)
	public int peakIndexInMountainArray(int[] nums) {
        if (nums == null || nums.length < 3)
        {
            return -1;
        }
        
        int left = 0, right = nums.length-1;
        
        while (left+1 < right)
        {
            int mid = left + (right-left)/2;
            
            if (nums[mid] < nums[mid-1])
            {
                right = mid;
            }
            else if (nums[mid] < nums[mid+1])
            {
                left = mid;
            }
            else
            {
                return mid;
            }
        }
        
        return nums[left] > nums[right] ? left : right;
    }
	
	
	// solution 2, 带验证功能, time complexity is O(n)
    public int peakIndexInMountainArray2(int[] nums) {
        if (nums == null || nums.length < 3)
        {
            return -1;
        }
        
        boolean isIncrease = true;
        int candidate = -1;
        
        for (int i = 0; i < nums.length-1; i++)
        {
            if (nums[i] < nums[i+1])
            {
                if (!isIncrease)
                {
                    return -1;
                }
            }
            else if (nums[i] > nums[i+1])
            {
                if (isIncrease)
                {
                    isIncrease = !isIncrease;
                    candidate = i;
                }
            }
            else
            {
                return -1;
            }
        }
        
        return candidate;
    }
}
