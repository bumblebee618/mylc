/***
 * 
 * @author jackie
 * 
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M row N matrix, return True if and only if the matrix is Toeplitz.
 

Example 1:

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
Example 2:

Input:
matrix = [
  [1,2],
  [2,2]
]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.

Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

Follow up:

What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
What if the matrix is so large that you can only load up a partial row into the memory at once?
 */
public class Q766_Toeplitz_Matrix {
	// solution 1:
	public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return false;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (i > 0 && j > 0 && matrix[i-1][j-1] != matrix[i][j])
                {
                    return false;
                }
            }
        }
        
        return true;
    }
	
	
	// solution 2:
	public boolean isToeplitzMatrix2(int[][] matrix) 
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return false;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int i = 0; i < col; i++)
        {
            if (!isValid(matrix, 0, i))
            {
                return false;
            }
        }
        
        for (int i = 1; i < row; i++)
        {
            if (!isValid(matrix, i, 0))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isValid(int[][] matrix, int x, int y)
    {
        int target = matrix[x][y];
        
        while (x < matrix.length && y < matrix[0].length)
        {
            if (matrix[x][y] != target)
            {
                return false;
            }
            
            x++;
            y++;
        }
        
        return true;
    }
	
	
	// solution 3
	public boolean isToeplitzMatrix3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return true;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int x_start = row-1, y_start = 0;
        int x = x_start, y = y_start;
        int target = matrix[x][y];
        
        for (int i = 0; i < row*col; i++)
        {
            if (matrix[x][y] != target)
            {
                return false;
            }
            
            x--;
            y--;
            
            if (x < 0 || y < 0)
            {
                x_start = (y_start == col-1) ? x_start-1 : x_start;
                y_start = (y_start == col-1) ? y_start : y_start+1;
                x = x_start;
                y = y_start;
                
                if (x >= 0 && x < row && y >= 0 && y < col)
                {
                    target = matrix[x][y];
                }                
            }
        }
        
        return true;
    }

}
