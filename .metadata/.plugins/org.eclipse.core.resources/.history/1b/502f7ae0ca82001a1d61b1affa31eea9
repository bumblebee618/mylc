/***
 * 
 * @author jackie
 * 
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.
 */
public class Q821_Shortest_Distance_to_a_Character {
	public int[] shortestToChar(String S, char C) {
        if (S == null || S.length() == 0)
        {
            return new int[0];
        }
        
        int[] result = new int[S.length()];
        int leftPos = -1, rightPos = -1;
        
        for (int i = 0; i < S.length(); i++)
        {
            if (i > rightPos)
            {
                if (rightPos != -1)
                {
                    leftPos = rightPos;
                }
                
                if (i == 0 || rightPos != -1)
                {
                    rightPos = S.indexOf(C, i);
                }
            }
            
            if (leftPos == -1)
            {
                result[i] = rightPos-i;
            }
            else if (rightPos == -1)
            {
                result[i] = i-leftPos;
            }
            else
            {
                result[i] = Math.min(i-leftPos, rightPos-i);
            }
        }
        
        return result;
    }
}
