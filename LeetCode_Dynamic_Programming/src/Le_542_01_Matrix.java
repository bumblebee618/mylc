import java.util.LinkedList;
import java.util.Queue;

/**
 * 
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 *
 */
public class Le_542_01_Matrix {
	// 此题无法用DFS解决，因为开始遍历后，从A -> B，然后B又会回到A，因此会造成死循环。
	// 另外，此题用queue保存顺序，可以从0作为起点开始BFS，每当distance有update, 此节点才需要重新入队，再继续update.
	public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dist = new int[row][col];
        Queue<Tuple> queue = new LinkedList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new Tuple(i, j));
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int newX = t.x + dx[i];
                int newY = t.y + dy[i];
            
                if (newX >= 0 && newX < row && newY >= 0 && newY < col) {
                    if (dist[newX][newY] > dist[t.x][t.y] + 1) {
                        dist[newX][newY] = dist[t.x][t.y] + 1;
                        queue.offer(new Tuple(newX, newY));
                    }
                }
            }
        }
        
        return dist;
    }
    
    class Tuple {
        int x;
        int y;
        
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
