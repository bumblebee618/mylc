/***
 * 
 * @author jackie
 * 
 * Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it was not in the typed output.
 

Constraints:

1 <= name.length, typed.length <= 1000
name and typed consist of only lowercase English letters.
 */
public class Q925_Long_Pressed_Name {
	public boolean isLongPressedName(String name, String typed) {
        if (name == null || typed == null || name.length() > typed.length()) {
            return false;
        }

        int index1 = 0, index2 = 0;
        
        while (index1 < name.length() && index2 < typed.length()) {
            char target = name.charAt(index1);
            int count1 = 0;

            while (index1 < name.length() && name.charAt(index1) == target) {
                index1++;
                count1++;
            }

            int count2 = 0;

            while (index2 < typed.length() && typed.charAt(index2) == target) {
                index2++;
                count2++;
            }

            if (count1 > count2) {
                return false;
            }
        }

        return index1 == name.length() && index2 == typed.length();
    }
}
