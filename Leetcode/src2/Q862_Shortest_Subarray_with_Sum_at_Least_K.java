import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */
public class Q862_Shortest_Subarray_with_Sum_at_Least_K {
	public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0)
        {
            return -1;
        }
        
        int size = A.length;
        long[] sum = new long[size+1];
        
        for (int i = 1; i <= size; ++i)
        {
            sum[i] = sum[i-1] + (long) A[i-1];
        }

        int result = Integer.MAX_VALUE; 
        Deque<Integer> dq = new LinkedList(); 

        for (int i = 0; i <= size; ++i) 
        {
            while (!dq.isEmpty() && sum[i] <= sum[dq.peekLast()])
            {
                dq.removeLast();
            }
                
            while (!dq.isEmpty() && sum[i] >= sum[dq.peekFirst()] + K)
            {
                result = Math.min(result, i - dq.removeFirst());
            }
                
            dq.addLast(i);
        }

        return result != Integer.MAX_VALUE ? result : -1;
    }
}
