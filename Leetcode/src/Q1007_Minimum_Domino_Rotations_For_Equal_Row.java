/***
 * 
 * @author jackie
 * 
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.

 

Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every minValue in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
 

Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
 */
public class Q1007_Minimum_Domino_Rotations_For_Equal_Row {
	public int minDominoRotations(int[] A, int[] B) {
        if (A  == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length)
        {
            return -1;
        }
        
        int countA = 0, countB = 0;
        
        for (int i = 0; i < A.length && (A[i] == A[0] || B[i] == A[0]); i++)
        {
            if (A[i] == A[0])
            {
                countA++;
            }
            
            if (B[i] == A[0])
            {
                countB++;
            }

            if (i == A.length-1)
            {
                return Math.min(A.length-countA, A.length-countB);
            }
        }
        
        countA = countB = 0;
        
        for (int i = 0; i < A.length && (A[i] == B[0] || B[i] == B[0]); i++)
        {
            if (A[i] == B[0])
            {
                countA++;
            }
            
            if (B[i] == B[0])
            {
                countB++;
            }

            if (i == A.length-1)
            {
                return Math.min(A.length-countA, A.length-countB);
            }
        }
        
        return -1;
    }
}
