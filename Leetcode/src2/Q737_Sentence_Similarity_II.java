import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class Q737_Sentence_Similarity_II {
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length)
        {
            return false;
        }

        UnionFind uf = new UnionFind(pairs);
        
        for (int i = 0; i < words1.length; i++)
        {
            String parent1 = uf.find(words1[i]);
            String parent2 = uf.find(words2[i]);
            
            if (parent1.equals(parent2))
            {
                continue;
            }
            else if (parent1.equals(""))
            {
                return false;
            }
            else if (!parent1.equals(parent2))
            {
                return false;
            }
        }
        
        return true;
    }
    
    class UnionFind
    {
        private Map<String, String> father;
        
        public UnionFind(List<List<String>> pairs)
        {
            father = new HashMap<>();
            
            if (pairs == null || pairs.size() == 0)
            {
                return;
            }
            
            for (List<String> pair : pairs)
            {
                if (pair == null || pair.size() != 2)
                {
                    continue;
                }
                
                String word1 = pair.get(0);
                String word2 = pair.get(1);
                father.computeIfAbsent(word1, k -> word1);
                father.computeIfAbsent(word2, k -> word2);
                union(word1, word2);
            }
        }
        
        public String find(String word)
        {
            if (!father.containsKey(word))
            {
                return "";
            }
            
            String parent = father.get(word);
            
            while (!parent.equals(father.get(parent)))
            {
                parent = father.get(parent);
            }
            
            while (!word.equals(father.get(word)))
            {
                String temp = father.get(word);
                father.put(word, parent);
                word = temp;
            }
            
            return parent;
        }
        
        public void union(String word1, String word2)
        {
            String parent1 = find(word1);
            String parent2 = find(word2);
            
            if (!parent1.equals(parent2))
            {
                father.put(parent1, parent2);
            }
        }
    }
}
