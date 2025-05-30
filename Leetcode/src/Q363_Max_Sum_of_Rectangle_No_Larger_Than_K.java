import java.util.*;
/***
 * 
 * @author jackie
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */

public class Q363_Max_Sum_of_Rectangle_No_Larger_Than_K {
	// time is O(n^3*logn)
	public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int row = matrix.length, col = matrix[0].length;
		int result = Integer.MIN_VALUE;
        int[][] prefixSum = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            int rowSum = 0;
            
            for (int j = 0; j < col; j++) {
                rowSum += matrix[i][j];
                prefixSum[i][j] = (i > 0) ? prefixSum[i-1][j] + rowSum : rowSum;
            }
        }

		for (int startRow = 0; startRow < row; startRow++) {
			for (int endRow = startRow; endRow < row; endRow++) {
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);   // in case k == curSum;

				for (int i = 0; i < col; i++) {
					int part1 = (startRow > 0) ? prefixSum[startRow-1][i] : 0;
					int curSum = prefixSum[endRow][i] - part1;
                    
					// use TreeMap to binary search previous sum to get possible result
					Integer subResult = set.ceiling(curSum - k);

					if (subResult != null) {
						result = Math.max(result, curSum - subResult);
					}

					set.add(curSum);
				}
			}
		}

		return result;
    }
	
	
	/***
	 * first consider the situation matrix is 1D we can save every sum of
	 * 0~i(0<=i<len) and binary search previous sum to find possible result for
	 * every index, time complexity is O(NlogN). so in 2D matrix, we can sum up
	 * all values from row i to row j and create a 1D array to use 1D array
	 * solution. If col number is less than row number, we can sum up all values
	 * from col i to col j then use 1D array solution.
	 * 
	 ***/
	// solution 1, use treeSet, time complexity is O(n^3 * logn)
	public int maxSumSubmatrix2(int[][] matrix, int k) 
	{
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
		{
			return 0;
		}

		int row = matrix.length, col = matrix[0].length;
		
		// indicating sum up in every row or every column
		int result = Integer.MIN_VALUE;

		// start from row between i and j, row from 0 to col
		for (int rowStart = 0; rowStart < row; rowStart++) 
		{
            // compress sum of each row into an array
			int[] rowSum = new int[col];  
			
			// sum row: [i, j] col:[0, col - 1]
			for (int rowEnd = rowStart; rowEnd < row; rowEnd++) 
			{
				int totalSum = 0;
				TreeSet<Integer> set = new TreeSet<>();
				set.add(0);   // in case k == curSum;

				// traverse every row and sum up
				for (int i = 0; i < col; i++) 
				{
					rowSum[i] += matrix[rowEnd][i];
					totalSum += rowSum[i];
					
					// use TreeSet to binary search previous sum to get possible result
					Integer subResult = set.ceiling(totalSum - k);

					if (subResult != null) 
					{
						if (result < totalSum - subResult)
						{
							System.out.println(String.format("2: start=%d, end=%d, col=%d, curSum=%d, subResult=%d", rowStart, rowEnd, i, totalSum, subResult));
						}
						
						result = Math.max(result, totalSum - subResult);
					}

					set.add(totalSum);
				}
			}
		}

		return result;
	}
	
	
	// solution 2: time complexity is O(n^4)
	public int maxSumSubmatrix3(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int result = Integer.MIN_VALUE;
        int[][] areas = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int area = matrix[i][j];
                
                if (i-1 >= 0) {
                    area += areas[i-1][j];
                }
                if (j-1 >= 0) {
                    area += areas[i][j-1];
                }
                if (i-1 >= 0 && j-1 >= 0) {
                    area -= areas[i-1][j-1];
                }
                
                areas[i][j] = area;
            }
        }
         
        for (int r1 = 0; r1 < row; r1++) {
            for (int r2 = r1; r2 < row; r2++) {
                for (int c1 = 0; c1 < col; c1++) {
                    for (int c2 = c1; c2 < col; c2++) {
                        int area = areas[r2][c2];
                        
                        if (r1-1 >= 0) {
                            area -= areas[r1-1][c2];
                        }
                        if (c1-1 >= 0) {
                            area -= areas[r2][c1-1];
                        }
                        if (r1-1 >= 0 && c1-1 >= 0) {
                            area += areas[r1-1][c1-1];
                        }
                        if (area <= k) {
                            result = Math.max(result, area);
                        }                        
                    }
                }     
            }
        }
        
        return result;           
    }
	
	
	
	
	
	
	
	/*************************************** main ***************************************/
	
	public static void main(String[] args)
	{
		Q363_Max_Sum_of_Rectangle_No_Larger_Than_K test = new Q363_Max_Sum_of_Rectangle_No_Larger_Than_K();
		int[][] matrix = {{5,-4,-3,4},{-3,-4,4,5},{5,1,5,-4}};
		int k = 10;
		
		for (int[] array : matrix)
		{
			for (int num : array)
			{
				System.out.print(num + ", ");
			}
			
			System.out.println();
		}
		
		System.out.println(test.maxSumSubmatrix(matrix, k));
		
		System.out.println(test.maxSumSubmatrix2(matrix, k));
	}
}
