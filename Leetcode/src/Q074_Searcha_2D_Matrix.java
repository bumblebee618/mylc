/*******
 * 
Write an efficient algorithm that searches for a minValue in an m row n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

 * 
 * */

public class Q074_Searcha_2D_Matrix 
{
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int x = mid / col;
            int y = mid % col;
            
            if (matrix[x][y] > target) {
            	right = mid-1;
            } else if (matrix[x][y] < target) {
                left = mid+1;
            } else {
                return true;
            }
        }
        
        return matrix[left / col][left % col] == target ? true : false;
    }
	
	
	
	
	
	
	
	
	
	/******************************* main function ********************************/
	
	public static void main(String[] args){
		Q074_Searcha_2D_Matrix t = new Q074_Searcha_2D_Matrix();
		int[][] matrix = {
				{-10,-10},
				{-9,-9},
				{-8,-6},
				{-4,-2},
				{0,1},
				{3,3},
				{5,5},
				{6,8}
		};
//		int[][] matrix = {{1, 3}};
		System.out.println(t.searchMatrix(matrix, -7));
	}
}
