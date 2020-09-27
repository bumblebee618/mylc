import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
 */
public class Q934_Shortest_Bridge {
	private int[] dx = new int[] {1, -1, 0, 0};
    private int[] dy = new int[] {0, 0, 1, -1};
    
    public int shortestBridge(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0)
        {
            return 0;
        }
        
        int row = nums.length;
        int col = nums[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        
        for (int i = 0; i < row; i++)
        {
            if (found)
            {
                break;
            }
            
            for (int j = 0; j < row; j++)
            {
                if (nums[i][j] == 1)
                {
                    dfs(nums, visited, i, j, queue);
                    found = true;
                    break;
                }
            }
        }
        
        // bfs
        int step = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int[] index = queue.poll();
                
                for (int j = 0; j < dx.length; j++)
                {
                    int newX = index[0] + dx[j];
                    int newY = index[1] + dy[j];
            
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY])
                    {
                        if (nums[newX][newY] == 1)
                        {
                            return step;
                        }
                        
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            
            step++;
        }
        
        return -1;
    }
    
    private void dfs(int[][] nums, boolean[][] visited, int x, int y, Queue<int[]> queue)
    {   
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < nums.length && newY >= 0 && newY < nums[0].length && nums[newX][newY] == 1 && !visited[newX][newY])
            {
                dfs(nums, visited, newX, newY, queue);
            }
        }
    }
}
