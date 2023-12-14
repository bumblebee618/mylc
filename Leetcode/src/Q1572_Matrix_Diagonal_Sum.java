/***
 * 
 * @author jackie
 * 
 * Given a square matrix mat, return the sum of the matrix diagonals.

Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 *
 */
public class Q1572_Matrix_Diagonal_Sum {
	public int diagonalSum(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0 || mat.length != mat[0].length) {
            return 0;
        }

        int size = mat.length;
        int sum = 0;

        for (int i = 0; i < size; i++) {
            sum += mat[i][i];

            if (size-1-i != i) {
                sum += mat[size-1-i][i];
            }
        }

        return sum;
    }
}
