import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.

In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.

Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.

 

Example 1:

Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
- Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
- Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
Example 2:

Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
Output: -1
Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
Example 3:

Input: nums1 = [6,6], nums2 = [1]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed. 
- Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
- Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
- Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 

Constraints:

1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[i] <= 6
 */
public class Q1775_Equal_Sum_Arrays_With_Minimum_Number_of_Operations 
{
	public int minOperations(int[] nums1, int[] nums2) 
    {
        if (nums1 == null || nums1.length == 0 
        	|| nums2 == null || nums2.length == 0)
        {
            return -1;
        }
        
        int size1 = nums1.length, size2 = nums2.length;
        int max1 = size1 * 6, min1 = size1;
        int max2 = size2 * 6, min2 = size2;
        
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum(); 
        
        if (max1 < min2 || max2 < min1)
        {
            return -1;
        }
        else if (sum1 == sum2)
        {
            return 0;
        }
        
        int diff = Math.abs(sum1 - sum2);
        
        int[] minArray = sum1 < sum2 ? nums1 : nums2;
        int[] maxArray = sum1 < sum2 ? nums2 : nums1;
        Arrays.sort(minArray);
        Arrays.sort(maxArray);
        
        int index1 = 0, index2 = maxArray.length-1;
        int step = 0;
        
        while (diff > 0)
        {
            step++;
            
            if ((index1 < minArray.length && diff <= 6-minArray[index1]) 
                || (index2 >= 0 && diff < maxArray[index2]-1))
            {
                break;
            }
            
            if (index1 < minArray.length && index2 >= 0)
            {
                if (6-minArray[index1] > maxArray[index2]-1)
                {
                    diff -= 6-minArray[index1++];
                }
                else
                {
                    diff -= maxArray[index2--]-1;
                }
            }
            else if (index2 >= 0)
            {
                diff -= maxArray[index2--]-1;
            }
            else
            {
                diff -= 6-minArray[index1++];
            }
        }
        
        return step;
    }
}
