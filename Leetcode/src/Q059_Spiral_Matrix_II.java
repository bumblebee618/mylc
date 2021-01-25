/******
 * 
 * @author jackie
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

 

Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
 */

public class Q059_Spiral_Matrix_II {
	public int[][] generateMatrix(int n) 
	{
        if (n <= 0) 
        {
            return new int[0][0];
        }
        
        int[][] result = new int[n][n];
        int num = 1;
        int row_start = 0, row_end = n - 1;
        int col_start = 0, col_end = n - 1;
        
        while (row_start <= row_end && col_start <= col_end)
        {
            for (int i = col_start; i <= col_end; i++)
            {
                result[row_start][i] = num++;
            }
            row_start++;
            
            for (int i = row_start; i <= row_end; i++)
            {
                result[i][col_end] = num++;
            }
            col_end--;
                     
            for (int i = col_end; i >= col_start; i--)
            {
                result[row_end][i] = num++;
            }
            row_end--;
            
            for (int i = row_end; i >= row_start; i--)
            {
                result[i][col_start] = num++;
            }
            col_start++;
        }
        
        return result;
    }
}
