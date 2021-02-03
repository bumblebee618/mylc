/***
 * 
 * @author jackie
 * 
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

A subarray is a contiguous subsequence of the array.

Return the length of the shortest subarray to remove.

 

Example 1:

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
Example 2:

Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
Example 3:

Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any elements.
Example 4:

Input: arr = [1]
Output: 0
 

Constraints:

1 <= arr.length <= 10^5
0 <= arr[i] <= 10^9
 */
public class Q1574_Shortest_Subarray_to_be_Removed_to_Make_Array_Sorted 
{
	public int findLengthOfShortestSubarray(int[] nums) 
	{
        if (nums == null || nums.length <= 1) 
        {
        	return 0;
        }
        
        int firstPos = -1, lastPos = -1;
        int size = nums.length;
        
        for (int i = 0; i < size-1; i++)
        {
            if (nums[i] > nums[i+1])
            {
                if (firstPos == -1)
                {
                    firstPos = i;
                    lastPos = i;
                }
                else
                {
                    lastPos = i;
                }
            }
        }
        
        if (firstPos == -1)
        {
            return 0;
        }
        
        int index1 = firstPos;
        int index2 = lastPos+1;
        int deleteCount = Math.min(index2, size-(index1+1));
        
        for (int i = 0; i <= index1; i++)
        {
            int targetIndex = findNextGreater(nums, index2, size-1, nums[i]);
            
            if (targetIndex == -1)
            {
                break;
            }
            
            deleteCount = Math.min(deleteCount, (targetIndex-1)+1 - (i+1));
        }
        
        return deleteCount;
    }

	// use binary search:
    private int findNextGreater(int[] nums, int left, int right, int target)
    {
    	while (left+1 < right)
    	{
    		int mid = left + (right - left) / 2;
    		
    		if (nums[mid] < target)
    		{
    			left = mid;
    		}
    		else
    		{
    			right = mid;
    		}
    	}
    	
    	if (nums[left] >= target)
    	{
    		return left;
    	}
    	else if (nums[right] >= target)
    	{
    		return right;
    	}
    	else 
    	{
    		return -1;
		}
    }
	
	
	
	
	public static void main(String[] args)
	{
		Q1574_Shortest_Subarray_to_be_Removed_to_Make_Array_Sorted test = new Q1574_Shortest_Subarray_to_be_Removed_to_Make_Array_Sorted();
		int[] arr1 = {1,2,3,10,4,2,3,5};
		int[] arr2 = {58,68,54,45,52,21,33,35,54,22,58,13,67,31,25,66,27,75,57,81,30,44,22,45,34,21,8,11,82,60,37,35,3,44,31,80,40,74,1,2,47};
		int[] arr3 = {22,37,59,16,42,32,29,53,9,55,29,3,4,1,49,17,37,31,27,45,33,24,54,51,32,51,54,31,36,53};
		int[] arr4 = {1,2,3,10,0,7,8,9};
		// System.out.println(arr3.length);
		System.out.println(test.findLengthOfShortestSubarray(arr1));
		System.out.println(test.findLengthOfShortestSubarray(arr2));
		System.out.println(test.findLengthOfShortestSubarray(arr3));
		System.out.println(test.findLengthOfShortestSubarray(arr4));
		// System.out.println(test.findLengthOfShortestSubarray2(arr2));
		
		// 1, 2, [3], 2, 3, 5 -> 5
		// 58, [68], 1, 2, 47 -> 3
		// 22, 37, [59], 31, 36, 53 -> 4
		// 1, 2, 3, [10], 0, 7, 8, 9
	}
}
