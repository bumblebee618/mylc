import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]
 

Constraints:

1 <= pattern.length <= 20
1 <= words.length <= 50
words[i].length == pattern.length
pattern and words[i] are lowercase English letters.
 */
public class Q890_Find_and_Replace_Pattern 
{
	public List<String> findAndReplacePattern(String[] words, String pattern) 
    {
        List<String> result = new LinkedList<>();
        
        if (words == null || words.length == 0 || pattern == null || pattern.length() == 0)
        {
            return result;
        }
        
        for (String word : words)
        {
            if (word == null || word.length() != pattern.length())
            {
                continue;
            }
            
            int[] map1 = new int[256];
            int[] map2 = new int[256];
            boolean found = true;
            
            for (int i = 0; i < word.length(); i++)
            {
                char c1 = pattern.charAt(i);
                char c2 = word.charAt(i);
                
                if (map1[c1] != 0 && map1[c1] != c2)
                {
                    found = false;
                    break;
                }
                
                map1[c1] = c2;
                
                if (map2[c2] != 0 && map2[c2] != c1)
                {
                    found = false;
                    break;
                }
                
                map2[c2] = c1;
            }
            
            if (found)
            {
                result.add(word);
            }
        }
        
        return result;
    }
}
