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
public class Q542_01_Matrix {
	// 此题无法用DFS解决，因为开始遍历后，从A -> B，然后B又会回到A，因此会造成死循环。
	// 另外，此题用queue保存顺序，可以从0作为起点开始BFS，每当distance有update, 此节点才需要重新入队，再继续update.
	public int[][] updateMatrix(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (mat[i][j] == 0)
                {
                    queue.offer(new int[] {i, j});
                }
                else if (mat[i][j] == 1)
                {
                    mat[i][j] = -1;
                }
            }
        }
        
        int distance = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int[] index = queue.poll();
                
                if (mat[index[0]][index[1]] == -1)
                {
                    mat[index[0]][index[1]] = distance;
                }
                
                for (int j = 0; j < dx.length; j++)
                {
                    int nextX = index[0] + dx[j];
                    int nextY = index[1] + dy[j];
                    
                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && mat[nextX][nextY] == -1)
                    {
                    	queue.offer(new int[] {nextX, nextY});
                    }
                }
            }
            
            distance++;
        }
        
        return mat;
    }
}
