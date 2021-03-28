/**
 * 
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 *
 */
public class Q541_Reverse_String_II {
	public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return s;
        }
        
        char[] letters = s.toCharArray();
        int len = s.length();
        int front = (k-1 >= len) ? len-1 : k-1;
        int back = 0;
        
        while (front < len) {
            reverse(letters, front, back);
            front += 2*k;
            back += 2*k;
            
            if (front >= len && back < len) {
                front = len-1;
            }
        }
        
        return new String(letters);
    }
    
    private void reverse(char[] letters, int front, int back) {
        while (front >= back) {
            char temp = letters[front];
            letters[front] = letters[back];
            letters[back] = temp;
            front--;
            back++;
        }
    }
}
