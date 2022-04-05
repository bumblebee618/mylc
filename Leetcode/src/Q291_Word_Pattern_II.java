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
        
        return search(0, 0, new HashMap<>(), new HashSet<>());
    }
    
    private boolean search(int patternStart, int strStart, Map<Character, String> map, Set<String> visited) {
        if (patternStart == pattern.length() || strStart == str.length()) {
            return patternStart == pattern.length() && strStart == str.length() ? true : false;
        }
        
        char c = pattern.charAt(patternStart);
        
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c), strStart)) {
                return false;
            }
            
            return search(patternStart+1, strStart+map.get(c).length(), map, visited);
        } else {
            for (int end = strStart; end < str.length(); end++) {
                String newWord = str.substring(strStart, end+1);
                
                if (visited.contains(newWord)) {
                    continue;
                }
                
                map.put(c, newWord);
                visited.add(newWord);
                
                if (search(patternStart+1, end+1, map, visited)) {
                    return true;
                }
                
                map.remove(c);
                visited.remove(newWord);
            }
        }
        
        return false;
    }
}
