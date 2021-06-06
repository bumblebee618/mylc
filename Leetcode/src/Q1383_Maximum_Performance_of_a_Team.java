import java.util.*;

/***
 * 
 * @author jackie
 *
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.

Choose at most k different engineers out of the n engineers to form a team with the maximum performance.

The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.

Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.

 

Example 1:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
Output: 60
Explanation: 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
Example 2:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
Output: 68
Explanation:
This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
Example 3:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
Output: 72
 

Constraints:

1 <= <= k <= n <= 105
speed.length == n
efficiency.length == n
1 <= speed[i] <= 105
1 <= efficiency[i] <= 108
 */
public class Q1383_Maximum_Performance_of_a_Team 
{
	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) 
    {
        int modulo = 1_000_000_007;
        
        // build tuples of (efficiency, speed)
        List<Pair> candidates = new ArrayList<>();
        
        for (int i = 0; i < n; ++i) 
        {
            candidates.add(new Pair(efficiency[i], speed[i]));
        }
        
        // sort the members by their efficiencies
        Collections.sort(candidates, (a, b) -> b.efficiency - a.efficiency);

        // create a heap to keep the top (k-1) speeds
        Queue<Integer> speedHeap = new PriorityQueue<>();

        long speedSum = 0, perf = 0;
        
        for (Pair p : candidates) 
        {
            // maintain a heap for the fastest (k-1) speeds
            if (speedHeap.size() > k - 1) 
            {
                speedSum -= speedHeap.remove();
            }
            
            speedHeap.add(p.speed);

            // calculate the maximum performance with
            // the current member as the least efficient one in the team
            speedSum += p.speed;
            perf = Math.max(perf, speedSum * p.efficiency);
        }
        
        return (int) (perf % modulo);
    }
	
	class Pair
	{
		public int efficiency;
		public int speed;
		
		public Pair(int e, int s)
		{
			efficiency = e;
			speed = s;
		}
	}
}
