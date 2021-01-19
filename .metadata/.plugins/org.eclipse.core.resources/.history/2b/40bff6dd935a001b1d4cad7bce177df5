/***
 * 
 * @author jackie
 * 
 * We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9
 */
public class Q848_Shifting_Letters {
	public String shiftingLetters(String s, int[] shifts) {
        if (s == null || s.length() == 0 || shifts == null || shifts.length == 0 || s.length() != shifts.length)
        {
            return s;
        }
        
        int size = shifts.length;
        int totalShift = 0;
        char[] letters = s.toCharArray();
        
        for (int i = size-1; i >= 0; i--)
        {
            totalShift = (totalShift + shifts[i]) % 26;
            letters[i] = getShiftResult(letters[i], totalShift);
        }
        
        return new String(letters);
    }
    
    private char getShiftResult(char c, int shift)
    {
        return (char) ((((int)(c-'a') + shift) % 26) + 'a');
    }
}
