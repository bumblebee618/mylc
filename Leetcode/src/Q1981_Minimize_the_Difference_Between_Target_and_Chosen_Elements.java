/***
 * 
 * @author jackie
 * 
 * You are given an m x n integer matrix mat and an integer target.

Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.

Return the minimum absolute difference.

The absolute difference between two numbers a and b is the absolute value of a - b.

 

Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
Output: 0
Explanation: One possible choice is to:
- Choose 1 from the first row.
- Choose 5 from the second row.
- Choose 7 from the third row.
The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
Example 2:


Input: mat = [[1],[2],[3]], target = 100
Output: 94
Explanation: The best possible choice is to:
- Choose 1 from the first row.
- Choose 2 from the second row.
- Choose 3 from the third row.
The sum of the chosen elements is 6, and the absolute difference is 94.
Example 3:


Input: mat = [[1,2,9,8,7]], target = 6
Output: 1
Explanation: The best choice is to choose 7 from the first row.
The absolute difference is 1.
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 70
1 <= mat[i][j] <= 70
1 <= target <= 800
 */

public class Q1981_Minimize_the_Difference_Between_Target_and_Chosen_Elements {
	private int abs;  
	private boolean[][] visited;
	private int[][] mat;
    
	public int minimizeTheDifference(int[][] mat, int target) {
		abs = Integer.MAX_VALUE;
		visited = new boolean[mat.length][4900];
		this.mat = mat;
	    search(0, 0, target);
	    return abs;
    }
	
	private void search(int sum, int curRow, int target) {
	    if (curRow >= mat.length) {
	        abs = Math.min(abs, Math.abs(target - sum));
	        return;
	    }  
	    
	    // we have arrived this row before with the same sum value, and calculated resultant abs min value. 
	    if (visited[curRow][sum]) { 
	        return;
	    }
	    
	    for (int i = 0; i < mat[0].length; i++) {
	        search(sum + mat[curRow][i], curRow + 1, target);
	    }
	    
	    visited[curRow][sum] = true;  
	}
}
