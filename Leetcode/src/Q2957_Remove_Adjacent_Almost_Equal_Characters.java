/***
 * 
 * @author jackie
 *
 * You are given a 0-indexed string word.

In one operation, you can pick any index i of word and change word[i] to any lowercase English letter.

Return the minimum number of operations needed to remove all adjacent almost-equal characters from word.

Two characters a and b are almost-equal if a == b or a and b are adjacent in the alphabet.

 

Example 1:

Input: word = "aaaaa"
Output: 2
Explanation: We can change word into "acaca" which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.
Example 2:

Input: word = "abddez"
Output: 2
Explanation: We can change word into "ybdoez" which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.
Example 3:

Input: word = "zyxyxyz"
Output: 3
Explanation: We can change word into "zaxaxaz" which does not have any adjacent almost-equal characters. 
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 3.
 

Constraints:

1 <= word.length <= 100
word consists only of lowercase English letters.
 *
 */
public class Q2957_Remove_Adjacent_Almost_Equal_Characters {
	public int removeAlmostEqualCharacters(String word) {
        if (word == null || word.length() <= 1) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while (index < word.length() - 1) {
            if (Math.abs(word.charAt(index) - word.charAt(index + 1)) <= 1) {
                count++;
                index += 2;
            } else {
                index++;
            }
        }
        
        return count;
    }
	
	public int removeAlmostEqualCharacters2(String word) {
        if (word == null || word.length() <= 1) {
            return 0;
        }

        int[][] dp = new int[word.length()][2];
        dp[0][1] = 1;

        for (int i = 1; i < word.length(); i++) {
            if (Math.abs(word.charAt(i-1) - word.charAt(i)) <= 1) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][0] + 1;
            } else {
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + 1;
            }
        }

        return Math.min(dp[word.length()-1][0], dp[word.length()-1][1]);
    }
}
