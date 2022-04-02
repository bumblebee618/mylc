import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has minValue grid[0][0])
C_k is at location (N-1, N-1) (ie. has minValue grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

 

Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4

 

Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
 */
public class Q1091_Shortest_Path_in_Binary_Matrix {
	private int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        } else if (grid[0][0] == 1) {
            return -1;
        }
        
        int row = grid.length, col = grid[0].length;
        int step = 0;
        
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[] {0, 0});
        
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                
                if (current[0] == row-1 && current[1] == col-1) {
                    return step;
                }
                
                for (int j = 0; j < dx.length; j++) {
                    int newX = current[0] + dx[j];
                    int newY = current[1] + dy[j];
                
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        
        return -1;  
    }
}
