/***
 * 
 * @author jackie
 * 
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

 

Example 1:



Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation: 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
Example 2:



Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation: 
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
 

Note:

0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 10
 */
public class Q1066_Campus_Bikes_II {
	public int assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0 || workers[0].length != 2)
        {
            return 0;
        }
        else if (bikes == null || bikes.length == 0 || bikes[0].length != 2)
        {
            return 0;
        }
        else if (workers.length > bikes.length)
        {
            return 0;
        }
        
        int[] memo = new int[1 << bikes.length];
	    return backtrack(workers, bikes, 0, 0, memo);
    }
    
    private int backtrack(int[][] workers, int[][] bikes, int index, int used, int[] memo) 
    {
	    if (index == workers.length) 
        {
            return 0;
        }
        else if (memo[used] > 0) 
        {
            return memo[used];
        }
        
	    int result = Integer.MAX_VALUE;
	
        for (int i = 0; i < bikes.length; i++) 
        {
		    if ((used & (1 << i)) != 0) 
            {
                continue;
            }
            
		    result = Math.min(result, Math.abs(workers[index][0] - bikes[i][0]) + Math.abs(workers[index][1] - bikes[i][1]) + backtrack(workers, bikes, index+1, used | (1<<i), memo));
	    }
        
        memo[used] = result;
	    return memo[used];
    }
}
