/*******
 * 
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
	Assume the length of given string will not exceed 1,010.

Example:
	Input:
	"abccccdd"
	Output:
	7

Explanation:
	One longest palindrome that can be built is "dccaccd", whose length is 7.

 * 
 * */

public class Q409_Longest_Palindrome {
	public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] hash = new int[256];
        int singleLetter = 0;
        int count = 0;
        
        for (char c : s.toCharArray()) {
            hash[c]++;
        }
        
        for (int i = 0; i < 256; i++) {
            if (hash[i] % 2 == 1) {
                singleLetter = 1;
            }
            count += (hash[i] / 2 * 2);
        }
        
        return count + singleLetter;
    }
}
