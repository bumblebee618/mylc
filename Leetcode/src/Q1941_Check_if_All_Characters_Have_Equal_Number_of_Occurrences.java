/***
 * 
 * @author jackie
 * 
 * Given a string s, return true if s is a good string, or false otherwise.

A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).

 

Example 1:

Input: s = "abacbc"
Output: true
Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
Example 2:

Input: s = "aaabb"
Output: false
Explanation: The characters that appear in s are 'a' and 'b'.
'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class Q1941_Check_if_All_Characters_Have_Equal_Number_of_Occurrences {
	public boolean areOccurrencesEqual(String s) {
        if (s == null)
        {
            return true;
        }
        
        int[] times = new int[26];
        
        for (char c : s.toCharArray())
        {
            times[c-'a']++;
        }
        
        int time = -1;
        
        for (int i = 0; i < 26; i++)
        {
            if (times[i] == 0)
            {
                continue;
            }
            else if (time == -1)
            {
                time = times[i];
            }
            else if (time != times[i])
            {
                return false;
            }
        }
        
        return true;
    }
}
