import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
You may repeat this procedure as many times as needed.
Return True if it is possible to construct the target array from A otherwise return False.

 

Example 1:

Input: target = [9,3,5]
Output: true
Explanation: Start with [1, 1, 1] 
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
Example 2:

Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].
Example 3:

Input: target = [8,5]
Output: true
 

Constraints:

N == target.length
1 <= target.length <= 5 * 10^4
1 <= target[i] <= 10^9
 */

public class Q1354_Construct_Target_Array_With_Multiple_Sums 
{
	public boolean isPossible(int[] target) 
    {
        if (target == null || target.length == 0)
        {
            return true;
        }
        
        Queue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        int sum = 0;
        
        for (int num : target) 
        {
            sum += num;
            pq.offer(num);
        }
        
        while (pq.peek() != 1) 
        {
            int num = pq.poll();
            sum -= num;
            
            if (num <= sum || sum < 1) 
            {
                return false;
            }
            
            num %= sum;
            sum += num;
            pq.offer(num);
        }
        
        return true;
    }
}
