/***
 * 
 * @author jackie
 * 
 * Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.

An alphanumeric string is a string consisting of lowercase English letters and digits.

 

Example 1:

Input: s = "dfa12321afd"
Output: 2
Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
Example 2:

Input: s = "abc1111"
Output: -1
Explanation: The digits that appear in s are [1]. There is no second largest digit. 
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
 */
public class Q1796_Second_Largest_Digit_in_a_String 
{
	public int secondHighest(String s) 
    {
        if (s == null || s.length() <= 1)
        {
            return -1;
        }
        
        int max1 = -1, max2 = -1;
        
        for (char c : s.toCharArray())
        {
            if (Character.isDigit(c))
            {
                int curNum = c - '0';
                
                if (max1 == -1 || curNum >= max1)
                {
                    if (curNum == max1)
                    {
                        continue;
                    }
                    
                    max2 = max1;
                    max1 = curNum;
                }
                else if (max2 == -1 || curNum > max2)
                {
                    max2 = curNum;
                }
            }
        }
        
        return max2;
    }
}
