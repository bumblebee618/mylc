/***
 * 
 * @author jackie
 * 
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 

Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 

Note:

L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
 * 
 */
public class Q1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays {
	// solution 1
	public int maxSumTwoNoOverlap(int[] nums, int L, int M) {
        if (nums == null || nums.length == 0 || L <= 0 || M <= 0 || L+M > nums.length)
        {
            return 0;
        }
        
        return Math.max(nonOverlappedSum(nums, L, M), nonOverlappedSum(nums, M, L));
    }
    
    private int nonOverlappedSum(int[] nums, int L, int M)
    {
        int leftSum = 0, rightSum = 0;
        
        for (int i = 0; i < L; i++)
        {
            leftSum += nums[i];
        }
        
        for (int i = L; i < L+M; i++)
        {
            rightSum += nums[i];
        }
        
        int leftMax = leftSum, result = leftMax + rightSum;
        
        for (int i = L+M; i < nums.length; i++)
        {
            leftSum -= nums[i-L-M];
            leftSum += nums[i-M];
            
            rightSum -= nums[i-M];
            rightSum += nums[i];
            
            leftMax = Math.max(leftMax, leftSum);
            result = Math.max(result, leftMax+rightSum);
        }
        
        return result;
    }

    
    
    
    
    
    // solution 2
    public int maxSumTwoNoOverlap2(int[] nums, int L, int M) {
        if (nums == null || nums.length == 0 || L <= 0 || L > nums.length || M <= 0 || M > nums.length || L+M > nums.length)
        {
            return 0;
        }
        
        int size = nums.length;
        Tuple[] array1 = new Tuple[size-L+1];
        Tuple[] array2 = new Tuple[size-M+1];
        int index1 = 0, index2 = 0;
        int sum1 = 0, sum2 = 0;
        int result = 0;
        
        for (int i = 0; i < size; i++)
        {
            sum1 += nums[i];
            sum2 += nums[i];
            
            if (i >= L)
            {
                sum1 -= nums[i-L];
            }
            
            if (i >= M)
            {
                sum2 -= nums[i-M];
            }
            
            if (i+1 >= L)
            {
                array1[index1++] = new Tuple(sum1, i+1-L, i);
            }
            
            if (i+1 >= M)
            {
                array2[index2++] = new Tuple(sum2, i+1-M, i);
            }
        }
        
        for (Tuple t1 : array1)
        {
            for (Tuple t2 : array2)
            {
                if (!t1.isOverlap(t2))
                {
                    result = Math.max(result, t1.sum+t2.sum);
                }
            }
        }
        
        return result;
    }
    
    class Tuple
    {
        public int sum;
        public int start;
        public int end;
        
        public Tuple(int sum, int start, int end)
        {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
        
        public boolean isOverlap(Tuple other)
        {
            return !(start > other.end || end < other.start);
        }
    }
}
