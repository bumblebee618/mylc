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
        int[][] uf = new int[row][col];
        Map<Integer, Integer> areaMap = new HashMap<>();
        int maxArea = 0, parentId = -1, curArea = 0;
        Set<Integer> visitedIslands = new HashSet<>();

        for (int i = 0; i < uf.length; i++) {
            Arrays.fill(uf[i], -1);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && uf[i][j] == -1) {
                    parentId = i * col + j;
                    curArea = dfs(i, j, parentId, grid, uf);
                    areaMap.put(parentId, curArea);
                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    curArea = 1;
                    visitedIslands.clear();

                    for (int k = 0; k < dx.length; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];

                        if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1) {
                            parentId = uf[newX][newY];

                            if (!visitedIslands.contains(parentId)) {
                                curArea += areaMap.get(parentId);
                                visitedIslands.add(parentId);
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int x, int y, int parentId, int[][] grid, int[][] uf) {
        uf[x][y] = parentId;
        int area = 1;

        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && uf[newX][newY] == -1) {
                area += dfs(newX, newY, parentId, grid, uf);
            }
        }

        return area;
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
