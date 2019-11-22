/**
 * 
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 */
public class Q680_Valid_Palindrome_II {
	// time complexity is O(n)
	public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0, right = s.length() - 1;
        int count = 0;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left+1, right) || isPalindrome(s, left, right-1);
            } 
            
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        
        return true;
    }
}
