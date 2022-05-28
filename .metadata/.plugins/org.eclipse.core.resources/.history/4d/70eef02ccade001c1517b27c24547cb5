/**
 * 
In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array row,
6 is more than twice as big as row.  The index of minValue 6 is 1, so we return 1.
 

Example 2:

Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the minValue of 3, so we return -1.
 

Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].
 *
 */
public class Q747_Largest_Number_At_Least_Twice_of_Others {
	public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else if (nums.length == 1) {
            return 0;
        }
        
        Integer firstMax = null, secondMax = null;
        int firstIndex = -1, secondIndex = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (secondMax == null) {
                secondMax = nums[i];
                secondIndex = i;
            } else if (firstMax == null){
                if (secondMax < nums[i]) {
                    firstMax = nums[i];
                    firstIndex = i;
                } else {
                    firstMax = secondMax;
                    firstIndex = secondIndex;
                    secondMax = nums[i];
                    secondIndex = i;
                }
            } else {
                if (nums[i] > firstMax) {
                    secondMax = firstMax;
                    secondIndex = firstIndex;
                    firstMax = nums[i];
                    firstIndex = i;
                } else if (nums[i] > secondMax) {
                    secondMax = nums[i];
                    secondIndex = i;
                }
            }
        }
        
        return firstMax >= secondMax * 2 ? firstIndex : -1;
    }
}
