/***
 * 
 * @author jackie
 * 
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.

 

Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]


 

Example 2:

Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]


 

Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C
 */
public class Q885_Spiral_Matrix_III {
	public int[][] spiralMatrixIII(int row, int col, int r0, int c0) {
        if (row <= 0 || col <= 0 || r0 < 0 || c0 < 0)
        {
            return new int[0][0];
        }
        
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};
        int[][] result = new int[row*col][2];
        int index = 0;
        
        result[index++] = new int[] {r0, c0};
        
        if (row * col == 1)
        {
            return result;
        }
        
        for (int k = 1; k < 2*(row+col); k += 2)
        {
            for (int i = 0; i < 4; i++)  // direction index
            {
                int dk = k + (i/2);  // number of steps in this direction
                
                for (int step = 0; step < dk; step++)
                {
                    r0 += dx[i];
                    c0 += dy[i];
                    
                    if (r0 >= 0 && r0 < row && c0 >= 0 && c0 < col)
                    {
                        result[index++] = new int[] {r0, c0};
                        
                        if (index == row * col)
                        {
                            return result;
                        }
                    }
                }
            }
        }
        
        return new int[0][0];
    }
}
