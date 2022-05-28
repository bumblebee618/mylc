import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:

S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
 *
 */
public class Q824_Goat_Latin {
	public String toGoatLatin(String str) {
        if (str == null || str.length() == 0)
        {
            return str;
        }
        
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        StringBuilder builder = new StringBuilder();
        builder.append("a");
        
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        
        for (int i = 0; i < words.length; i++)
        {
            words[i] = words[i].trim();
            
            if (words[i].length() == 0)
            {
                continue;
            }
            
            char c = words[i].charAt(0);
            
            if (vowels.contains(c))
            {
                result.append(words[i]).append("ma").append(builder.toString()).append(" ");
            }
            else
            {
                result.append(words[i].substring(1)).append(c).append("ma").append(builder.toString()).append(" ");
            }
            
            builder.append("a");
        }
        
        return result.substring(0, result.length()-1);
    }
}
