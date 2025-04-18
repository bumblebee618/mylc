import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
 

Example 1:


Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
Example 2:


Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
 

Constraints:

rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100
 *
 */
public class Q1463_Cherry_Pickup_II {
	// memo search
	private int[] dy = {0, -1, 1};
    private int[][][] memo = new int[71][71][71];
    private int row, col;

    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        row = grid.length;
        col = grid[0].length;

        for (int[][] arr2D : memo) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }
        
        return dfs(grid, 0, 0, col - 1);
    }

    private int dfs(int[][] grid, int curRow, int col1, int col2) {
        if (curRow == row || col1 < 0 || col2 < 0 || col1 >= col || col2 >= col) {
        	return Integer.MIN_VALUE;
        } else if (memo[curRow][col1][col2] != -1) {
        	return memo[curRow][col1][col2];
        }

        memo[curRow][col1][col2] = 0;
        
        for (int i = 0; i < dy.length; i++) {
            for (int j = 0; j < dy.length; j++) {
            	memo[curRow][col1][col2] = Math.max(memo[curRow][col1][col2], dfs(grid, curRow + 1, col1 + dy[i], col2 + dy[j]));
            }
        }

        memo[curRow][col1][col2] += (col1 == col2) ? grid[curRow][col1] : grid[curRow][col1] + grid[curRow][col2];
        return memo[curRow][col1][col2];
    }
}
