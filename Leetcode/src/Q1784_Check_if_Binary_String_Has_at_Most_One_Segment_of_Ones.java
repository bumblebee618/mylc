/***
 * 
 * @author jackie
 * 
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s contains at most one contiguous segment of ones. Otherwise, return false.

 

Example 1:

Input: s = "1001"
Output: false
Explanation: The ones do not form a contiguous segment.
Example 2:

Input: s = "110"
Output: true
 

Constraints:

1 <= s.length <= 100
s[i]​​​​ is either '0' or '1'.
s[0] is '1'.
 */
public class Q1784_Check_if_Binary_String_Has_at_Most_One_Segment_of_Ones 
{
	public boolean checkOnesSegment(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return false;
        }
        
        int size = s.length();
        boolean hasZero = false;
        
        for (int i = 1; i < size; i++)
        {
            if (s.charAt(i) == '0')
            {
                hasZero = true;
            }
            else
            {
                if (hasZero)
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}
