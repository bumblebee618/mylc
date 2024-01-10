/***
 * 
 * @author jackie
 * 
 * Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
public class Q1358_Number_of_Substrings_Containing_All_Three_Characters {
	public int numberOfSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] letters = s.toCharArray();
        int result = 0;
        int[] hash = new int[256];

        for (int front = 0, back = 0; front < letters.length; front++) {
            hash[letters[front]]++;

            while (hash['a'] >= 1 && hash['b'] >= 1 && hash['c'] >= 1) {
                result += letters.length - front;
                hash[letters[back++]]--;
            }
        }

        return result;
    }
}
