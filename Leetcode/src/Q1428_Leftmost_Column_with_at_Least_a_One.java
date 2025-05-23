import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * (This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.

 

 

 

Example 1:



Input: mat = [[0,0],[1,1]]
Output: 0
Example 2:



Input: mat = [[0,0],[0,1]]
Output: 1
Example 3:



Input: mat = [[0,0],[0,0]]
Output: -1
Example 4:



Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1
 

Constraints:

rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in a non-decreasing way.
 * 
 */

public class Q1428_Leftmost_Column_with_at_Least_a_One {
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if (binaryMatrix == null)
        {
            return -1;
        }
        
        List<Integer> dimensions = binaryMatrix.dimensions();
        int row = dimensions.get(0);
        int col = dimensions.get(1);
        int x = 0, y = col-1;
        
        while (y >= 0 && x < row)
        {
            int value = binaryMatrix.get(x, y);
            
            if (value == 1)
            {
                y--;
            }
            else
            {
                x++;
            }
        }
        
        return (x >= row && y == col-1) ? -1 : y+1;
    }
}

class BinaryMatrix 
{
	public int get(int x, int y)
	{
		return 1;
	}
	
	public List<Integer> dimensions()
	{
		return new LinkedList<Integer>();
	}
}
