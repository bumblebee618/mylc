import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed. 

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]
 

Constraints:

1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
 */
public class Q1268_Search_Suggestions_System {
private Trie root = new Trie();
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new LinkedList<>();
        
        if (products == null || products.length == 0 || searchWord == null || searchWord.length() == 0)
        {
            return result;        
        }
        
        buildTrie(products);
        Trie node = root;
        
        for (char c : searchWord.toCharArray())
        {
            List<String> list = new LinkedList<>();
            
            if (node != null && node.children[c] != null)
            {
                node = node.children[c];
                
                if (node.suggestions.size() > 0)
                {
                    Queue<String> queue = new PriorityQueue<>(node.suggestions);
                    
                    while (!queue.isEmpty())
                    {
                        list.add(0, queue.poll());
                    }
                }
            }
            else
            {
                node = null;
            }
            
            result.add(list);
        }
        
        return result;
    }
    
    private void buildTrie(String[] products)
    {
        for (String product : products)
        {
            Trie node = root;
            
            for (char c : product.toCharArray())
            {
                if (node.children[c] == null)
                {
                    node.children[c] = new Trie();
                }
                
                node = node.children[c];
                node.suggestions.offer(product);
                
                if (node.suggestions.size() > 3)
                {
                    node.suggestions.poll();
                }
            }
        }
    }
    
    class Trie
    {
        public Trie[] children;
        public Queue<String> suggestions;
        
        public Trie()
        {
            children = new Trie[256];
            suggestions = new PriorityQueue<String>(4, (word1, word2)->word2.compareTo(word1));
        }
    }
}
