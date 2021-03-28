/**
 * 
 Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 *
 */
public class Q557_Reverse_Words_in_a_String_III {
	public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }    
        
        StringBuilder sb = new StringBuilder();
        int front = 0, back = 0, len = s.length();
        
        while (front < len) {
            while (front < len && s.charAt(front) != ' ') {
                front++;
            }
            
            for (int i = front-1; i >= back; i--) {
                sb.append(s.charAt(i));
            }
            
            if (front < len) {
                sb.append(" ");
            }
            
            while (front < len && s.charAt(front) == ' ') {
                front++;
            }
            
            back = front;
        }
        
        return sb.toString();
    }
}
