import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * You are given two integer arrays nums1 and nums2 of length n.

The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).

For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
Rearrange the elements of nums2 such that the resulting XOR sum is minimized.

Return the XOR sum after the rearrangement.

 

Example 1:

Input: nums1 = [1,2], nums2 = [2,3]
Output: 2
Explanation: Rearrange nums2 so that it becomes [3,2].
The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
Example 2:

Input: nums1 = [1,0,3], nums2 = [5,3,4]
Output: 8
Explanation: Rearrange nums2 so that it becomes [5,4,3]. 
The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
 

Constraints:

n == nums1.length
n == nums2.length
1 <= n <= 14
0 <= nums1[i], nums2[i] <= 107
 */
public class Q1879_Minimum_XOR_Sum_of_Two_Arrays {
	// backtracking + memo search
	public int minimumXORSum(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums1.length != nums2.length) {
			return 0;
		}
		
        Integer[] memo = new Integer[1 << nums2.length];
        return memoSearch(nums1, 0, nums2, 0, memo);
    }
	
	private int memoSearch(int[] nums1, int nums1CurIndex, int[] nums2, int nums2Status, Integer[] memo) {
        if (nums1CurIndex == nums1.length) {
        	return 0;
        } else if (memo[nums2Status] != null) {
        	return memo[nums2Status];
        }
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums2.length; i++) {
            if ((nums2Status & (1 << i)) == 0) {
                min = Math.min(min, (nums1[nums1CurIndex] ^ nums2[i]) + memoSearch(nums1, nums1CurIndex+1, nums2, nums2Status | (1 << i), memo));
            }
        }
        
        memo[nums2Status] = min;
        return memo[nums2Status];
    } 
}
