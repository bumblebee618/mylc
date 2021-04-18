import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

 

Example 1:

Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
Example 2:

Input: sentence = "leetcode"
Output: false
 

Constraints:

1 <= sentence.length <= 1000
sentence consists of lowercase English letters.
 */
public class Q1832_Check_if_the_Sentence_Is_Pangram 
{
	public boolean checkIfPangram(String sentence) 
    {
        if (sentence == null || sentence.length() < 26)
        {
            return false;
        }
        
        Set<Character> set = new HashSet<>();
        
        for (char c : sentence.toCharArray())
        {
            set.add(c);
            
            if (set.size() == 26)
            {
                break;
            }
        }
        
        return set.size() == 26;
    }
}
