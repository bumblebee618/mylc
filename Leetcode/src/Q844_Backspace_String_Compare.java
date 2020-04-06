import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */
public class Q844_Backspace_String_Compare {
	// Time complexity O(n), space complexity O(1)
	public boolean backspaceCompare(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0)
        {
            return false;
        }
        
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) 
        { 
            // While there may be chars in build(S) or build (T)
            while (i >= 0) 
            { 
                // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') 
                {
                    skipS++; 
                    i--;
                }
                else if (skipS > 0) 
                {
                    skipS--; 
                    i--;
                }
                else 
                {
                    break;
                }
            }
            
            while (j >= 0) 
            { 
                // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') 
                {
                    skipT++; 
                    j--;
                }
                else if (skipT > 0) 
                {
                    skipT--; 
                    j--;
                }
                else 
                {
                    break;
                }
            }
            
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
            {
                return false;
            }
            
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
            {
                return false;
            }
            
            i--; 
            j--;
        }
        
        return true;
    }
    
    
    // Time complexity O(n), space complexity O(n)
    public boolean backspaceCompare2(String s, String t) {
        if (s == null || t == null)
        {
            return s == null && t == null ? true : false;
        }
        
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        
        for (char c : s.toCharArray())
        {
            if (c == '#')
            {
                if (!stack1.isEmpty())
                {
                    stack1.pop();
                }
            }
            else
            {
                stack1.push(c);
            }
        }
        
        for (char c : t.toCharArray())
        {
            if (c == '#')
            {
                if (!stack2.isEmpty())
                {
                    stack2.pop();
                }
            }
            else
            {
                stack2.push(c);
            }
        }
        
        if (stack1.size() != stack2.size())
        {
            return false;
        }
        
        while (!stack1.isEmpty())
        {
            if (stack1.pop() != stack2.pop())
            {
                return false;
            }
        }
        
        return true;
    }
}
