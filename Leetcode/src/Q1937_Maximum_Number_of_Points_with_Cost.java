/***
 * 
 * @author jackie
 * 
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.
 

Example 1:


Input: points = [[1,2,3],[1,5,1],[3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.
Example 2:


Input: points = [[1,5],[2,3],[4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.
 

Constraints:

m == points.length
n == points[r].length
1 <= m, n <= 105
1 <= m * n <= 105
0 <= points[r][c] <= 105
 */
public class Q1937_Maximum_Number_of_Points_with_Cost 
{
	// Solution 1: time O(m*n*n)
	public long maxPoints(int[][] points) 
    {
        if (points == null || points.length == 0 || points[0].length == 0)
        {
            return 0;
        }
        
        long result = 0;
        int row = points.length, col = points[0].length;
        long[][] dp = new long[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (i == 0)
                {
                    dp[i][j] = points[i][j];
                    continue;
                }
                
                for (int k = 0; k < col; ++k) 
                {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] - Math.abs(k - j) + points[i][j]);
                }
            }
        }
        
        for (long point : dp[row-1]) 
        {
            result = Math.max(result, point);
        }
        
        return result;
    }
    
	
	
	// Solution 2: time O(m*n)
    public long maxPoints2(int[][] points) 
    {
        if (points == null || points.length == 0 || points[0].length == 0)
        {
            return 0;
        }
        
        long result = 0;
        int row = points.length, col = points[0].length;
        long[] dp = new long[col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                dp[j] += points[i][j];
            }
            
            for (int j = 1; j < col; j++) 
            {
                dp[j] = Math.max(dp[j], dp[j-1] - 1);
            }
            
            for(int j = col-2; j >= 0; j--)
            {
                dp[j] = Math.max(dp[j], dp[j+1] - 1);
            }
        }
        
        for(int i = 0; i < col; i++)
        {
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
}
