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
	// use dfs + bfs + unionfind
	
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private int maxArea = 0;
    
    public int largestIsland(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] uf = new int[row][col];
        Map<Integer, List<int[]>> islandMap = new HashMap<>();
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1 && !visited[i][j])
                {
                    int parendId = i * col + j + 1; // parentId starts from 1
                    islandMap.put(parendId, new LinkedList<>());
                    dfs(islandMap, grid, visited, uf, i, j, parendId);
                }
            }
        }
        
        if (islandMap.size() == 0)
        {
        	return maxArea + 1;
        }
        
        for (int islandId : islandMap.keySet())
        {
        	// bfs per island
        	bfs(islandMap, grid, uf, visited, islandMap.get(islandId));
        }
        
        return maxArea;
    }
    
    private void dfs(Map<Integer, List<int[]>> islandMap, int[][] grid, boolean[][] visited, int[][] uf, int x, int y, int parendId)
    {
        visited[x][y] = true;
        uf[x][y] = parendId;
        islandMap.get(parendId).add(new int[] {x, y});
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && !visited[newX][newY])
            {
                dfs(islandMap, grid, visited, uf, newX, newY, parendId);
            }
        }
    }
    
    private void bfs(
    		Map<Integer, List<int[]>> islandMap, 
    		int[][] grid, 
    		int[][] uf, 
    		boolean[][] visited,
    		List<int[]> nodes)
    {
    	Queue<int[]> queue = new LinkedList<>();
    	
    	// get all zero nodes
    	for (int[] rootNode : nodes)
    	{
    		int rootIslandId = uf[rootNode[0]][rootNode[1]];
        	maxArea = Math.max(maxArea, islandMap.get(rootIslandId).size());
        	
    		for (int i = 0; i < dx.length; i++)
            {
                int newX = rootNode[0] + dx[i];
                int newY = rootNode[1] + dy[i];
                
                if (newX >= 0 && newX < grid.length 
                	&& newY >= 0 && newY < grid[0].length 
                	&& grid[newX][newY] == 0 
                	&& !visited[newX][newY])
                {
                    queue.offer(new int[] {newX, newY});
                    visited[newX][newY] = true;
                }
            }
    	}
        
        // bfs to find all different islands around zero nodes
        while (!queue.isEmpty())
        {
        	Set<Integer> visitedIslands = new HashSet<>();
        	int area = 1;
        	int[] zeroNode = queue.poll();
        	
        	for (int i= 0; i < dx.length; i++)
            {
                int newX = zeroNode[0] + dx[i];
                int newY = zeroNode[1] + dy[i];
                
                if (newX >= 0 && newX < grid.length 
                    && newY >= 0  && newY < grid[0].length 
                    && grid[newX][newY] == 1 
                    && !visitedIslands.contains(uf[newX][newY]))
                {
                	int area1 = islandMap.get(uf[newX][newY]).size();
                	area += area1;
                	visitedIslands.add(uf[newX][newY]);
                }
            }
        	
        	maxArea = Math.max(maxArea, area);
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