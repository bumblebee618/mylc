import java.util.*;

public class Q363_Max_Sum_of_Rectangle_No_Larger_Than_K {
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
	public int maxSumSubmatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int row = matrix.length, col = matrix[0].length;
		// indicating sum up in every row or every column
		int ans = Integer.MIN_VALUE;

		// start from column between i and j, row from 0 to row
		for (int colStart = 0; colStart < col; colStart++) {
			int[] rowSum = new int[row];  // compress sum of each row into an array
			
			// sum row:[0, row - 1], col: [i, j]
			// sum from col i to col - 1
			for (int colEnd = colStart; colEnd < col; colEnd++) {
				int curSum = 0;
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);   // in case k == curSum;

				// traverse every row and sum up
				for (int k = 0; k < row; k++) {
					rowSum[k] += matrix[k][colEnd];
					curSum += rowSum[k];
					
					// use TreeMap to binary search previous sum to get possible result
					Integer subResult = set.ceiling(curSum - target);

					if (subResult != null) {
						ans = Math.max(ans, curSum - subResult);
					}

					set.add(curSum);
				}
			}
		}

		return ans;
	}
	
	
	// solution 2: time complexity is O(n^4)
	public int maxSumSubmatrix2(int[][] matrix, int k) {
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
	
	
	
	// solution 2:
	public int maxSumSubmatrix3(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int row = matrix.length, col = matrix[0].length;
		int m = Math.min(row, col);
		int n = Math.max(row, col);

		// indicating sum up in every row or every column
		boolean colIsBig = col > row;
		int ans = Integer.MIN_VALUE;

		for (int i = 0; i < m; i++) {
			int[] sum = new int[n];

			// sum from row j to row i
			for (int j = i; j >= 0; j--) {
				int curSum = 0;
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);

				// traverse every column/row and sum up
				for (int k = 0; k < n; k++) {
					sum[k] = sum[k] + (colIsBig ? matrix[j][k] : matrix[k][j]);
					curSum = curSum + sum[k];
					
					// use TreeMap to binary search previous sum to get possible result
					Integer subResult = set.ceiling(curSum - target);

					if (null != subResult) {
						ans = Math.max(ans, curSum - subResult);
					}

					set.add(curSum);
				}
			}
		}

		return ans;
	}
}
