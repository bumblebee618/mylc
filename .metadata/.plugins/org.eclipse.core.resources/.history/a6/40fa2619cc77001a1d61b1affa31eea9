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
        if (s == null || t == null)
        {
            return s == null && t == null ? true : false;
        }
        
        int index1 = s.length()-1;
        int index2 = t.length()-1;
        
        while (index1 >= 0 || index2 >= 0)
        {
            index1 = findValidIndex(s, index1);
            index2 = findValidIndex(t, index2);
            
            if (index1 >= 0 && index2 >= 0)
            {              
                if (s.charAt(index1) != t.charAt(index2))
                {
                    return false;
                }
                
                index1--;
                index2--;
            }
            else
            {
                break;
            }
        }
        
        return index1 < 0 && index2 < 0;
    }
    
    private int findValidIndex(String str, int index)
    {
        while (index >= 0 && str.charAt(index) == '#')
        {
            int count = 0;
        
            while (index >= 0)
            {
                if (str.charAt(index) == '#')
                {
                    count++;
                }
                else
                {
                    count--;
                }
            
                index--;
                
                if (count == 0)
                {
                    break;
                }
            }
        }
 
        return index;
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
