import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/***
 * 
 * @author jackie
 * 
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
 */
public class Q694_Number_of_Distinct_Islands 
{
	private int[][] grid;
    private boolean[][] visited;
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int numDistinctIslands(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];
        Set<String> shapes = new HashSet<>();

        for (int x = 0; x < grid.length; x++) 
        {
            for (int y = 0; y < grid[0].length; y++) 
            {
                if (grid[x][y] == 1 && !visited[x][y])
                {
                    StringBuilder shape = new StringBuilder();
                    dfs(x, y, 0, shape);
                    
                    if (shape.length() > 0) 
                    {
                        shapes.add(shape.toString());
                    }
                }
            }
        }

        return shapes.size();
    }
    
    private void dfs(int x, int y, int direct, StringBuilder shape) 
    {
        visited[x][y] = true;
        shape.append(direct);
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1 && !visited[newX][newY])
            {
                dfs(newX, newY, i, shape);
            }
        }
        
        // tail, 也可以用其他数字like "5"
        shape.append(dx.length);
    }

    
    
    
    
    
    
    /***************************** main *********************************************/
    
    public static void main(String[] args)
    {
    	Q694_Number_of_Distinct_Islands test = new Q694_Number_of_Distinct_Islands();
    	int[][] grid = {{1,1,0}, {0,1,1}, {0,0,0}, {1,1,1}, {0,1,0}};
    	test.numDistinctIslands(grid);
    }
}
