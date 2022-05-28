import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 
 * @author jackie
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 

Note:

1 <= paragraph.length <= 1000.
0 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class Q819_Most_Common_Word {
	public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0)
        {
            return "";
        }
        
        Set<String> bannedSet = new HashSet<>();
        
        for (String word : banned)
        {
            bannedSet.add(word);
        }
        
        Map<String, Integer> map = new HashMap<>();
        StringBuilder builder = null;
        
        for (int i = 0; i < paragraph.length(); i++)
        {
            char c = paragraph.charAt(i);
            
            if (Character.isLetter(c))
            {
                if (builder == null)
                {
                    builder = new StringBuilder();
                }
                
                builder.append(c);
            }
            
            if (!Character.isLetter(c) || i == paragraph.length()-1)
            {
                if (builder != null)
                {
                    String word = builder.toString().toLowerCase();
                    builder = null;
                    
                    if (!bannedSet.contains(word))
                    {
                        map.put(word, map.getOrDefault(word, 0)+1);
                    }
                }
            }
        }
        
        int maxFreq = 0;
        String result = "";
        
        for (String word : map.keySet())
        {
            if (map.get(word) > maxFreq)
            {
                maxFreq = map.get(word);
                result = word;
            }
        }
        
        return result;
    }
}
