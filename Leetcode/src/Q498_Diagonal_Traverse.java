/**
 * 
 * @author jackie
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

 

Note:

The total number of elements of the given matrix will not exceed 10,000.
 */
public class Q498_Diagonal_Traverse {
	public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return new int[0];
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int len = row*col;
        int[] result = new int[len];
        
        boolean isUp = true;
        int x = 0, y = 0;
        int level = 0;
        
        for (int i = 0; i < len; i++)
        {
            result[i] = matrix[x][y];
            
            if (isUp)
            {
                if (x-1 < 0 || y+1 >= col)
                {
                    isUp = !isUp;
                    level++;
                    y = Math.min(level, col-1);
                    x = level-y;
                }
                else
                {
                    x--;
                    y++;
                }
            }
            else
            {
                if (x+1 >= row || y-1 < 0)
                {
                    isUp = !isUp;
                    level++;
                    x = Math.min(level, row-1);
                    y = level-x;
                }
                else
                {
                    x++;
                    y--;
                }
            }
        }
        
        return result;
    }
}
