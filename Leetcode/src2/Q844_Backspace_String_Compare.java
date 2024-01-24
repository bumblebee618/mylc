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
	public boolean backspaceCompare(String s, String t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        
        int index1 = s.length()-1, index2 = t.length()-1;
        int delete = 0;
        
        while (index1 >= 0 || index2 >= 0) {
            while (index1 >= 0 && s.charAt(index1) == '#') {
                delete = 0;
                while (index1 >= 0) {
                    delete += s.charAt(index1--) == '#' ? 1 : -1;
                    if (delete == 0) {
                        break;
                    }
                }
            }
            
            while (index2 >= 0 && t.charAt(index2) == '#') {
                delete = 0;
                while (index2 >= 0) {
                    delete += t.charAt(index2--) == '#' ? 1 : -1;
                    if (delete == 0) {
                        break;
                    }
                }
            }
            
            if (index1 >= 0 && index2 >= 0 && s.charAt(index1) != t.charAt(index2)) {
                return false;
            } else if ((index1 >= 0) != (index2 >= 0)) {
                return false;
            } else {
                index1--;
                index2--;
            }
        }
        
        return index1 < 0 && index2 < 0;
    }
	
	
	
	// Time complexity O(n), space complexity O(1)
	public boolean backspaceCompare(String S, String T) 
    {
        if (S == null || S.length() == 0 || T == null || T.length() == 0)
        {
            return false;
        }
        
        int s_index = S.length()-1;
        int t_index = T.length()-1;
        int s_skip = 0, t_skip = 0;
        
        while (s_index >= 0 || t_index >= 0)
        {
        	// While there may be chars in build(S) or build (T)
            while (s_index >= 0)
            {
                if (S.charAt(s_index) == '#')
                {
                    s_skip++;
                    s_index--;
                }
                else if (s_skip > 0)
                {
                    s_skip--;
                    s_index--;
                }
                else
                {
                    break;
                }
            }
            
            // Find position of next possible char in build(T)
            while (t_index >= 0)
            {
                if (T.charAt(t_index) == '#')
                {
                    t_skip++;
                    t_index--;
                }
                else if (t_skip > 0)
                {
                    t_skip--;
                    t_index--;
                }
                else
                {
                    break;
                }
            }
            
            if (s_index >= 0 && t_index >= 0 && S.charAt(s_index) != T.charAt(t_index))
            {
                return false;
            }
            else if ((s_index >= 0) != (t_index >= 0))
            {
                return false;
            }
            else
            {
                s_index--;
                t_index--;
            }
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
