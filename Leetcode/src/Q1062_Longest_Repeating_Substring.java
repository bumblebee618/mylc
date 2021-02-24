import java.util.*;

/***
 * 
 * @author jackie
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.

 

Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.
 

Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500
 */
public class Q1062_Longest_Repeating_Substring 
{
	public int longestRepeatingSubstring(String S) 
	{
        if (S == null || S.length() == 0)
        {
            return 0;
        }
        
        int left = 0, right = S.length()-1;
        
        while (left+1 < right)
        {
            int mid = left+(right-left)/2;
            
            if (findRepeatingStr(mid, S))
            {
                left = mid;
            }
            else 
            {
                right = mid;
            }
        }
        
        if (findRepeatingStr(right, S))
        {
            return right;
        }
        else if (findRepeatingStr(left, S))
        {
            return left;
        }
        else
        {
            return 0;
        }
    }
    
    private boolean findRepeatingStr(int L, String S)
    {
        int size = S.length();
        Set<String> set = new HashSet<>();
        
        for (int start = 0; start+L-1 < size; start++)
        {
            String subStr = S.substring(start, start+L);
            
            if (set.contains(subStr))
            {
                return true;
            }
            else
            {
                set.add(subStr);
            }
        }
        
        return false;
    }
}
