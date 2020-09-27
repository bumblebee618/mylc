import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */
public class Q1254_Number_of_Closed_Islands {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private int row = 0;
    private int col = 0;
    
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        row = grid.length;
        col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        
        for (int i = 0; i < col; i++)
        {
            if (grid[0][i] == 0 && !visited[0][i])
            {
                bfs(grid, visited, 0, i);
            }
            
            if (grid[row-1][i] == 0 && !visited[row-1][i])
            {
                bfs(grid, visited, row-1, i);
            }
        }
        
        for (int i = 0; i < row; i++)
        {
            if (grid[i][0] == 0 && !visited[i][0])
            {
                bfs(grid, visited, i, 0);
            }
            
            if (grid[i][col-1] == 0 && !visited[i][col-1])
            {
                bfs(grid, visited, i, col-1);
            }
        }
        
        int count = 0;
        
        for (int i = 1; i < row; i++)
        {
            for (int j = 1; j < col; j++)
            {
                if (grid[i][j] == 0 && !visited[i][j])
                {
                    count++;
                    bfs(grid, visited, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void bfs(int[][] grid, boolean[][] visited, int x, int y)
    {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        
        while (!queue.isEmpty())
        {
            int[] node = queue.poll();
            
            for (int i = 0; i < dx.length; i++)
            {
                int newX = node[0] + dx[i];
                int newY = node[1] + dy[i];
                
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 0 && !visited[newX][newY])
                {
                    queue.offer(new int[] {newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
    }
}
