import java.util.Stack;
/*********
 * 
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 6.
 * 
 * */

/**************************************************************************
 * 注意： 最大的矩形的底部不一定出现在最后一行，因此需要每行遍历，运用O(n^2)的时间复杂度
 * 
 **************************************************************************/

public class Q085_Maximal_Rectangle {
	public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int[][] heights = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    heights[i][j] = matrix[i][j] - '0';
                } else {
                    heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i-1][j] + 1;
                }
            }
        }
        
        int maxArea = 0;
        
        for (int i = 0; i < row; i++) {
            Stack<Integer> stack = new Stack<>();
            
            for (int j = 0; j <= col; j++) {
                while (!stack.isEmpty() && (j == col || heights[i][j] <= heights[i][stack.peek()])) {
                    int height = heights[i][stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, height*width);
                }
            
                stack.push(j);
            }
        }

        return maxArea;
    }
	
	
	
	
	
	
	/*** main function ***/
	public static void main(String[] args){
		Q085_Maximal_Rectangle t = new Q085_Maximal_Rectangle();
		char[][] matrix = {
				{'0', '0'},
				{'0', '0'}
		};
		
		System.out.println(t.maximalRectangle(matrix));
	}
}
