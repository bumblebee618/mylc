/***
 * 
 * @author jackie
 * 
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.

 

Example 1:

Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
Example 2:

Input: s1 = "attack", s2 = "defend"
Output: false
Explanation: It is impossible to make them equal with one string swap.
Example 3:

Input: s1 = "kelb", s2 = "kelb"
Output: true
Explanation: The two strings are already equal, so no string swap operation is required.
Example 4:

Input: s1 = "abcd", s2 = "dcba"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 100
s1.length == s2.length
s1 and s2 consist of only lowercase English letters.
 */
public class Q1790_Check_if_One_String_Swap_Can_Make_Strings_Equal 
{
	public boolean areAlmostEqual(String s1, String s2) 
    {
        if (s1 == null || s2 == null)
        {
            return s1 == null && s2 == null;
        }
        else if (s1.length() != s2.length())
        {
            return false;
        }
        else if (s1.equals(s2))
        {
            return true;
        }
        
        int index1 = -1, index2 = -1;
        int size = s1.length();
        
        for (int i = 0; i < size; i++)
        {
            if (s1.charAt(i) != s2.charAt(i))
            {
                if (index1 == -1)
                {
                    index1 = i;
                }
                else if (index2 == -1)
                {
                    index2 = i;
                }
                else
                {
                    return false;
                }
            }
        }
        
        if (index2 == -1)
        {
            return false;
        }
        else
        {
            return s1.charAt(index1) == s2.charAt(index2) && s1.charAt(index2) == s2.charAt(index1);
        }
    }
}
