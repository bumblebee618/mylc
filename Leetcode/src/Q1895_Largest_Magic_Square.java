/***
 * 
 * @author jackie
 * 
 * A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.

 

Example 1:


Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
Example 2:


Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 106
 */
public class Q1895_Largest_Magic_Square 
{
	private int[][] rowSum;
	private int[][] colSum;
	private int[][] diagSum;
	private int[][] antiDiagSum;
	private int row, col;
	
	public int largestMagicSquare(int[][] grid) 
	{
		if (grid == null || grid.length == 0 || grid[0].length == 0)
		{
			return 0;
		}
		
		row = grid.length; 
		col = grid[0].length;
		int result = 1, side = Math.min(row, col);
		
		rowSum = new int[row][col];
		colSum = new int[row][col];
		diagSum = new int[row][col];
		antiDiagSum = new int[row][col];
		
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				rowSum[i][j] = j > 0 ? rowSum[i][j-1] + grid[i][j] : grid[i][j];
				colSum[i][j] = i > 0 ? colSum[i-1][j] + grid[i][j] : grid[i][j];
				diagSum[i][j] = (i > 0 && j > 0) ? diagSum[i-1][j-1] + grid[i][j] : grid[i][j];
				antiDiagSum[i][j] = (i > 0 && j+1 < col) ? antiDiagSum[i-1][j+1] + grid[i][j] : grid[i][j];
			}
		}
		
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				for (int k = 1; k < side; k++)
				{
					if (i+k >= row || j+k >= col)
					{
						break;
					}
					
					int diagResult = getDiagSum(i, i+k, j, j+k);
					
					if (diagResult != getAntiDiagSum(i, i+k, j+k, j))
					{
						continue;
					}
					
					boolean found = true;
					
					for (int x = 0; x < k; x++)
					{
						if (diagResult != getRowSum(i+x, j, j+k))
						{
							found = false;
							break;
						}
						
						if (diagResult != getColSum(j+x, i, i+k))
						{
							found = false;
							break;
						}
					}
					
					if (found)
					{
						result = Math.max(result, k+1);
					}
				}
			}
		}
		
		return result;
    }
	
	private int getRowSum(int row, int start, int end)
	{
		return start > 0 ? rowSum[row][end] - rowSum[row][start-1] : rowSum[row][end];
	}
	
	private int getColSum(int col, int start, int end)
	{
		return start > 0 ? colSum[end][col] - colSum[start-1][col] : colSum[end][col];
	}
	
	private int getDiagSum(int startRow, int endRow, int startCol, int endCol)
	{
		return startRow > 0 && startCol > 0 ? diagSum[endRow][endCol] - diagSum[startRow-1][startCol-1] : diagSum[endRow][endCol];
	}
	
	private int getAntiDiagSum(int startRow, int endRow, int startCol, int endCol)
	{
		return startRow > 0 && startCol+1 < col ? antiDiagSum[endRow][endCol] - antiDiagSum[startRow-1][startCol+1] : antiDiagSum[endRow][endCol];
	}
}
