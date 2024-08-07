import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 

Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000

 */
public class Q974_Subarray_Sums_Divisible_by_K {
	public int subarraysDivByK(int[] nums, int K) {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int result = 0;
        
        for (int num : nums)
        {
            sum = (sum + num) % K;
            
            if (sum < 0)
            {
                sum += K;
            }
            
            result += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
	
	
	
	public static void main(String[] args)
	{
		Q974_Subarray_Sums_Divisible_by_K test = new Q974_Subarray_Sums_Divisible_by_K();
		// int[] nums = new int[] {4, 5, 0, -2, -3, 1};
		// int[] nums = new int[] {2, -2, 2, -4};
		int[] nums = new int[] {-1, 2, 9};
		int K = 5;
		System.out.println(test.subarraysDivByK(nums, K));
	}
}
