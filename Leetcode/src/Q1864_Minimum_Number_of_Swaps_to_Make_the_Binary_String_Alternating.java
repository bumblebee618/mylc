/***
 * 
 * @author jackie
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.

 

Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.
Example 3:

Input: s = "1110"
Output: -1
 

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
 */
public class Q1864_Minimum_Number_of_Swaps_to_Make_the_Binary_String_Alternating 
{
	public int minSwaps(String s) 
    {
        if (s == null || s.length() == 0)
		{
			return 0;
		}
		
		int[] counts = new int[2];
		int change1 = 0, change2 = 0;
		
		for (int i = 0; i < s.length(); i++)
		{
			counts[s.charAt(i)-'0']++;
			char target1 = (i % 2 == 0) ? '1' : '0';
			char target2 = (i % 2 == 0) ? '0' : '1';
			change1 += (s.charAt(i) != target1) ? 1 : 0;
			change2 += (s.charAt(i) != target2) ? 1 : 0;
		}
		
		if (Math.abs(counts[0] - counts[1]) > 1)
		{
			return -1;
		}
		else
		{
			if (change1 % 2 == 0 && change2 % 2 == 0)
			{
				return Math.min(change1, change2) / 2;
			}
			else if (change1 % 2 == 0)
			{
				return change1 / 2;
			}
			else 
			{
				return change2 / 2;
			}
		}    
    }
}
