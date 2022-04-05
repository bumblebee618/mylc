import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 
 * Given a pattern and a string s, return true if s matches the pattern.
A string s matches a pattern if there is some bijective mapping of single characters to strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.
 
Example 1:
Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue"
Example 2:
Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
Example 3:
Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false
 
Constraints:
	• 1 <= pattern.length, s.length <= 20
	• pattern and s consist of only lowercase English letters.

 *
 */

public class Q291_Word_Pattern_II {
	private String pattern;
    private String str;
    
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
            return false;
        }
        
        this.pattern = pattern;
        this.str = str;
        return isMatch(0, 0, new HashMap<>(), new HashSet<>());
    }
  
    private boolean isMatch(int strIndex, int patternIndex, Map<Character, String> map, Set<String> set) {
        if (strIndex == str.length() || patternIndex == pattern.length()) {
            return strIndex == str.length() && patternIndex == pattern.length() ? true : false;
        }
    
        char c = pattern.charAt(patternIndex);
    
        if (map.containsKey(c)) {
            String s = map.get(c);
      
            if (!str.startsWith(s, strIndex)) {
                return false;
            }
      
            return isMatch(strIndex + s.length(), patternIndex + 1, map, set);
        } else {
            for (int k = strIndex; k < str.length(); k++) {
                String p = str.substring(strIndex, k + 1);

                if (set.contains(p)) {
                    continue;
                }

                map.put(c, p);
                set.add(p);
      
                if (isMatch(k + 1, patternIndex + 1, map, set)) {
                    return true;
                }

                // backtracking
                map.remove(c);
                set.remove(p);
            }
    
            return false;
        }
    }

}
