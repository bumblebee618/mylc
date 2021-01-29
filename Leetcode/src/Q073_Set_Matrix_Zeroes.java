/*******************
 * 
 * Given a m row n matrix, if an element is 0, 
 * set its entire row and column to 0. Do it in place.
 * 
 * */

public class Q073_Set_Matrix_Zeroes {
	public void setZeroes(int[][] matrix) 
	{
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
		{
            return ;
        }
		
        int row = matrix.length;  
        int col = matrix[0].length;  
        int i, j;  
          
        //先标记第一行和第一列是否有0  
        boolean firstRow = false, firstCol = false;  
        
        for (j = 0; j < col; j++) 
        {  
            if (0 == matrix[0][j]) 
            {  
                firstRow = true;  
                break;  
            }  
        }
        
        for (i = 0; i < row; i++) 
        {  
            if (0 == matrix[i][0]) 
            {  
                firstCol = true;  
                break;  
            }  
        }  
           
        //从第二行第二列还是遍历，如果遇到0，则把它所在行和列的第一个值设为0     
        for (i = 1; i < row; i++) 
        {  
            for (j = 1; j < col; j++) 
            {  
                if (0 == matrix[i][j]) 
                {  
                    matrix[i][0] = 0;  
                    matrix[0][j] = 0;  
                }  
            }  
        }  
          
        //把第一列的0所在行都设为0，把第一行的0所在列都设为0  
        for (i = 1; i < row; i++)         // 注意，从1开始
        {
            if (0 == matrix[i][0]) 
            {  
            	setValue(matrix, i, true);   
            }  
        }  
        
        for (j = 1; j < col; j++)         // 注意，从1开始
        {
            if (0 == matrix[0][j]) 
            {  
            	setValue(matrix, i, false);  
            }  
        }  
          
        //根据标记决定第一行和第一列是否全设为0  
        if (firstRow) 
        {  
        	setValue(matrix, 0, true);   
        }  
        
        if (firstCol) 
        {  
        	setValue(matrix, 0, false); 
        }  
    }
	
	private void setValue(int[][] matrix, int index, boolean isRow)
    {
        if (isRow)
        {
            for (int i = 0; i < matrix[0].length; i++)
            {
                matrix[index][i] = 0;
            }
        }
        else
        {
            for (int i = 0; i < matrix.length; i++)
            {
                matrix[i][index] = 0;
            }
        }
    }
	
	
	
	
	
	
	
	
	
	/****************************************************/
	// by other
	public void setZeroes2(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		int m = matrix.length, n = matrix[0].length;
		boolean row = false, col = false;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					if (i == 0)
						row = true;
					else if (j == 0)
						col = true;
					else {
						matrix[i][0] = 0;
						matrix[0][j] = 0;
					}
				}
			}
		for (int i = m - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--)
				if (i == 0 && row == true || j == 0 && col == true
						|| matrix[0][j] == 0 || matrix[i][0] == 0)
					matrix[i][j] = 0;
		return;
	}
	
	
	public static void main(String[] args){
		Q073_Set_Matrix_Zeroes t = new Q073_Set_Matrix_Zeroes();
		int[][] matrix = {
				{1,2,3,0,5},
				{1,2,3,4,5},
				{1,2,3,4,5},
		};
		t.setZeroes(matrix);
		
		for(int i = 0; i < matrix.length; ++i){
			for(int j = 0 ; j < matrix[i].length; ++j)
				System.out.print(matrix[i][j] + ", ");
			System.out.println();
		}
	}
}
