import java.util.ArrayDeque;
import java.util.Deque;

/***
 * 
 * @author jackie
 * 
 * You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 

Constraints:

 1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
 */
public class Q1696_Jump_Game_VI 
{
	public int maxResult(int[] nums, int k) 
    {
        if (nums == null || nums.length == 0 || k <= 0)
        {
            return 0;
        }
        
        int size = nums.length;
        
        // store index of `nums` elements, elements is in decreasing order, the front is the maximum element.
        Deque<Integer> dq = new ArrayDeque<>(); 
        dq.offer(0);
        
        for (int i = 1; i < size; ++i) 
        {
            // nums[i] = max(nums[i-k], nums[i-k+1], .., nums[i-1]) + nums[i] = nums[dq.front()] + nums[i]
            nums[i] = nums[dq.peekFirst()] + nums[i];

            // Add nums[i] to our deque
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) 
            {
                // Eliminate elements less or equal to nums[i]
                dq.pollLast();
            }
            
            dq.offerLast(i);

            // Remove if the last element is out of window size k
            if (i - dq.peekFirst() >= k) 
            {
                dq.pollFirst();
            }
        }
        
        return nums[size - 1];
    }
	
	
	
	
	
	/**************************** main ****************************/
	
	public static void main(String[] args)
	{
		Q1696_Jump_Game_VI test = new Q1696_Jump_Game_VI();
		int[] nums1 = {1,-1,-2,4,-7,3};
		int k1 = 2;
		
		int[] nums2 = {10,-5,-2,4,0,3};
		int k2 = 3;
		
		int[] nums3 = {1,-5,-20,4,-1,3,-6,-3};
		int k3 = 2;
		
		
		// System.out.println(test.maxResult(nums1, k1));
		// System.out.println(test.maxResult(nums2, k2));
		System.out.println(test.maxResult(nums3, k3));
	}
}