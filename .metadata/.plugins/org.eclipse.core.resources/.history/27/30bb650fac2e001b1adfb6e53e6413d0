/***
 * 
 * @author jackie
 * 
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example 1:


Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4
 

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
 */


public class Q463_Island_Perimeter {
	// solution 1: time complexity is O(n^2)
	public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int perimeter = 0;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int neighbour = 0;
                    
                    for (int k = 0; k < 4; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];
            
                        if (newX >= 0 && newX < row && newY >= 0 && newY < col 
                            && grid[newX][newY] == 1) {
                            neighbour++;
                        }
                    }
                    
                    perimeter += 4-neighbour;
                }
            }
        }
        
        return perimeter;
    }
	
	
    
    
    
    /***********************************************************************
    * 1. loop over the matrix and count the number of islands;
    * 2. if the current dot is an island, count if it has any right neighbour or down neighbour;
    * 3. the result is islands * 4 - neighbours * 2
    * 
    * each island grid has four sides, and just need to count right neighbour or down neighbour;
    * each neighbours pairs have two sides. 
    ************************************************************************/
    // solution 2: time is O(n^2)
    public int islandPerimeter2(int[][] grid) {
    	if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        } 
    	
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;      // count islands
                    
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {    // count down neighbours
                    	neighbours++; 
                    }
                    
                    if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {  // count right neighbours
                    	neighbours++; 
                    }
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
    
    
    
 // solution 3: using DFS, TLE
 	public int islandPerimeter3(int[][] grid) {
         if(grid == null || grid.length == 0 || grid[0].length == 0) {
             return 0;
         }    
         
         int row = grid.length, col = grid[0].length;
         boolean[][] visited = new boolean[row][col];
         
         for(int i = 0; i < row; i++) {
             for(int j = 0; j < col; j++) {
                 if(grid[i][j] == 1) {
                     return DFS(grid, visited, i, j);
                 }
             }
         }
         
         return 0;
     }
     
     public int DFS(int[][] grid, boolean[][] visited, int x, int y) {
         visited[x][y] = true;
         int[] dx = {0, 0, 1, -1};
         int[] dy = {1, -1, 0, 0};
         int curCount = getGridPerimeter(grid, x, y);
         
         for(int i = 0; i < 4; i++) {
             int newX = x + dx[i];
             int newY = y + dy[i];
             
             if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !visited[newX][newY] && grid[newX][newY] == 1) {
                 curCount += DFS(grid, visited, newX, newY);
             }
         }
         
         return curCount;
     }
     
     public int getGridPerimeter(int[][] grid, int x, int y) {
         int[] dx = {0, 0, 1, -1};
         int[] dy = {1, -1, 0, 0};
         int count = 0;
         
         for(int i = 0; i < 4; i++) {
             int newX = x + dx[i];
             int newY = y + dy[i];
             
             if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1) {
                 continue;
             } else {
                 count++;
             }
         }
         
         return count;
     }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************** main function *************************************/
    
    public static void main(String[] args) {
    	Q463_Island_Perimeter t = new Q463_Island_Perimeter();
    	int[][] grid = {
    			{0,1,0,0},
    			{1,1,1,0},
    			{0,1,0,0},
    			{1,1,0,0}
    	};
    	
    	System.out.println(t.islandPerimeter(grid));
    }
}
