import java.util.*;
/**************************************
You are given a string, s, and a list of words, words, that are all of the same length. 
Find all starting indices of substring(s) in s that is a concatenation of each word in 
words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

 * 
 * 
 * 应用类似hash[256]的方法
 * 字符用hash[256], string用hahsmap
 * 
 **************************************/

public class Q030_Substring_with_Concatenation_of_All_Words 
{
	public List<Integer> findSubstring(String s, String[] words) 
	{
        List<Integer> result = new LinkedList<>();
        
        if (s == null || s.length() == 0 || words == null || words.length == 0)
        {
            return result;
        }
        
        Map<String, Integer> wordMap = new HashMap<>();
        int wordLen = words[0].length();
        int wordNum = words.length;
        
        for (String word : words)
        {
            wordMap.put(word, wordMap.getOrDefault(word, 0)+1);
        }
        
        int size = s.length();
        
        for (int start = 0; start+(wordLen*wordNum) <= size; start++)
        {
            String word = s.substring(start, start+wordLen);
            
            if (!wordMap.containsKey(word))
            {
                continue;
            }
            
            Map<String, Integer> map = new HashMap<>(wordMap);
            
            for (int i = 0; i < wordNum; i++)
            {
                int index = start + i*wordLen;
                String nextWord = s.substring(index, index+wordLen);
                
                if (!map.containsKey(nextWord))
                {
                    break;
                }
                
                updateMap(map, nextWord);
            }
            
            if (map.size() == 0)
            {
                result.add(start);
            }
        }
        
        return result;
    }
    
    private void updateMap(Map<String, Integer> map, String word)
    {
        int count = map.get(word);
            
        if (count == 1)
        {
            map.remove(word);
        }
        else
        {
            map.put(word, count-1);
        }
    }
}
