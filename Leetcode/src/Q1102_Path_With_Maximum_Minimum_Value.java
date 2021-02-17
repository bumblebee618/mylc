import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
The score of a path is the minimum minValue in that path.  For example, the minValue of the path 8 →  4 →  5 →  9 is 4.
A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 
Example 1:

Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 
Example 2:

Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:

Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
 
Note:
	1. 1 <= R, C <= 100
	2. 0 <= A[i][j] <= 10^9

 */
public class Q1102_Path_With_Maximum_Minimum_Value 
{
	// BFS + Priority Queue
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int maximumMinimumPath(int[][] matrix) 
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;           
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        Queue<Tuple> heap = new PriorityQueue<>((a, b) -> b.minValue - a.minValue);
        heap.offer(new Tuple(0, 0, matrix[0][0]));
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        
        while (!heap.isEmpty())
        {
        	// 此处不可以用常规的基于当前queue size的BFS，会限定最小的步数
            Tuple node = heap.poll();
            
            if (node.x == row-1 && node.y == col-1)
            {
                return node.minValue;
            }
            
            for (int i = 0; i < dx.length; i++)
            {
                int newX = node.x + dx[i];
                int newY = node.y + dy[i];
                
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY])
                {
                    heap.offer(new Tuple(newX, newY, Math.min(node.minValue, matrix[newX][newY])));
                    visited[newX][newY] = true;
                }
            }
        }
        
        return -1;
    }
    
    class Tuple
    {
        public int x;
        public int y;
        public int minValue;
        
        public Tuple(int x, int y, int minValue)
        {
            this.x = x;
            this.y = y;
            this.minValue = minValue;
        }
    }
}
