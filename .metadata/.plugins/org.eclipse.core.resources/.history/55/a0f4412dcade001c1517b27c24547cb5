import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class Q734_Sentence_Similarity {
	public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length)
        {
            return false;
        }
        
        Map<String, Set<String>> map = new HashMap<>();
        
        if (pairs != null && pairs.size() > 0)
        {
            for (List<String> pair : pairs)
            {
                if (pair != null && pair.size() == 2)
                {
                    String word1 = pair.get(0);
                    String word2 = pair.get(1);
                    map.computeIfAbsent(word1, k -> new HashSet<String>());
                    map.get(word1).add(word2);
                    map.computeIfAbsent(word2, k -> new HashSet<String>());
                    map.get(word2).add(word1);
                }
            }
        }
        
        for (int i = 0; i < words1.length; i++)
        {
            if (words1[i].equals(words2[i]))
            {
                continue;
            }
            else if (!map.containsKey(words1[i]))
            {
                return false;
            }
            else if (!map.get(words1[i]).contains(words2[i]))
            {
                return false;
            }
        }
        
        return true;
    }
}
