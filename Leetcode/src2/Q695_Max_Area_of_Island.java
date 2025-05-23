import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
 *
 */
public class Q695_Max_Area_of_Island 
{
	// solution 1:
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int maxAreaOfIsland(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int result = 0;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1 && !visited[i][j])
                {
                    result = Math.max(result, dfs(grid, visited, i, j));
                }
            }
        }
        
        return result;
    }
    
    private int dfs(int[][] grid, boolean[][] visited, int x, int y)
    {
        int count = 1;
        visited[x][y] = true;
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && !visited[newX][newY])
            {
                count += dfs(grid, visited, newX, newY);
            }
        }
        
        return count;
    }

    // solution 2:
    public int maxAreaOfIsland2(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int maxArea = 0;
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1 && !visited[i][j])
                {
                    maxArea = Math.max(maxArea, bfs(grid, visited, i, j));
                }
            }
        }
        
        return maxArea;
    }
    
    private int bfs(int[][] grid, boolean[][] visited, int x, int y)
    {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        int area = 1;
        
        while (!queue.isEmpty())
        {
            int[] node = queue.poll();
            
            for (int i = 0; i < dx.length; i++)
            {
                int newX = node[0] + dx[i];
                int newY = node[1] + dy[i];
                
                if (newX >= 0 && newX < grid.length 
                	&& newY >= 0 && newY < grid[0].length 
                	&& grid[newX][newY] == 1 && !visited[newX][newY])
                {
                    queue.offer(new int[] {newX, newY});
                    area++;
                    visited[newX][newY] = true;
                }
            }
        }
        
        return area;
    }
    
    
    
    
    
    /************************************* main *************************************/ 
    
    public static void main(String[] args) 
    {
    	int[][] grid = {
    			{1,1,0,0,0},
    			{1,1,0,0,0},
    			{0,0,0,1,1},
    			{0,0,0,1,1}
    			};
    	
    	Q695_Max_Area_of_Island test = new Q695_Max_Area_of_Island();
    	System.out.println(test.maxAreaOfIsland(grid));
    	System.out.println(test.maxAreaOfIsland2(grid));
    }
}
