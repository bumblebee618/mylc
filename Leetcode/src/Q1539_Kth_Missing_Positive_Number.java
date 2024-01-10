/***
 * 
 * @author jackie
 * 
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
 */
public class Q1539_Kth_Missing_Positive_Number {
	public int findKthPositive(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return -1;
        } else if (nums[nums.length-1] - nums.length < k) {
            return nums[nums.length-1] + k - (nums[nums.length-1] - nums.length);
        } else if (nums[0] - 1 >= k) {
            return k;
        }

        int left = 0, right = nums.length-1;

        while (left+1 < right) {
            int mid = left + (right-left)/2;
            
            if (nums[mid] - mid - 1 < k) {
                left = mid;
            } else {
                right = mid;
            }
        }   

        return nums[left] + k - (nums[left] - left - 1);
    }

	
	public int findKthPositive2(int[] arr, int k) 
    {
        if (arr == null || arr.length == 0 || k <= 0)
        {
            return -1;
        }
        
        int numNeed = 1;
        int totalMissingCount = 0;
        
        for (int num : arr)
        {
            if (num != numNeed)
            {
                int missingNum = num - numNeed;
                
                if (totalMissingCount + missingNum >= k)
                {
                    return numNeed + (k-totalMissingCount) - 1;
                }
                
                totalMissingCount += missingNum;
            }
            
            numNeed = num+1;
        }

        // test case: (1, 2, 3, 4), k = 2
        return numNeed + (k-totalMissingCount) - 1;
    }
}
