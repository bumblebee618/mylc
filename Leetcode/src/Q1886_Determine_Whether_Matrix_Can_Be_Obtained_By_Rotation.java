/***
 * 
 * @author jackie
 * 
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

 

Example 1:


Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
Example 2:


Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.
Example 3:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
 

Constraints:

n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1.
 */
public class Q1886_Determine_Whether_Matrix_Can_Be_Obtained_By_Rotation 
{
	public boolean findRotation(int[][] mat, int[][] target) 
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
        {
            return false;
        }
        else if (target == null || target.length == 0 || target[0].length == 0)
        {
            return false;
        }
        else if (mat.length != target.length)
        {
            return false;
        }
        
        int n = mat.length;
        int[] status1 = new int[n];
        int[] status2 = new int[n];
        
        for (int row = 0; row < n; row++)
        {
        	status1[row] = 0;
        	status2[row] = 0;
        	
        	for (int col = 0; col < n; col++)
        	{
        		if (mat[row][col] == 1)
        		{
        			status1[row] |= (1 << col);
        		}
        		
        		if (target[row][col] == 1)
        		{
        			status2[row] |= (1 << col);
        		}
        	}
        }
        
        if (isEqual(status1, status2))
        {
        	return true;
        }
        
        int[] status3 = new int[n];
        
        for (int col = n-1; col >= 0; col--)
        {
        	status3[n-1-col] = 0;
        	
        	for (int row = 0; row < n; row++)
        	{
        		if (target[row][col] == 1)
        		{
        			status3[n-1-col] |= (1 << row);
        		}
        	}
        }
        
        if (isEqual(status1, status3))
        {
        	return true;
        }
        
        int[] status4 = new int[n];
        
        for (int row = n-1; row >= 0; row--)
        {
        	status4[n-1-row] = 0;
        	
        	for (int col = n-1; col >= 0; col--)
        	{
        		if (target[row][col] == 1)
        		{
        			status4[n-1-row] |= (1 << (n-1-col));
        		}
        	}
        }
        
        if (isEqual(status1, status4))
        {
        	return true;
        }
        
        int[] status5 = new int[n];
        
        for (int col = 0; col < n; col++)
        {
        	status5[col] = 0;
        	
        	for (int row = n-1; row >= 0; row--)
        	{
        		if (target[row][col] == 1)
        		{
        			status5[col] |= (1 << (n-1-row));
        		}
        	}
        }
        
        if (isEqual(status1, status5))
        {
        	return true;
        }
        
        return false;
    }		
	
	private boolean isEqual(int[] status1, int[] status2)
	{
		for (int i = 0; i < status1.length; i++)
		{
			if (status1[i] != status2[i])
			{
				return false;
			}
		}
		
		return true;
	}
}
