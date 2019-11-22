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
public class Le_695_Max_Area_of_Island {
	public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int maxArea = 0;
        
        for(int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (grid[x][y] == 1 && !visited[x][y]) {
                    maxArea = Math.max(maxArea, DFS(grid, visited, x, y));
                }
            }
        }
        
        return maxArea;
    }
    
    private int DFS (int[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y] == true) {
            return 0;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        visited[x][y] = true;
        int area = 1;
        
        for (int i = 0; i < 4; i++) {
        	int newX = x + dx[i];
        	int newY = y + dy[i];
        	
            if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1) {
                area += DFS(grid, visited, newX, newY);
            }
        }
        
        return area;
    }
    
    public static void main(String[] args) {
    	int[][] grid = {
    			{1,1,0,0,0},
    			{1,1,0,0,0},
    			{0,0,0,1,1},
    			{0,0,0,1,1}
    			};
    	
    	Le_695_Max_Area_of_Island test = new Le_695_Max_Area_of_Island();
    	System.out.println(test.maxAreaOfIsland(grid));
    }
}
