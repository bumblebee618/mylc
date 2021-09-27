import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[row][col] with x1 <= row <= x2 and y1 <= col <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

 

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 

Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
 */
public class Q1074_Number_of_Submatrices_That_Sum_to_Target 
{
	// solution 1:
	public int numSubmatrixSumTarget(int[][] matrix, int target) 
    {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
		{
			return 0;
		}
		
		int row = matrix.length, col = matrix[0].length;
		int[][] prefixSum = new int[row][col];
        int result = 0;
		
		for (int i = 0; i < row; i++)
		{
			int rowSum = 0;
			
			for (int j = 0; j < col; j++)
			{
				rowSum += matrix[i][j];
				prefixSum[i][j] = (i > 0) ? prefixSum[i-1][j] + rowSum : rowSum;
			}
		}
		
		for (int startRow = 0; startRow < row; startRow++)
		{
			for (int endRow = startRow; endRow < row; endRow++)
			{
				Map<Integer, Integer> map = new HashMap<>();
				map.put(0, 1);
				
				for (int j = 0; j < col; j++)
				{
					int part1 = (startRow > 0) ? prefixSum[startRow-1][j] : 0;
					int sum = prefixSum[endRow][j] - part1;
					result += map.getOrDefault(sum-target, 0);
					map.put(sum, map.getOrDefault(sum, 0) + 1);
				}
			}
		}
		
		return result; 
    }
	
	
	
	// solution 2:
	public int numSubmatrixSumTarget2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }
        
        int rows = matrix.length, cols = matrix[0].length;
        int count = 0;
        
        for (int start = 0; start < rows; start++) 
        {
            int[] row_sum = new int[cols];
            
            for (int end = start; end < rows; end++) 
            {
                for (int col = 0; col < cols; col++) 
                {
                    row_sum[col] += matrix[end][col];
                }
                
                count += find_target_in_array(row_sum, target);
            }
        }
        
        return count;
    }
    
    private int find_target_in_array(int[] array, int target) 
    {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        
        for (int i = 0; i < array.length; i++) 
        {
            sum += array[i];
            count += map.getOrDefault(sum-target, 0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return count;
    }
    
    
    
    
    public static void main(String[] args)
    {
    	Q1074_Number_of_Submatrices_That_Sum_to_Target test = new Q1074_Number_of_Submatrices_That_Sum_to_Target();
    	
    	int[][] matrix = 
    		{
    				{0,1,0},
    				{1,1,1},
    				{0,1,0}
    		};
    	
    	int target = 0;
    	
    	System.out.println(test.numSubmatrixSumTarget(matrix, target));
    }
}
