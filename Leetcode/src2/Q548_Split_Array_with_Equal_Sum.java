import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].
 */
public class Q548_Split_Array_with_Equal_Sum 
{
	public boolean splitArray(int[] nums) 
	{
        if (nums.length < 7)
        {
            return false;
        }
            
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int size = nums.length;
        
        for (int i = 1; i < size; i++) 
        {
            sum[i] = sum[i-1] + nums[i];
        }
        
        for (int j = 3; j < size-3; j++) 
        {
            Set<Integer> candidates = new HashSet<>();
            
            for (int i = 1; i < j-1; i++) 
            {
                if (sum[i-1] == sum[j-1] - sum[i])
                {
                    candidates.add(sum[i-1]);
                }
            }
            
            for (int k = j+2; k < size-1; k++) 
            {
                if (sum[size-1] - sum[k] == sum[k-1] - sum[j] && candidates.contains(sum[k-1] - sum[j]))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}
