/***
 * 
 * @author jackie
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

 

Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation: 
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation: 
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation: 
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 

Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
 *
 */
public class Q1060_Missing_Element_in_Sorted_Array {
	// solution 1, using binary search and time complexity is O(logn)
	public int missingElement(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0 || k <= 0)
        {
            return -1;
        }
        
        /***
        int total = nums[nums.length-1]-nums[0]+1;
        int missing = total - nums.length;
        ***/
        int missing = findMissingNumber(nums, nums.length-1);
        
        // result == nums[index] + (k - missing number ranges from 0 to index)
        if (k > missing)
        {
            return nums[nums.length-1] + (k - missing);
        }
        
        int left = 0, right = nums.length-1;
        
        while (left+1 < right)
        {
            int mid = left + (right - left) / 2;
            
            if (k > findMissingNumber(nums, mid))
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        
        if (findMissingNumber(nums, left) == k)
        {
            return nums[left-1] + (k - findMissingNumber(nums, left-1));
        }
        else
        {
            return nums[left] + (k - findMissingNumber(nums, left));
        }
    }
    
    private int findMissingNumber(int[] nums, int pos)
    {
        return nums[pos] - nums[0] - pos;
    }

    
    
    
    
    
    
    // solution 2, time complexity is O(n)
    public int missingElement2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
        {
            return -1;
        }
        
        int total = nums[nums.length-1]-nums[0]+1;
        int missing = total-nums.length;
        
        if (k > missing)
        {
            return nums[nums.length-1] + k - missing;
        }
        
        int result = -1;
        
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i] == nums[i-1] + 1)
            {
                continue;
            }
            
            int gap = nums[i] - nums[i-1] - 1;
            
            if (k <= gap)
            {
                result = nums[i-1] + k;
                k = 0;
                break;
            }
            
            k -= gap;
        }

        return result;
    }
}
