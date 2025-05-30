import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.
 

Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0
 

Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.
 */
public class Q1297_Maximum_Number_of_Occurrences_of_a_Substring 
{
	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        if (maxLetters <= 0 || minSize < 0 || maxSize < 0 || s == null || s.length() < minSize) {
            return 0;
        }

        int[] hash = new int[256];
        int uniqueLetterNum = 0;
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;

        for (int front = 0, back = 0; front < s.length(); front++) {
            if (++hash[s.charAt(front)] == 1) {
                uniqueLetterNum++;
            }
            
            // Use front - back + 1 > minSize to check substring, e.g "ab" from "aab" as its frequency >= its parent's
            while (uniqueLetterNum > maxLetters || front - back + 1 > minSize) {
                if (--hash[s.charAt(back++)] == 0) {
                    uniqueLetterNum--;
                }
            }

            if (front - back + 1 >= minSize && front - back + 1 <= maxSize) {
                String key = s.substring(back, front+1);
                map.put(key, map.getOrDefault(key, 0) + 1);
                maxCount = Math.max(maxCount, map.get(key));
            }
        }

        return maxCount;
    }
	
	
	// Solution 2
	public int maxFreq2(String s, int maxLetters, int minSize, int maxSize) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        else if (maxLetters <= 0 || maxLetters > s.length())
        {
            return 0;
        }
        else if (minSize < 0 || maxSize < 0 || minSize > maxSize || minSize > s.length() || maxSize > s.length())
        {
            return 0;
        }
        
        int maxFrequency = 0;
        int[] hash = new int[256];
        int uniqueLetters = 0;
        Map<String, Integer> occurence = new HashMap<>();
        
        for (int index = 0; index < s.length(); index++)
        {
        	// 先加再移除
        	if (hash[s.charAt(index)]++ == 0)
            {
                uniqueLetters++;
            }
        	
        	if (index >= minSize)
        	{
        		if (hash[s.charAt(index-minSize)]-- == 1)
                {
                    uniqueLetters--;
                }
        	}
        	
            if (index >= minSize-1 && uniqueLetters <= maxLetters)
            {
            	String substr = s.substring(index-minSize+1, index+1);
                int frequency = occurence.getOrDefault(substr, 0) + 1;
            	occurence.put(substr, frequency);
                maxFrequency = Math.max(maxFrequency, frequency);
            }
        }
        
        return maxFrequency;
    }
	
	
    
    
    
    
    /********************************* main *********************************/ 
    
    public static void main(String[] args)
    {
    	Q1297_Maximum_Number_of_Occurrences_of_a_Substring test = new Q1297_Maximum_Number_of_Occurrences_of_a_Substring();
    	String s = "aabcabcab";
    	System.out.println(test.maxFreq(s, 2, 2, 3));
    }
}
