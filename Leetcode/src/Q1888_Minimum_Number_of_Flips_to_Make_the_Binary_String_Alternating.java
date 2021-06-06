/***
 * 
 * @author jackie
 *
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
 */
public class Q1888_Minimum_Number_of_Flips_to_Make_the_Binary_String_Alternating 
{
	public int minFlips(String s) 
	{
		if (s == null || s.length() == 0)
		{
			return 0;
		}
		
		int n = s.length();
        int result = 0;
        
        int[][] left = new int[n][2];
        left[0][0] = (s.charAt(0) == '0') ? 0 : 1;
        left[0][1] = (s.charAt(0) == '1') ? 0 : 1;
        
        for (int i = 1; i < s.length(); i++) 
        {
            left[i][0] = ((s.charAt(i) == '0') ? 0 : 1) + left[i - 1][1];
            left[i][1] = ((s.charAt(i) == '1') ? 0 : 1) + left[i - 1][0];
        }
        
        int[][] right = new int[n][2];
        right[n - 1][0] = (s.charAt(n - 1) == '0') ? 0 : 1;
        right[n - 1][1] = (s.charAt(n - 1) == '1') ? 0 : 1;
        
        for (int i = n - 2; i >= 0; i--) 
        {
            right[i][0] = ((s.charAt(i) == '0') ? 0 : 1) + right[i + 1][1];
            right[i][1] = ((s.charAt(i) == '1') ? 0 : 1) + right[i + 1][0];
        }
        
        result = Math.min(left[n - 1][0], left[n - 1][1]);
        
        for (int i = 1; i < n; i++) 
        {
            if ((n - i) % 2 == 0) 
            {
                // 0101 0101 ? 1010 1010
            	result = Math.min(result, right[i][0] + ((i % 2 == 0) ? left[i - 1][1] : left[i - 1][0]));
            	result = Math.min(result, right[i][1] + ((i % 2 == 0) ? left[i - 1][0] : left[i - 1][1]));
            } 
            else 
            {
                // 101 0101
                // 1010 101 ? 0101 ? 010
            	result = Math.min(result, right[i][0] + ((i % 2 == 0) ? left[i - 1][0] : left[i - 1][1]));
            	result = Math.min(result, right[i][1] + ((i % 2 == 0) ? left[i - 1][1] : left[i - 1][0]));
            } 
        }
        
        return result; 
    }
}
