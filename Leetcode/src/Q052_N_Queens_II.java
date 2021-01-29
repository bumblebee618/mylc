/*******
 * 
 * @author jackie
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

 

Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 9
 *
 */

public class Q052_N_Queens_II {
	/********************************************************/
	// using backtrack (recursive)
	private int count = 0;
    
    public int totalNQueens(int n) 
    {
        if (n <= 0)
        {
            return 0;
        }
        
        int[] position = new int[n + 1];
        backtrack(position, 1, n);
        return count;
    }
    
    public void backtrack(int[] position, int curRow, int n)
    {
        if (curRow > n)
        {
            count++;
        } 
        else
        {
            for (int i = 1; i <= n; i++)
            {
                position[curRow] = i;
                
                if (isValid(position, curRow) == true)
                {
                    backtrack(position, curRow+1, n);
                }
            }    
        }
    }
    
    public boolean isValid(int[] position, int curRow)
    {
        for (int i = 1; i < curRow; i++)
        {
            if (position[i] == position[curRow] || (Math.abs(i - curRow) == Math.abs(position[i] - position[curRow])))
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    
    
    
    /******************************* main *******************************/
    public static void main(String[] args)
    {
    	Q052_N_Queens_II t = new Q052_N_Queens_II();
    	System.out.println(t.totalNQueens(8));
    }
}
