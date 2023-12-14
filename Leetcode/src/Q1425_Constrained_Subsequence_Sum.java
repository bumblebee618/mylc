import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

 

Example 1:

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].
Example 2:

Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.
Example 3:

Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].
 

Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 *
 */
public class Q1425_Constrained_Subsequence_Sum {
	// Solution 1: Deque (sliding window), time complexity is O(n)
	public int constrainedSubsetSum(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
		
        Deque<Integer> dq = new LinkedList<>();
        int[] dp_maxSum = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
        	dp_maxSum[i] = nums[i] + (!dq.isEmpty() ? dp_maxSum[dq.peekFirst()] : 0);
        	
        	while (!dq.isEmpty() && dp_maxSum[i] >= dp_maxSum[dq.peekLast()]) {
        		dq.pollLast();
        	}
        	
        	while (!dq.isEmpty() && i - dq.peekFirst() >= k) {
        		dq.pollFirst();
        	}
            
            if (dp_maxSum[i] > 0) {
                dq.offerLast(i);
            }
        }
        
        return Arrays.stream(dp_maxSum).max().getAsInt();
    }
	
	
	
	// Solution 2: DP + Heap, time complexity is O(nlogn)
	public int constrainedSubsetSum2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }

        int[] dp_maxSum = new int[nums.length];
        Queue<Tuple> heap = new PriorityQueue<>((a, b) -> b.maxSum - a.maxSum);

        for (int i = 0; i < nums.length; i++) {
            while (!heap.isEmpty() && i - heap.peek().index > k) {
                heap.poll();
            }

            dp_maxSum[i] = heap.isEmpty() ? nums[i] : Math.max(0, heap.peek().maxSum) + nums[i];
            heap.offer(new Tuple(i, dp_maxSum[i]));
        }

        return Arrays.stream(dp_maxSum).max().getAsInt();
    }

    class Tuple {
        public int index;
        public int maxSum;

        public Tuple (int index, int maxSum) {
            this.index = index;
            this.maxSum = maxSum;
        }
    }
}
