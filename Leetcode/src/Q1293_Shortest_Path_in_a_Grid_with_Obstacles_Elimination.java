import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:

Input: 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10. 
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:

Input: 
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
Output: -1
Explanation: 
We need to eliminate at least two obstacles to find such a walk.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] == 0 or 1
grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class Q1293_Shortest_Path_in_a_Grid_with_Obstacles_Elimination {
	// solution 1, bfs + dp
	public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || k < 0) {
            return -1;
        }    
        
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int row = grid.length, col = grid[0].length, step = 0;    
        int[][] solutions = new int[row][col];  // using dp, == visited
        
        for (int[] solution : solutions) {
            Arrays.fill(solution, Integer.MAX_VALUE);
        }
        
        solutions[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 0});
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                
                if (node[0] == row-1 && node[1] == col-1) {
                    return step;
                }
                
                for (int j = 0; j < dx.length; j++) {
                    int newX = node[0] + dx[j];
                    int newY = node[1] + dy[j];
                    
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col) {
                        int newObs = node[2] + grid[newX][newY];
                        
                        if (newObs > k) {
                            continue;
                        }
                        
                        if (newObs < solutions[newX][newY]) {
                            solutions[newX][newY] = newObs;
                            queue.offer(new int[] {newX, newY, newObs}); 
                        }                    
                    }
                }
            }
            
            step++;
        }
        
        return -1;
    }

	
	// solution 2
	public int shortestPath2(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || k < 0) {
            return -1;
        }    
        
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int row = grid.length, col = grid[0].length, step = 0;
        
        List<Integer>[][] solutions = new List[row][col]; // == visited
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 0});
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                
                if (node[0] == row-1 && node[1] == col-1) {
                    return step;
                }
                
                for (int j = 0; j < dx.length; j++) {
                    int newX = node[0] + dx[j];
                    int newY = node[1] + dy[j];
                    
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col) {
                        int newObstacles = node[2] + grid[newX][newY];
                        
                        if (newObstacles > k) {
                            continue;
                        }
                        
                        if (solutions[newX][newY] == null) {
                            solutions[newX][newY] = new LinkedList<>();
                        }
                        
                        boolean isValid = true;
                        
                        for (int solution : solutions[newX][newY]) {
                            if (newObstacles >= solution) {
                                isValid = false;
                                break;
                            }
                        }
                        
                        if (isValid) {
                            solutions[newX][newY].add(newObstacles);
                            queue.offer(new int[] {newX, newY, newObstacles}); 
                        }
                    }
                }
            }
            
            step++;
        }
        
        return -1;
    }
	
	
	
	
	
	
	/********************************* main *********************************/
	
	public static void main(String[] args) {
		Q1293_Shortest_Path_in_a_Grid_with_Obstacles_Elimination test = new Q1293_Shortest_Path_in_a_Grid_with_Obstacles_Elimination();
		int[][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
		int k = 1;
		
		System.out.println(test.shortestPath(grid, k));
		
	}
}
