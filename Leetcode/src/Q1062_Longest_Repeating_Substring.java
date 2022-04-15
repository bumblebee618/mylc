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
public class Q1062_Longest_Repeating_Substring {
	public int longestRepeatingSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int left = 0, right = s.length()-1, result = 0;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (findRepeatedStr(s, mid)) {
                result = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        
        return findRepeatedStr(s, left) ? left : result;
    }
    
    private boolean findRepeatedStr(String s, int len) {
        Set<String> strs = new HashSet<>();
        
        for (int i = 0; i <= s.length()-len; i++) {
            String curStr = s.substring(i, i+len);
            
            if (strs.contains(curStr)) {
                return true;
            }
            
            strs.add(curStr);
        }
        
        return false;
    }
}
