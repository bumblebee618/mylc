/*****
 * 
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10

Note:
	The matrix is only modifiable by the update function.
	You may assume the number of calls to update and sumRegion function is distributed evenly.
	You may assume that row1 ≤ row2 and col1 ≤ col2.
 
 * 
 * 
 * */

public class Q308_Range_Sum_Query_2D_Mutable {
	// update's time is O(col), sumRegion's time is O(row)
	private int[][] matrix;
    private int[][] rowSum;
    
    public Q308_Range_Sum_Query_2D_Mutable(int[][] matrix) 
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return;
        }
        
        this.matrix = matrix;
        int row = matrix.length;
        int col = matrix[0].length;
        rowSum = new int[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                rowSum[i][j] = (j == 0) ? matrix[i][j] : rowSum[i][j-1] + matrix[i][j];
            }
        }
    }
    
    public void update(int row, int col, int val) 
    {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)
        {
            return;
        }
        
        int different = val - matrix[row][col];
        matrix[row][col] = val;
        
        for (int i = col; i < matrix[0].length; i++)
        {
            rowSum[row][i] += different;
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) 
    {
        if (row1 < 0 || row1 >= matrix.length || row2 < 0 || row2 >= matrix.length || row1 > row2)
        {
            return 0;
        }
        else if (col1 < 0 || col1 >= matrix[0].length || col2 < 0 || col2 >= matrix[0].length || col1 > col2)
        {
            return 0;
        }
        
        long sum = 0;
        
        for (int i = row1; i <= row2; i++)
        {
            int part1 = (col1 == 0) ? 0 : rowSum[i][col1-1];
            sum += rowSum[i][col2] - part1;
        }
        
        return sum >= Integer.MAX_VALUE || sum <= Integer.MIN_VALUE ? 0 : (int) sum;
    }
    
    
    
    
    
    
    
    
    
    /**************************** main function **************************************/
    
    public static void main(String[] args) {
    	int[][] matrix = {
    			{2, 4},
    			{-3, 5}
    	};
    	
    	Q308_Range_Sum_Query_2D_Mutable t = new Q308_Range_Sum_Query_2D_Mutable(matrix);
    	t.update(0, 1, 3);
    	t.update(1, 1, -3);
    	t.update(0, 1, 1);
    	System.out.println(t.sumRegion(0, 0, 1, 1));
    }
}
