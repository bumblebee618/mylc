import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

 

Example 1:

Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.
Example 2:

Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
Example 3:

Input: s = "c"
Output: ""
Explanation: There are no nice substrings.
Example 4:

Input: s = "dDzeE"
Output: "dD"
Explanation: Both "dD" and "eE" are the longest nice substrings.
As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 

Constraints:

1 <= s.length <= 100
s consists of uppercase and lowercase English letters.
 */
public class Q1763_Longest_Nice_Substring 
{
private int diff = 'a' - 'A';
    
    public String longestNiceSubstring(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return "";
        }
        
        int[] lastPos = new int[256];
        char[] letters = s.toCharArray();        
        Arrays.fill(lastPos, -1);
        
        for (int i = 0; i < letters.length; i++)
        {
            lastPos[letters[i]] = i;
        }
        
        int maxLen = 0, startIndex = -1;
        
        for (int start = 0; start < letters.length; start++)
        {
            if (!isCandidate(letters[start], start, lastPos))
            {
                continue;
            }
            
            int[] hash = new int[256];
            
            for (int end = start; end < letters.length; end++)
            {
                if (!isCandidate(letters[end], start, lastPos))
                {
                    break;
                }
                
                hash[letters[end]]++;
                
                if (end-start+1 > maxLen && isNiceString(hash))
                {
                    maxLen = end-start+1;
                    startIndex = start;
                }
            }
        }
        
        return maxLen == 0 ? "" : s.substring(startIndex, startIndex+maxLen);
    }
    
    private boolean isCandidate(char c, int startPos, int[] lastPos)
    {
        int target = (c >= 'a' && c <= 'z') ? c - diff : c + diff;
        return lastPos[target] != -1 && lastPos[target] >= startPos;
    }
    
    private boolean isNiceString(int[] hash)
    {
        for (char c = 'a'; c <= 'z'; c++)
        {
            if (hash[c] > 0 && hash[c-diff] == 0)
            {
                return false;
            }
        }
        
        for (char c = 'A'; c <= 'Z'; c++)
        {
            if (hash[c] > 0 && hash[c+diff] == 0)
            {
                return false;
            }
        }
        
        return true;
    }
}
