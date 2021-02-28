/***
 * 
 * @author jackie
 * 
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
 

Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class Q424_Longest_Repeating_Character_Replacement {
	public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k < 0 || s.length() < k)
        {
            return 0;
        }
        
        int[] hash = new int[26];
        int back = 0;
        int size = s.length();
        int result = 1;
        
        for (int front = 0; front < size; front++)
        {
            hash[s.charAt(front)-'A']++;
            
            while (!isValid(hash, k))
            {
                hash[s.charAt(back++)-'A']--;
            }
            
            result = Math.max(result, front-back+1);
        }
        
        return result;
    }
    
    private boolean isValid(int[] hash, int k)
    {
        int total = 0;
        int maxCount = 0;
        
        for (int count : hash)
        {
            total += count;
            maxCount = Math.max(maxCount, count);
        }
        
        return total-maxCount <= k;
    }
}
