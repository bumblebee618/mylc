import java.io.StringReader;
import java.util.*;
/**
 * 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 *
 */
public class Q698_Partition_to_K_Equal_Sum_Subsets 
{
	// solution 1: from bottom to up + memo search
	private Boolean[] memo;
	
	public boolean canPartitionKSubsets(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0 || k == 0) 
        {
            return k == 0;
        } 
        else if (k < 0) 
        {
            return false;
        } 
        
        int sum = Arrays.stream(nums).sum();
        
        if (sum % k != 0) 
        {
            return false;
        }
        
        memo = new Boolean[1 << nums.length];
        
        // init the status "1111...1111" as true: memo[sum] = true;
        // 从后往前 "1111...1111" -> "0000....00000"
        memo[memo.length - 1] = true; 
        return search(nums, 0, sum, sum/k);
	}
	
	private boolean search(int[] nums, int status, int todo, int target)
	{
		if (memo[status] != null)
		{
			return memo[status];
		}
		
		memo[status] = false;
		
		// 这步非常关键，将sum分成K段处理！then change the question to find the close target(sum/k)
		// 因为sum % target = 0 所以需要做这个特殊处理, 因此(sum-1) % target + 1 = target
		int nextTarget = (todo - 1) % target + 1;
		
		for (int i = 0; i < nums.length; i++)
		{
			if ( ((status >> i) & 1) == 0 && nums[i] <= nextTarget )
			{
				int nextStatus = status | (1 << i);
				
				if (search(nums, nextStatus, todo - nums[i], target))
				{
					memo[status] = true;
					break;
				}
			}
		}
		
		return memo[status];
	}
	
	
	
	// solution 2: backtracking
	public boolean canPartitionKSubsets2(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0 || k == 0) 
        {
            return k == 0;
        } 
        else if (k < 0) 
        {
            return false;
        } 
        
        int sum = Arrays.stream(nums).sum();
        
        if (sum % k != 0) 
        {
            return false;
        }
        
        int len = nums.length;
        boolean[] visited = new boolean[len];
        return backtrack(nums, visited, 0, 0, sum/k, k);        
    }
    
    private boolean backtrack(int[] nums, boolean[] visited, int start, int sum, int target, int k) 
    {
        if (k == 1) // no need to check again since sum % k == 0
        {
            return true;
        } 
        else if (sum == target) 
        {
            return backtrack(nums, visited, 0, 0, target, k-1);
        }
        
        for (int i = start; i < nums.length; i++) 
        {
            if (!visited[i]) 
            {
                visited[i] = true;
                
                if (backtrack(nums, visited, i+1, sum+nums[i], target, k)) 
                {
                    return true;
                }
                
                visited[i] = false;
            }
        }
        
        return false;
    }
    
    
    
    
    
    
    /****************************************** main ******************************************/
    
    public static void main(String[] args)
    {
    	Q698_Partition_to_K_Equal_Sum_Subsets test = new Q698_Partition_to_K_Equal_Sum_Subsets();
    	int[] nums = {4,3,2,3,5,2,1};
    	int k = 4;
    	System.out.println(test.canPartitionKSubsets(nums, k));
    }
}
