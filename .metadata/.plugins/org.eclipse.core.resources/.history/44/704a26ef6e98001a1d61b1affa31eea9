import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class Q410_Split_Array_Largest_Sum {
	public int splitArray(int[] nums, int m) {
        if (nums == null || m <= 0 || nums.length < m)
        {
            return 0;
        }
        
        int left = 0, right = 0;
        
        for (int num : nums)
        {
            left = Math.max(left, num);
            right += num;
        }
        
        while (left+1 < right)
        {
            int mid = left + (right-left)/2;
            
            if (!canSpit(nums, m, mid))
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        
        return canSpit(nums, m, left) ? left : right; 
    }
    
    private boolean canSpit(int[] nums, int m, int target)
    {
        int count = 1;
        int sum = 0;
        
        for (int num : nums)
        {
            sum += num;
            
            if (sum > target)
            {
                sum = num;
                count++;
            }
            
            if (count > m)
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    // f[i][j]: the minimum largest subarray sum for splitting nums[0..i] into j parts.
    public int splitArray2(int[] nums, int m) {
        if (nums == null || m <= 0 || nums.length < m)
        {
            return 0;
        }
        
        int n = nums.length;
        int[][] f = new int[n+1][m+1];
        int[] sub = new int[n + 1];
        
        for (int i = 0; i <= n; i++) 
        {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < n; i++) 
        {
            sub[i+1] = sub[i] + nums[i];
        }
        
        f[0][0] = 0;
    
        for (int i = 1; i <= n; i++) 
        {
            for (int j = 1; j <= m; j++) 
            {
                for (int k = 0; k < i; k++) 
                {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j-1], sub[i] - sub[k]));
                }
            }
        }
        
        return f[n][m]; 
    }
}
