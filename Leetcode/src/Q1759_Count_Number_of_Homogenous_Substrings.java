/***
 * 
 * @author jackie
 * 
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
Example 2:

Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".
Example 3:

Input: s = "zzzzz"
Output: 15
 

Constraints:

1 <= s.length <= 105
s consists of lowercase letters.
 */

public class Q1759_Count_Number_of_Homogenous_Substrings 
{
	private int mod = 1_000_000_000 + 7;
    
    public int countHomogenous(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int front = 0, back = 0;
        int size = s.length();
        long result = 0;
        
        while (front < size)
        {
            back = front;
            
            while (front < size && s.charAt(front) == s.charAt(back))
            {
                front++;
            }
            
            int len = front - back;
            int count = getCount(len);
            result += count;
        }
        
        return (int) result % mod;
    }
    
    private int getCount(int len)
    {
        long count = 0; 
        
        for (int i = 1; i <= len; i++)
        {
            count += len - (i-1);
            count %= mod;
        }
        
        return (int) count;
    }
}
