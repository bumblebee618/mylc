/***
 * 
 * @author jackie
 * 
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
 */
public class Q1905_Count_Sub_Islands 
{
	// dfs
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	
	public int countSubIslands(int[][] grid1, int[][] grid2) 
    {
        int row = grid2.length, col = grid2[0].length;
        boolean[][] visited = new boolean[row][col];
        int result = 0;
        
        for (int i = 0; i < row; i++)
        {
        	for (int j = 0; j < col; j++)
        	{
        		if (!visited[i][j] && grid2[i][j] == 1)
        		{
        			result += dfs(grid1, grid2, visited, i, j) ? 1 : 0;
        		}
        	}
        }
        
        return result;
    }
	
	private boolean dfs(int[][] grid1, int[][] grid2, boolean[][] visited, int x, int y)
	{
		boolean result = true;
		
		if (grid1.length < x || grid1[0].length < y || grid1[x][y] != 1)
		{
			result = false;
		}
		
		for (int i = 0; i < dx.length; i++)
		{
			int newX = x + dx[i];
			int newY = y + dy[i];
			
			if (newX >= 0 && newX < grid2.length 
				&& newY >= 0 && newY < grid2[0].length
				&& grid2[newX][newY] == 1 && !visited[newX][newY])
			{
				visited[newX][newY] = true;
				result &= dfs(grid1, grid2, visited, newX, newY);
			}
		}
		
		return result;
	}
}
