import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.

Example 1:

Input: "cdadabcc"
Output: "adbc"
Example 2:

Input: "abcd"
Output: "abcd"
Example 3:

Input: "ecbacba"
Output: "eacb"
Example 4:

Input: "leetcode"
Output: "letcod"
 

Constraints:

1 <= text.length <= 1000
text consists of lowercase English letters.
Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
 */
public class Q1081_Smallest_Subsequence_of_Distinct_Characters {
	public String smallestSubsequence(String s) {
        if (s == null || s.length() <= 1)
        {
            return s;
        }
        
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        int[] hash = new int[256];
        
        for (int i = 0; i < s.length(); i++)
        {
            hash[s.charAt(i)] = i;
        }

        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!visited.contains(c))
            {
                while(!stack.isEmpty() && c < stack.peek() && hash[stack.peek()] > i)
                {
                    visited.remove(stack.pop());
                }
                
                visited.add(c);
                stack.push(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : stack) 
        {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
