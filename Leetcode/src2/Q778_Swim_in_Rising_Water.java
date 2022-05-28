import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class Q778_Swim_in_Rising_Water 
{
	private int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
	
	public int swimInWater(int[][] grid) 
	{
		if (grid == null || grid.length == 0 || grid[0].length == 0)
		{
			return 0;
		}
		
        int time = 0;
        int N = grid.length;
        Set<Integer> visited = new HashSet<>();
        
        while(!visited.contains(N*N-1)) 
        {
            visited.clear();
            dfs(grid, 0, 0, time, visited);
            time++;
        }
        
        return time - 1;
    }
    
    private void dfs(int[][] grid, int i, int j, int time, Set<Integer> visited) 
    {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] > time || visited.contains(i*grid.length+j))
        {
        	return;
        }
        
        visited.add(i*grid.length+j);
        
        for (int[] dir : dirs) 
        {
            dfs(grid, i+dir[0], j+dir[1], time, visited);
        }
    }
}
