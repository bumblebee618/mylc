import java.util.*;

/***
 * 
 * @author jackie
 *
 * In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

 

Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
 */
public class Q994_Rotting_Oranges {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 2)
                {
                    queue.offer(i*col+j);
                }
            }
        }
        
        int minTime = bfs(grid, queue);
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1)
                {
                    return -1;
                }
            }
        }
        
        return minTime == -1 ? 0 : minTime;
    }
    
    private int bfs(int[][] grid, Queue<Integer> queue)
    {
        int row = grid.length;
        int col = grid[0].length;
        int time = -1;
        
        while (!queue.isEmpty())
        {
            time++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int num = queue.poll();
                int curX = num / col;
                int curY = num % col;
            
                for (int j = 0; j < dx.length; j++)
                {
                    int newX = curX + dx[j];
                    int newY = curY + dy[j];
                
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1)
                    {
                        grid[newX][newY] = 0;
                        queue.offer(newX * col + newY);
                    }
                }
            }
            
        }
        
        return time;
    }

    
    
    public static void main(String[] args)
    {
    	Q994_Rotting_Oranges test = new Q994_Rotting_Oranges();
    	int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
    	System.out.println(test.orangesRotting(grid));
    }
}
