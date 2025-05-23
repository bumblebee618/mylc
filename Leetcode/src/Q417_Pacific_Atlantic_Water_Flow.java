import java.util.*;
/******
 * 
Given an m row n matrix of non-negative integers representing the height of each unit cell 
in a continent, the "Pacific ocean" touches the left and top edges of the matrix and 
the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another 
one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic 
ocean.

Note:
	The order of returned grid coordinates does not matter.
	Both m and n are less than 150.

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

 * 
 * */

public class Q417_Pacific_Atlantic_Water_Flow {
	// using DFS
	private int[] dx = {-1, 0, 0, 1};
	private	int[] dy = {0, -1, 1, 0};
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) 
    {
        List<List<Integer>> result = new LinkedList<>();

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        {
			return result;
		}

		int row = matrix.length;
		int col = matrix[0].length;
		boolean[][] visitedAtlantic = new boolean[row][col];
		boolean[][] visitedPacific = new boolean[row][col];

		for (int i = 0; i < row; i++) 
        {
			dfs(matrix, visitedPacific, i, 0);
			dfs(matrix, visitedAtlantic, i, col - 1);
		}

		for (int j = 0; j < col; j++) 
        {
			dfs(matrix, visitedPacific, 0, j);
			dfs(matrix, visitedAtlantic, row - 1, j);
		}

		for (int i = 0; i < row; i++) 
        {
			for (int j = 0; j < col; j++) 
            {
				if (visitedAtlantic[i][j] && visitedPacific[i][j]) 
                {
                    List<Integer> list = new LinkedList<>();
                    list.add(i);
                    list.add(j);
					result.add(list);
				}
			}
		}

		return result;
    }
    
    private void dfs(int[][] matrix, boolean[][] visited, int x, int y) 
    {
		visited[x][y] = true;

		for (int i = 0; i < dx.length; i++) 
        {
		    int newX = x + dx[i];
		    int newY = y + dy[i];
		    
		    if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && !visited[newX][newY] && matrix[newX][newY] >= matrix[x][y]) 
            {
		        dfs(matrix, visited, newX, newY);
		    }
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**************************** main function *****************************/
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 },
				{ 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } };

		// int[][] matrix = {
		// {1,2,3},
		// {8,9,4},
		// {7,6,5}
		// };

		Q417_Pacific_Atlantic_Water_Flow t = new Q417_Pacific_Atlantic_Water_Flow();
		List<List<Integer>> result = t.pacificAtlantic(matrix);

		for (List<Integer> list : result) 
		{
			System.out.println(list.get(0) + ", " + list.get(1));
		}
	}
}
