/***
 * 
 * @author jackie
 * 
 * There is a special keyboard with all keys in a single row.

Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.

You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.

 

Example 1:

Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
Output: 4
Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
Total time = 2 + 1 + 1 = 4. 
Example 2:

Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
Output: 73
 

Constraints:

keyboard.length == 26
keyboard contains each English lowercase letter exactly once in some order.
1 <= word.length <= 10^4
word[i] is an English lowercase letter.
 */
public class Q1165_Single_Row_Keyboard {
	public int calculateTime(String keyboard, String word) {
        if (keyboard == null || keyboard.length() != 26 || word == null || word.length() == 0)
        {
            return 0;        
        }
        
        int[] hash = new int[256];
        int cost = 0;
        int index = 0;
        
        for (int i = 0; i < keyboard.length(); i++)
        {
            hash[keyboard.charAt(i)] = i;
        }
        
        for (int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            cost += Math.abs(hash[c]-index);
            index = hash[c];
        }
        
        return cost;
    }
}
