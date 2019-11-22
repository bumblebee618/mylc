package z_exercise;

import java.util.LinkedList;
import java.util.List;

public class findMinPath {
	
	class Tuple {
		int from_x;
		int pathLength;
		
		public Tuple(int x, int pathLength) {
			from_x = x;
			this.pathLength = pathLength;
		}
	}
	
	public List<Integer> findMinPath(int[][] matrix) {
		List<Integer> result = new LinkedList<>();
		
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		
		int row = matrix.length, col = matrix[0].length;
		Tuple[][] dp = new Tuple[row][col];
		
		for (int i = 0; i < row; i++) {
			dp[i][0] = new Tuple(-1, matrix[i][0]);
		}
		
		for (int j = 1; j < col; j++) {
			for (int i = 0; i < row; i++) {
				int minValue = dp[i][j-1].pathLength;
				int from_x = i;
				
				if (i > 0 && minValue > dp[i-1][j-1].pathLength) {
					minValue = dp[i-1][j-1].pathLength;
					from_x = i-1;
				}
				
				if (i+1 < row && minValue > dp[i+1][j-1].pathLength) {
					minValue = dp[i+1][j-1].pathLength;
					from_x = i+1;
				}
				
				dp[i][j] = new Tuple(from_x, minValue + matrix[i][j]);
			}
		}
		
		int globalMinValue = dp[0][col-1].pathLength;
		int current_x = 0;
		
		for (int i = 1; i < row; i++) {
			if (dp[i][col-1].pathLength < globalMinValue) {
				globalMinValue = dp[i][col-1].pathLength;
				current_x = i;
			}
		}
		
		for (int i = col-1; i >= 0; i--) {
			result.add(0, matrix[current_x][i]);
			current_x = dp[current_x][i].from_x;
		}
		
		return result;
	}
	
	
	

		 
	public int solution(int[][] m) {
		int[][][] dp = new int[m.length][m[0].length][4];
		
		for (int i = 0; i < m.length; i++) {
			dp[i][0] = new int[] { m[i][0], m[i][0], m[i][0], m[i][0] };
		}

		for (int i = 1; i < m[0].length; i++) {
			for (int j = 0; j < m.length; j++) {
				dp[j][i][0] = dp[j][i - 1][3] + m[j][i];
				dp[j][i][3] = dp[j][i][0];
			}
			for (int j = 1; j < m.length; j++) {
				dp[j][i][1] = dp[j - 1][i][3] + m[j][i];
				dp[j][i][3] = Math.min(dp[j][i][1], dp[j][i][3]);
			}
			for (int j = m.length - 2; j >= 0; j--) {
				dp[j][i][2] = dp[j + 1][i][3] + m[j][i];
				dp[j][i][3] = Math.min(dp[j][i][2], dp[j][i][3]);
			}
		}
		
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < m.length; i++) {
			res = Math.min(res, dp[i][m[0].length - 1][3]);
		}
		return res;
	}


	
	
	
	
	
	public static void main(String[] args) {
		findMinPath test = new findMinPath();
		int[][] matrix = {{1,7,1,3}, 
						  {2,1,4,5}, 
						  {6,2,1,2}};
		List<Integer> result = test.findMinPath(matrix);
		result.stream().forEach(elem -> System.out.println(elem));
	}
}
