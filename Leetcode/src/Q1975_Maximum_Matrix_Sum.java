/***
 * 
 * @author jackie
 * 
 * You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
 

Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105
 */
public class Q1975_Maximum_Matrix_Sum {
	public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length, negativeCount = 0;
        int minPositive = Integer.MAX_VALUE, maxNegative = Integer.MIN_VALUE;
        long sum = 0;
        boolean hasZero = false;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	sum += matrix[i][j] > 0 ? matrix[i][j] : -matrix[i][j];
            	
                if (matrix[i][j] > 0) {
                	minPositive = Math.min(minPositive, matrix[i][j]);
                } else if (matrix[i][j] < 0) {
                	maxNegative = Math.max(maxNegative, matrix[i][j]);
                	negativeCount++;
                } else {
					hasZero = true;
				}
            }
        }
        
        if (!hasZero && negativeCount % 2 > 0) {
        	if (minPositive != Integer.MAX_VALUE) {
        		sum -= 2 * Math.min(minPositive, -maxNegative);
        	} else {
        		sum += 2 * maxNegative;
        	}
        }
        
        return sum;
    }
}
