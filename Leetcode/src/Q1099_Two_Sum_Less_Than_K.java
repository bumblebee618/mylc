import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

 

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation: 
We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: 
In this case it's not possible to get a pair sum less that 15.
 

Note:

1 <= A.length <= 100
1 <= A[i] <= 1000
1 <= K <= 2000
 */
public class Q1099_Two_Sum_Less_Than_K {
	public int twoSumLessThanK(int[] A, int K) {
        if (A == null || A.length < 2)
        {
            return -1;
        }
        
        Arrays.sort(A);
        int left = 0, right = A.length-1;
        int result = -1;
        int gap = Integer.MAX_VALUE;
        
        while (left < right)
        {
            int sum = A[left]+A[right];
            
            if (K > sum && K-sum < gap)
            {
                gap = K-sum;
                result = sum;
            }
            
            if (sum > K)
            {
                right--;
            }
            else
            {
                left++;
            }
        }
        
        return result;
    }
}
