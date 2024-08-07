/***
 * 
 * @author jackie
 * 
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 

Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 */
public class Q689_Maximum_Sum_of_3_Non_Overlapping_Subarrays {
	// solution 1
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < 3*k)
        {
            return new int[0];
        }
        
        int size = nums.length;
        int[] sum = new int[size+1];
        
        for (int i = 0; i < size; i++)
        {
            sum[i+1] = sum[i] + nums[i];
        }
        
        int[] leftStartIndex = new int[size];
        int leftMax = sum[k] - sum[0];
        
        for (int i = k; i < size; i++)
        {
        	// Don't need to update when there are multiple answers and get smaller one for lexicographically smaller requirement
            if (leftMax < sum[i+1] - sum[i+1-k])
            {
                leftMax = sum[i+1] - sum[i+1-k];
                leftStartIndex[i] = i + 1 - k;
            }
            else
            {
                leftStartIndex[i] = leftStartIndex[i-1];
            }
        }
        
        int[] rightStartIndex = new int[size];
        int rightMax = sum[size] - sum[size-k];
        rightStartIndex[size-k] = size - k;
        
        for (int i = size-1-k; i >= 0; i--)
        {
        	// need to update when there are multiple answers and get smaller one for lexicographically smaller requirement
            if (rightMax <= sum[i+k]-sum[i])
            {
                rightMax = sum[i+k]-sum[i];
                rightStartIndex[i] = i;
            }
            else
            {
                rightStartIndex[i] = rightStartIndex[i+1];
            }
        }

        int globalMax = Integer.MIN_VALUE;
        int[] result = new int[3];
        
        for (int i = k; i <= size-2*k; i++)
        {
            int l = leftStartIndex[i-1];
            int r = rightStartIndex[i+k];
            
            // left + middle + right 
            int total = (sum[l+k]-sum[l]) + (sum[i+k]-sum[i]) + (sum[r+k]-sum[r]);
            
            if (total > globalMax)
            {
                globalMax = total;
                result[0] = l;
                result[1] = i;
                result[2] = r;
            }
        }
        
        return result;
    }


	
	
	
	
	// solution 2
	public int[] maxSumOfThreeSubarrays2(int[] nums, int k) 
	{
		if (nums == null || nums.length < k*3)
		{
			return new int[0];
		}
		
		int len = nums.length;
        int sum[] = new int[len+1];
        
        for (int i = 1; i <= len; i++) 
        {
            sum[i] = nums[i-1] + sum[i-1];
        }

        int[][] dp  = new int[len+1][4];
        int[][] from = new int[len+1][4];
        int[] result = new int[3];
        
        for (int i = 1; i < 4; i++) 
        {
            for (int j = 1; j <= len; j++) 
            {
                if (j >= k) 
                {
                	if (dp[j-1][i] < dp[j-k][i-1] + sum[j]-sum[j-k])
                	{
                		dp[j][i] = dp[j-k][i-1] + sum[j]-sum[j-k];
                		from[j][i] = j-k;
                	}
                	else
                	{
                		dp[j][i] = dp[j-1][i];
                		from[j][i] = from[j-1][i];
                	}
                }
            }
        }

        // Retrieve the index result.
        int start = from[len][3];
        int index = 2;
        
        while (index >= 0)
        {
        	result[index] = start;
        	start = from[start][index];
            index--;
        } 
        
        return result;
	}
	

	
	
	/********************** main ***************************/
	
	public static void main(String[] args)
	{
		Q689_Maximum_Sum_of_3_Non_Overlapping_Subarrays test = new Q689_Maximum_Sum_of_3_Non_Overlapping_Subarrays();
		int[] nums = {1,2,1,2,6,7,5,1};
		int[] nums2 = {18,11,14,7,16,4,18,11,4,8};
		
		test.maxSumOfThreeSubarrays(nums2, 2);
	}
}
