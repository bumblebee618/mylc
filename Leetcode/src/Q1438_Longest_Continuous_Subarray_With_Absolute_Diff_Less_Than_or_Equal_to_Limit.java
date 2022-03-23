import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

 

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */
public class Q1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit 
{
	// solution 1: Time O(n)
	public int longestSubarray(int[] nums, int limit) 
    {
        if (nums == null || nums.length == 0 || limit < 0)
        {
            return 0;
        }
        
        Deque<Integer> maxDq = new LinkedList<>();
        Deque<Integer> minDq = new LinkedList<>();
        int maxLen = 0;
        
        for (int front = 0, back = 0; front < nums.length; front++)
        {
        	// expends the window size, cannot put "=" here since we need to find the maximum window size
            while (!maxDq.isEmpty() && nums[front] > nums[maxDq.peekLast()])
            {
                maxDq.pollLast();
            }
            
            maxDq.offer(front);
            
            while (!minDq.isEmpty() && nums[front] < nums[minDq.peekLast()])
            {
                minDq.pollLast();
            }
            
            minDq.offer(front);
            
            // decrease window size
            int maxIndex = maxDq.peekFirst();
            int minIndex = minDq.peekFirst();
            
            while (nums[maxIndex] - nums[minIndex] > limit)
            {
                back = Math.min(maxIndex, minIndex);
                
                if (back == maxIndex)
                {
                    maxDq.pollFirst();
                    maxIndex = maxDq.peekFirst();
                }
                else if (back == minIndex)
                {
                    minDq.pollFirst();
                    minIndex = minDq.peekFirst();
                }
                
                back++;
            }
            
            if (nums[maxIndex] - nums[minIndex] <= limit)
            {
                maxLen = Math.max(maxLen, front - back + 1);
            }
        }
        
        return maxLen;
    }
	
	
	
	// solution 2: Time O(nlogn)
	public int longestSubarray2(int[] nums, int limit) {
        if (nums == null || nums.length == 0 || limit < 0) {
            return 0;
        }    
        
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        int result = 0;
        
        for (int front = 0, back = 0; front < nums.length; front++) {            
            while (!maxHeap.isEmpty()) {
                if (maxHeap.peek() < back) {
                    maxHeap.poll();
                } else if (Math.abs(nums[front] - nums[maxHeap.peek()]) > limit) {
                    back = Math.max(back, maxHeap.poll()+1);
                } else {
                    break;
                }
            }
            
            while (!minHeap.isEmpty()) {
                if (minHeap.peek() < back) {
                    minHeap.poll();
                } else if (Math.abs(nums[front] - nums[minHeap.peek()]) > limit) {
                    back = Math.max(back, minHeap.poll()+1);
                } else {
                    break;
                }
            }
            
            maxHeap.offer(front);
            minHeap.offer(front);
            result = Math.max(result, front-back+1);
        }
        
        return result;
    }
}
