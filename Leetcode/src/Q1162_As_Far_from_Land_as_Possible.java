import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an N row N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.

 

Example 1:



Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: 
The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:



Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: 
The cell (2, 2) is as far as possible from all the land with distance 4.
 

Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
 */
public class Q1162_As_Far_from_Land_as_Possible {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int maxDistance(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return -1;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int result = -1;
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1)
                {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        
        //bfs
        int dist = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            result = Math.max(result, dist);
            
            for (int i = 0; i < size; i++)
            {
                int[] index = queue.poll();
                
                for(int j = 0; j < dx.length; j++)
                {
                    int newX = index[0] + dx[j];
                    int newY = index[1] + dy[j];
            
                    if(newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 0 && !visited[newX][newY])
                    {
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            
            dist++;
        }
        
        return result == 0 ? -1 : result;
    }
}
