import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

/***
 * 
 * @author jackie
 * 
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
 */
public class Q1329_Sort_the_Matrix_Diagonally 
{
	private int row = 0;
    private int col = 0;
    
    public int[][] diagonalSort(int[][] mat) 
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
        {
            return new int[0][0];
        }
        
        row = mat.length;
        col = mat[0].length;
        
        for (int i = 0; i < col; i++)
        {
            sort(mat, 0, i);
        }
        
        for (int i = 1; i < row; i++)
        {
            sort(mat, i, 0);
        }
        
        return mat;
    }
    
    private void sort(int[][] mat, int x, int y)
    {
        for (int i_x = x, i_y = y; i_x < row && i_y < col; i_x++, i_y++)
        {
            int candidate = mat[i_x][i_y];
            int candidate_x = i_x, candidate_y = i_y;
              
            for (int j_x = i_x+1, j_y = i_y+1; j_x < row && j_y < col; j_x++, j_y++)
            {	
                if (mat[j_x][j_y] < candidate)
                {
                    candidate_x = j_x;
                    candidate_y = j_y;
                    candidate = mat[j_x][j_y];
                }
            }
            
            if (candidate_x != i_x)
            {
            	int temp = mat[i_x][i_y];
                mat[i_x][i_y] = mat[candidate_x][candidate_y];
                mat[candidate_x][candidate_y] = temp;
            }
        }
    }
    
    
    
    
    
    
    
    /************************************* main *************************************/
    
    private static void printMat(int[][] mat)
    {
    	for (int[] row : mat)
    	{
    		for (int num : row)
    		{
    			System.out.print(num + ", ");
    		}
    		
    		System.out.println();
    	}
    }
    
    public static void main(String[] args)
    {
    	Q1329_Sort_the_Matrix_Diagonally test = new Q1329_Sort_the_Matrix_Diagonally();
    	// int[][] mat = new int[][] {{3,3,1,1}, {2,2,1,2}, {1,1,1,2}};
    	int[][] mat = new int[][] {
    		{11,25,66,1,69,7}, 
    		{23,55,17,45,15,52}, 
    		{75,31,36,44,58,8}, 
    		{22,27,33,25,68,4},
    		{84,28,14,11,5,50}
    		};
    	printMat(mat);
    	System.out.println("*******");
    	printMat(test.diagonalSort(mat));
    }
}
