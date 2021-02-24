/******
 * 
Given a m row n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 * 
 * */

public class Q064_Minimum_Path_Sum {
	// solution 1: time O(n^2), space O(n^2)
	public int minPathSum(int[][] grid) 
	{
        if (grid == null || grid.length == 0 || grid[0].length == 0) 
        {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        
        for (int i = 0; i < row; i++) 
        {
            for (int j = 0; j < col; j++) 
            {
            	if (i == 0 && j == 0)
            	{
            		dp[i][j] = grid[i][j];
            	}
            	else if (i == 0)
            	{
            		dp[i][j] = dp[i][j - 1] + grid[i][j];
            	}
            	else if (j == 0)
            	{
            		dp[i][j] = dp[i - 1][j] + grid[i][j];
            	}
            	else 
            	{
            		dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
				}
            }
        }
        
        return dp[row - 1][col - 1];
    }
	
	// solution 2: time O(n^2), space O(n), optimize the space complexity
    public int minPathSum2(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[2][col];
        dp[0][0] = grid[0][0];
        
        for(int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        
        for(int i = 1; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(j == 0) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j] + grid[i][j];
                } else {
                    dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]) + grid[i][j];
                }
            }
        }
        
        return dp[(row - 1) % 2][col - 1];
    }
    
    // solution 3: 自底向上，backtrack + memo
    public int minPathSum3(int[][] grid) 
	{
        if (grid == null || grid.length == 0 || grid[0].length == 0) 
        {
            return 0;
        }
        
        Integer[][] memo = new Integer[grid.length][grid[0].length];       
        return search(grid, memo, grid.length-1, grid[0].length-1);
    }
    
    private int search(int[][] grid, Integer[][] memo, int x, int y)
    {
        if (x == 0 && y == 0)
        {
            return grid[0][0];
        }
        else if (memo[x][y] != null)
        {
            return memo[x][y];
        }

        int fromUp = (x-1 >= 0) ? search(grid, memo, x-1, y) : Integer.MAX_VALUE;
        int fromLeft = (y-1 >= 0) ? search(grid, memo, x, y-1) : Integer.MAX_VALUE;
        
        memo[x][y] = Math.min(fromUp, fromLeft) + grid[x][y];
        return memo[x][y];
    }
}
