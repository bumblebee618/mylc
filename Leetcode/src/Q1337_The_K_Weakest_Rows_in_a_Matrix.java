import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.

 

Example 1:

Input: mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
Output: [2,0,3]
Explanation: 
The number of soldiers for each row is: 
row 0 -> 2 
row 1 -> 4 
row 2 -> 1 
row 3 -> 2 
row 4 -> 5 
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
Example 2:

Input: mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
Output: [0,2]
Explanation: 
The number of soldiers for each row is: 
row 0 -> 1 
row 1 -> 4 
row 2 -> 1 
row 3 -> 1 
Rows ordered from the weakest to the strongest are [0,2,3,1]
 

Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
 */

public class Q1337_The_K_Weakest_Rows_in_a_Matrix 
{
	public int[] kWeakestRows(int[][] mat, int k) 
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0 
            || k <= 0 || k > mat.length)
        {
            return new int[0];
        }
        
        int row = mat.length, col = mat[0].length;        
        int[] result = new int[k];
        int index = 0;
        Set<Integer> visited = new HashSet<>();
        
        for (int j = 0; j < col; j++)
        {
            for (int i = 0; i < row; i++)
            {
                if (mat[i][j] == 0 && !visited.contains(i))
                {
                    visited.add(i);
                    result[index++] = i;
                    
                    if (index == k)
                    {
                        return result;
                    }
                }
            }
        }
        
        if (index < k)
        {
            for (int i = 0; i < row && index < k; i++)
            {
                if (mat[i][col-1] == 1)
                {
                    result[index++] = i;
                }
            }
        }
        
        return result;
    }
}
