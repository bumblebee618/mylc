/***
 * 
 * Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome strings or false otherwise.

 

Example 1:

Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
Example 2:

Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the characters of s.
Example 3:

Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= 105
 *
 */

public class Q1400_Construct_K_Palindrome_Strings {
	public boolean canConstruct(String s, int k) {
        if (s == null || s.length() == 0 || k < 0 || s.length() < k) {
            return false;
        } else if (s.length() == k) {
            return true;
        }
        
        int[] hash = new int[256];
        int single = 0;
        
        for (char c : s.toCharArray()) {
            hash[c]++;
        }
        
        for (int num : hash) {
            single += num % 2;
        }
        
        return single <= k;
    }
}
