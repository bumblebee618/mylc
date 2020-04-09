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
        if (words1 == null || words2 == null)
        {
            return words1 == null && words2 == null;
        }
        else if (words1.length != words2.length)
        {
            return false;
        }
        
        UnionFind uf = new UnionFind(pairs);
        int size = words1.length;
        
        for (int i = 0; i < size; i++)
        {
            if (words1[i].equals(words2[i]))
            {
                continue;
            }
            else if (uf.find(words1[i]).equals(""))
            {
                return false;
            }
            else if (!uf.find(words1[i]).equals(uf.find(words2[i])))
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
            
            for (List<String> pair : pairs)
            {
                String word1 = pair.get(0);
                String word2 = pair.get(1);
                
                if (!father.containsKey(word1))
                {
                    father.put(word1, word1);
                }
                
                if (!father.containsKey(word2))
                {
                    father.put(word2, word2);
                }
                
                union(word1, word2);
            }
        }
        
        public String find(String node)
        {
            if (!father.containsKey(node))
            {
                return "";
            }
            
            String parent = father.get(node);
            
            while (parent != father.get(parent))
            {
                parent = father.get(parent);
            }
            
            String temp = "";
            String fa = node;
            
            while (fa != father.get(fa))
            {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            
            return parent;
        }
        
        private void union(String node1, String node2)
        {
            String parent1 = find(node1);
            String parent2 = find(node2);
            
            if (!parent1.equals(parent2))
            {
                father.put(parent1, parent2);
            }
        }
    }
}
