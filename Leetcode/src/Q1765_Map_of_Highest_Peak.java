import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

If isWater[i][j] == 0, cell (i, j) is a land cell.
If isWater[i][j] == 1, cell (i, j) is a water cell.
You must assign each cell a height in a way that follows these rules:

The height of each cell must be non-negative.
If the cell is a water cell, its height must be 0.
Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
Find an assignment of heights such that the maximum height in the matrix is maximized.

Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.

 

Example 1:



Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.
Example 2:



Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 

Constraints:

m == isWater.length
n == isWater[i].length
1 <= m, n <= 1000
isWater[i][j] is 0 or 1.
There is at least one water cell.
 */
public class Q1765_Map_of_Highest_Peak 
{
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int[][] highestPeak(int[][] isWater) 
    {
        if (isWater == null || isWater.length == 0 || isWater[0].length == 0)
        {
            return new int[0][0];
        }
        
        int row = isWater.length, col = isWater[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] result = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (isWater[i][j] == 1)
                {
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        int height = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int[] node = queue.poll();
                result[node[0]][node[1]] = height;
                
                for (int j = 0; j < dx.length; j++)
                {
                    int newX = node[0] + dx[j];
                    int newY = node[1] + dy[j];
                    
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY])
                    {
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            
            height++;
        }
        
        return result;
    }
}
