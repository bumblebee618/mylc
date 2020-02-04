/***
 * 
 * @author jackie
 * 
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class Q562_Longest_Line_of_Consecutive_One_in_Matrix {
	public int longestLine(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int result = 0;
        int[][][] dp = new int[row][col][4];
        
        for (int i = 0; i < row; i++) 
        {
            for (int j = 0; j < col; j++) 
            {
                if (matrix[i][j] == 1) 
                {
                    dp[i][j][0] = j > 0 ? dp[i][j-1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i-1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i-1][j-1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j < col - 1) ? dp[i-1][j+1][3] + 1 : 1;
                    result = Math.max(result, Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                }
            }
        }
        
        return result;
    }
}
