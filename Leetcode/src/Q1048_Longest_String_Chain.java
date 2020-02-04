import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 

Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
 */
public class Q1048_Longest_String_Chain {
	private Map<Integer, Set<String>> map = new HashMap<>();
    private Map<String, Integer> lenMap = new HashMap<>();
    
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0)
        {
            return 0;
        }

        int startLen = Integer.MAX_VALUE;
        int endLen = Integer.MIN_VALUE;
        
        for (String word : words)
        {
            int len = word.length();
            startLen = Math.min(startLen, len);
            endLen = Math.max(endLen, len);
            
            if (!map.containsKey(len))
            {
                map.put(len, new HashSet<>());
            }
            
            map.get(len).add(word);
            lenMap.put(word, 1);
        }
        
        for (int i = startLen; i < endLen; i++)
        {   
            for (String word : map.get(i))
            {
                updateLenMap(word, map.get(i+1));
            }
        }
        
        int result = 1;
        
        for (String word : lenMap.keySet())
        {
            result = Math.max(result, lenMap.get(word));
        }
            
        
        return result;
    }
    
    private void updateLenMap(String curWord, Set<String> nextWordSet)
    {
        for (String word : nextWordSet)
        {
            int index = 0;
            
            while (index < curWord.length() && curWord.charAt(index) == word.charAt(index))
            {
                index++;
            }
            
            if (index == curWord.length())
            {
                if (lenMap.get(word) < lenMap.get(curWord)+1)
                {
                    lenMap.put(word, lenMap.get(curWord)+1);
                }
                
                continue;
            }
            
            String newWord = word.substring(0, index) + word.substring(index+1);
            
            if (curWord.equals(newWord))
            {
                if (lenMap.get(word) < lenMap.get(curWord)+1)
                {
                    lenMap.put(word, lenMap.get(curWord)+1);
                }
            }
        }
    }
}
