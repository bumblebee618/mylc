import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
 */
public class Q745_Prefix_and_Suffix_Search 
{
	private Trie root1 = new Trie();
	private Trie root2 = new Trie();
    
    public Q745_Prefix_and_Suffix_Search(String[] words) 
    {
        if (words == null || words.length == 0)
		{
			return;
		}
		
		for (int i = words.length-1; i >= 0; i--)
		{
			insertWord(words[i], i);
		}
    }
    
    public int f(String prefix, String suffix) 
    {
        List<Integer> candidates1 = search(prefix, true);
        List<Integer> candidates2 = search(suffix, false);
        
        if (candidates1 == null || candidates2 == null)
        {
        	return -1;
        }
        
        int index1 = 0, index2 = 0;
        int size1 = candidates1.size(), size2 = candidates2.size();
        
        while (index1 < size1 && index2 < size2)
        {
        	int candidate1 = candidates1.get(index1);
        	int candidate2 = candidates2.get(index2);
        	
        	if (candidate1 == candidate2)
        	{
        		return candidate1;
        	}
        	else if (candidate1 > candidate2)
        	{
        		index1++;
        	}
        	else 
        	{
        		index2++;
			}
        }
        
        return -1; 
    }
    
    private void insertWord(String word, int index)
    {
    	if (word == null || word.length() == 0)
    	{
    		return;
    	}
    	
    	Trie node1 = root1, node2 = root2;
    	char[] letters = word.toCharArray();
    	
    	for (int i = 0; i < letters.length; i++)
    	{
    		if (node1.children[letters[i]-'a'] == null)
    		{
    			node1.children[letters[i]-'a'] = new Trie();
    		}
    		
    		node1 = node1.children[letters[i]-'a'];
    		node1.candidates.add(index);
    	}
    	
    	for (int i = letters.length-1; i >= 0; i--)
    	{
    		if (node2.children[letters[i]-'a'] == null)
    		{
    			node2.children[letters[i]-'a'] = new Trie();
    		}
    		
    		node2 = node2.children[letters[i]-'a'];
    		node2.candidates.add(index);
    	}
    }
    
    private List<Integer> search(String wildCard, boolean isPrefix)
    {
    	if (wildCard == null || wildCard.length() == 0)
    	{
    		return null;
    	}
    	
    	Trie node = isPrefix ? root1 : root2;
    	char[] letters = wildCard.toCharArray();
    	
    	if (isPrefix)
    	{
    		for (int i = 0; i < letters.length; i++)
        	{
        		if (node.children[letters[i]-'a'] == null)
        		{
        			return null;
        		}
        		
        		node = node.children[letters[i]-'a'];
        	}
    	}
    	else
    	{
    		for (int i = letters.length-1; i >= 0; i--)
        	{
        		if (node.children[letters[i]-'a'] == null)
        		{
        			return null;
        		}
        		
        		node = node.children[letters[i]-'a'];
        	}
    	}
    	
    	return node.candidates; 
    }
    
    class Trie
    {
    	public Trie[] children;
    	public List<Integer> candidates;
    	
    	public Trie()
    	{
    		children = new Trie[26];
    		candidates = new ArrayList<>();
    	}
    }
}


