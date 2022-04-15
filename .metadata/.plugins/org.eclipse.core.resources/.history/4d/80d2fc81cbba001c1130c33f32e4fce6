import java.util.*;

/**
 * 
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
 *
 */

public class Q718_Maximum_Length_of_Repeated_Subarray 
{
	// Solution 1: Binary Search, time is O(nlogn)
	public int findLength(int[] nums1, int[] nums2) 
    {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
        {
            return 0;
        }
        
        String nums1Str = convertion(nums1);
        String nums2Str = convertion(nums2);
        int left = 1, right = Math.min(nums1.length, nums2.length);
        
        while (left < right) 
        {
            int mid = left + (right - left)/2;
            
            if (binarySearch(nums1Str, nums2Str, mid)) 
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid;
            }
        }
        
        return binarySearch(nums1Str, nums2Str, left) ? left : left - 1;
    }
    
    private String convertion(int[] nums)
    {
        StringBuilder builder = new StringBuilder();
        
        for (int num : nums)
        {
            builder.append((char) num);
        }
        
        return builder.toString();
    }
    
    private boolean binarySearch(String str1, String str2, int guess) 
    {
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i <= str1.length() - guess; i++)
        {
            set.add(str1.substring(i, i+guess));
        }
        
        for (int i = 0; i <= str2.length() - guess; i++)
        {
            if (set.contains(str2.substring(i, i+guess)))
            {
                return true;
            }
        }
        
        return false;
    }
	
    
    
	
	// Solution 2: DP, time is O(n^2)
	public int findLength2(int[] nums1, int[] nums2) 
    {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
        {
            return 0;
        }
        
        // dp[i][j] is the length of longest common subarray ending with nums[i] and nums[j]
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int maxLen = 0;
        
        for (int i = 0; i < nums1.length; i++)
        {
            for (int j = 0; j < nums2.length; j++)
            {
                if (nums1[i] == nums2[j])
                {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    maxLen = Math.max(maxLen, dp[i+1][j+1]);
                }
            }
        }
        
        return maxLen;
    }
}
