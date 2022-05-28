/***
 * 
 * @author jackie
 * 
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 

Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.
 */
public class Q925_Long_Pressed_Name {
	public boolean isLongPressedName(String name, String typed) {
        if (name == null || name.length() == 0 || typed == null || typed.length() == 0 || name.length() > typed.length())
        {
            return false;
        }
        else if (name.length() == typed.length())
        {
            return name.equals(typed);
        }
        
        int index1 = 0, index2 = 0;
        int size1 = name.length(), size2 = typed.length();
        
        while (index1 < size1 || index2 < size2)
        {
            if (index1 < size1 && index2 < size2)
            {
                char c1 = name.charAt(index1);
                char c2 = typed.charAt(index2);
            
                if (c1 == c2)
                {
                    index1++;
                    index2++;
                }
                else if (index2 > 0 && c2 == typed.charAt(index2-1))
                {
                    index2++;
                }
                else
                {
                    return false;
                }
            }
            else if (index2 < size2)
            {
                char target = typed.charAt(index2-1);
                
                while (index2 < size2)
                {
                    if (typed.charAt(index2) != target)
                    {
                        return false;
                    }
                    
                    index2++;
                }
            }
            else
            {
                return false;
            }
        }
        
        return true;
    }
}
