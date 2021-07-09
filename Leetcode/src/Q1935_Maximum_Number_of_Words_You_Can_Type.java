import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.

Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.

 

Example 1:

Input: text = "hello world", brokenLetters = "ad"
Output: 1
Explanation: We cannot type "world" because the 'd' key is broken.
Example 2:

Input: text = "leet code", brokenLetters = "lt"
Output: 1
Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.
Example 3:

Input: text = "leet code", brokenLetters = "e"
Output: 0
Explanation: We cannot type either word because the 'e' key is broken.
 

Constraints:

1 <= text.length <= 104
0 <= brokenLetters.length <= 26
text consists of words separated by a single space without any leading or trailing spaces.
Each word only consists of lowercase English letters.
brokenLetters consists of distinct lowercase English letters.
 */
public class Q1935_Maximum_Number_of_Words_You_Can_Type 
{
	public int canBeTypedWords(String text, String brokenLetters) 
    {
        if (text == null || text.length() == 0)
        {
            return 0;
        }
        
        String[] words = text.split(" ");
        System.out.println(words.length);
        
        if (brokenLetters == null || brokenLetters.length() == 0)
        {
            return words.length;
        }
        
        Set<Character> set = new HashSet<>();
        
        for (char c : brokenLetters.toCharArray())
        {
            set.add(c);
        }
        
        int result = 0;
        
        for (String word : words)
        {
            boolean isValid = true;
            
            for (char c : word.toCharArray())
            {
                if (set.contains(c))
                {
                    isValid = false;
                    break;
                }
            }
            
            result += isValid ? 1 : 0;
        }
        
        return result;
    }
}
