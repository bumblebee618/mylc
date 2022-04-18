import java.util.*;

/***
 * 
 * @author jackie
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.
 */
public class Q827_Making_A_Large_Island {
	// use dfs + unionfind
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int maxArea = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int[][] uf = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && uf[i][j] == 0) {
                    int id = i * col + j + 1;
                    dfs(i, j, grid, map, uf, id);
                }
            }
        }
        
        for (int area : map.values()) {
            maxArea = Math.max(maxArea, area);
        }
        
        Set<Integer> islands = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    islands.clear();
                    int area = 1;
                    
                    for (int k = 0; k < dx.length; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];
            
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1) {
                            if (!islands.contains(uf[newX][newY])) {
                                islands.add(uf[newX][newY]);
                                area += map.get(uf[newX][newY]);
                            }
                        }
                    }
                    
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    private void dfs(int x, int y, int[][] grid, Map<Integer, Integer> map, int[][] uf, int id) {
        uf[x][y] = id;
        map.put(id, map.getOrDefault(id, 0)+1);
        
        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && uf[newX][newY] == 0) {
                dfs(newX, newY, grid, map, uf, id);
            }
        }
    }
	
    
    
    
    /******************************** main ********************************/
    
    public static void main(String[] args)
    {
    	Q827_Making_A_Large_Island test = new Q827_Making_A_Large_Island();
    	int[][] grid1 = {{0, 0}, {0, 0}};
    	int[][] grid2 = {{0, 0}, {0, 1}};
    	int[][] grid3 = {{1, 0}, {0, 1}};
    	int[][] grid4 = {{1, 1}, {1, 0}};
    	int[][] grid5 = {
    			{0,0,0,0,0,0,0},
    			{0,1,1,1,1,0,0},
    			{0,1,0,0,1,0,0},
    			{1,0,1,0,1,0,0},
    			{0,1,0,0,1,0,0},
    			{0,1,0,0,1,0,0},
    			{0,1,1,1,1,0,0}
    		};
    	
    	System.out.println(test.largestIsland(grid1));
    	System.out.println(test.largestIsland(grid2));
    	System.out.println(test.largestIsland(grid3));
    	System.out.println(test.largestIsland(grid4));
    	System.out.println(test.largestIsland(grid5));
    }
}
