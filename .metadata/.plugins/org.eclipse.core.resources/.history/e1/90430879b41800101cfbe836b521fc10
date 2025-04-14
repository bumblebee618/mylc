/***
 * 
 * @author jackie
 * 
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Example 2:


Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
 

Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
 * 
 */
public class Q576_Out_of_Boundary_Paths {
	public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (m <= 0 || n <= 0 || maxMove <= 0 || startRow < 0 || startRow >= m || startColumn < 0 || startColumn > n) {
            return 0;
        }
    
        int MOD = 1000_000_007;
        int dp[][] = new int[m][n];
        dp[startRow][startColumn] = 1;
        int count = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
    
        for (int moves = 1; moves <= maxMove; moves++) {
            int[][] temp = new int[m][n];
            
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (x == m - 1) {
                        count = (count + dp[x][y]) % MOD;
                    }

                    if (x == 0) {
                        count = (count + dp[x][y]) % MOD;
                    }

                    if (y == n - 1) {
                        count = (count + dp[x][y]) % MOD;
                    }

                    if (y == 0) {
                        count = (count + dp[x][y]) % MOD;
                    }

                    for (int i = 0; i < dx.length; i++) {
                        int nextX = x + dx[i];
                        int nextY = y + dy[i];

                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                            temp[x][y] = (temp[x][y] + dp[nextX][nextY]) % MOD;
                        }

                    }
                }
            }

            dp = temp;
        }

         return count;
    }
}
