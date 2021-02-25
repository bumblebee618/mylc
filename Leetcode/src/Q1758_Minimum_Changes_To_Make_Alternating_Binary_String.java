/***
 * 
 * @author jackie
 * 
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.

The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.

Return the minimum number of operations needNum to make s alternating.

 

Example 1:

Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.
Example 2:

Input: s = "10"
Output: 0
Explanation: s is already alternating.
Example 3:

Input: s = "1111"
Output: 2
Explanation: You need two operations to reach "0101" or "1010".
 

Constraints:

1 <= s.length <= 104
s[i] is either '0' or '1'.
 */
public class Q1758_Minimum_Changes_To_Make_Alternating_Binary_String 
{
	public int minOperations(String s) 
    {
        if (s == null || s.length() <= 1)
        {
            return 0;
        }
        
        char[] letters = s.toCharArray();
        int count1 = 0;
        
        // "10"
        for (int i = 0; i < letters.length-1; i += 2)
        {
            if (letters[i] == '1' && letters[i+1] == '0')
            {
                continue;
            }
            
            count1 += letters[i] == '1' ? 0 : 1;
            count1 += letters[i+1] == '0' ? 0 : 1;
        }
        
        if (letters.length % 2 == 1)
        {
            count1 += letters[letters.length-1] == '1' ? 0 : 1;
        }
        
        int count2 = 0;
        
        // "01"
        for (int i = 0; i < letters.length-1; i += 2)
        {
            if (letters[i] == '0' && letters[i+1] == '1')
            {
                continue;
            }
            
            count2 += letters[i] == '0' ? 0 : 1;
            count2 += letters[i+1] == '1' ? 0 : 1;
        }
        
        if (letters.length % 2 == 1)
        {
            count2 += letters[letters.length-1] == '0' ? 0 : 1;
        }
        
        return Math.min(count1, count2);
    }
}
