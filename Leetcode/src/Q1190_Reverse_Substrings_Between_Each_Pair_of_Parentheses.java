import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * You are given a string s that consists of lower case English letters and brackets. 

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
Example 4:

Input: s = "a(bcdefghijkl(mno)p)q"
Output: "apmnolkjihgfedcbq"
 

Constraints:

0 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It's guaranteed that all parentheses are balanced.
 */
public class Q1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses {
	// solution 1: use stack
	public String reverseParentheses(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c != ')') {
                stack.push(String.valueOf(c));
                continue;
            }
            
            StringBuilder strBuilder = new StringBuilder();
            
            while (!stack.isEmpty() && !stack.peek().equals("(")) {
                strBuilder.insert(0, stack.pop());
            }
            
            if (!stack.isEmpty()) {
                stack.pop();
            }
            
            stack.push(strBuilder.reverse().toString());
        }
        
        StringBuilder builder = new StringBuilder();
        
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }
        
        return builder.toString();
    }
	
	
	
	
	// solution 2: use recursive
	public String reverseParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        StringBuilder builder = new StringBuilder();
        int index = 0;
        
        while (index < s.length()) {
            int start = index;
            
            while (start < s.length() && s.charAt(start) != '(') {
                start++;    
            }
            
            builder.append(s.substring(index, start));
            
            if (start >= s.length()) {
                break;
            } 
            
            int end = findClosePos(s, start);
            
            if (end == -1) {
                return "";
            }
            
            String str = new StringBuilder(reverseParentheses(s.substring(start+1, end))).reverse().toString();
            builder.append(str);
            index = end+1;
        }
        
        return builder.toString();
    }
    
    private int findClosePos(String s, int index) {
        int count = 0;
        
        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                count++;
            } else if (s.charAt(index) == ')') {
                count--;
            }
            
            if (count == 0) {
                return index;
            }
            
            index++;
        }
        
        return -1;
    }
}
