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

public class Q718_Maximum_Length_of_Repeated_Subarray {
	public int findLength(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        
        int len1 = A.length, len2 = B.length;
        int maxLen = 0;
      //dp[i][j] is the length of longest common subarray ending with nums[i] and nums[j]
        int[][] dp = new int[len1][len2];
        
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = (A[i] == B[j]) ? 1 : 0;
                    maxLen = Math.max(dp[i][j], maxLen);
                } else {
                    if (A[i] == B[j]) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                        maxLen = Math.max(dp[i][j], maxLen);
                    }
                }
            }
        }
        
        return maxLen;
    }
}
