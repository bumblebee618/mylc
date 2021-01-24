import java.util.*;

/*******
 * 
 * @author jackie
 * 
 * Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

public class Q242_Valid_Anagram 
{
	// using sort, time is O(nlogn), space is O(n)
	public boolean isAnagram(String s, String t) 
	{
        if (s == null || t == null) 
        {
            return s == null && t == null;
        } 
        else if (s.length() != t.length()) 
        {
            return false;
        }
        
        char[] letters1 = s.toCharArray();
        char[] letters2 = t.toCharArray();
        Arrays.sort(letters1);
        Arrays.sort(letters2);
        return new String(letters1).equals(new String(letters2));
    }
	
	
	
	// follow up: using hashtable, time is O(n), space is O(256)
	public boolean isAnagram2(String s, String t) {
        if(s == null && t == null) {
            return true;
        } else if(s == null || t == null) {
            return false;
        } else if(s.length() != t.length()) {
            return false;
        }
        
        int[] hash1 = new int[256];
        int[] hash2 = new int[256];
        
        for(int i = 0; i < s.length(); i++) {
            hash1[s.charAt(i)]++;
            hash2[t.charAt(i)]++;
        }
        
        for(int i = 0; i < 256; i++) {
            if(hash1[i] != hash2[i]) {
                return false;
            }
        }
        
        return true;
    }
	
	
}
