/********
 * 
Design a data structure that supports the following two operations:
	void addWord(word)
	bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:
	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true
	
Note:
	You may assume that all words are consist of lowercase letters a-z.
	
 * 
 * */

public class Q211_Add_and_Search_Word_Data_structure_design 
{
	private TrieNode root;
    
    /** Initialize your data structure here. */
    public Q211_Add_and_Search_Word_Data_structure_design() 
    {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) 
    {
        if (word == null || word.length() == 0)
        {
            return;
        }
        
        TrieNode node = root;
        
        for (char c : word.toCharArray())
        {
            if (node.children[c] == null)
            {
                node.children[c] = new TrieNode();
            }
            
            node = node.children[c];
        }
        
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) 
    {
        if (word == null || word.length() == 0)
        {
            return false;
        }
        
        return searchWord(word, 0, root);
    }
    
    private boolean searchWord(String word, int index, TrieNode node)
    {
        if (index == word.length())
        {
            return node.isWord;
        }
        
        char c = word.charAt(index);
            
        if (c != '.')
        {
            return node.children[c] == null ? false : searchWord(word, index+1, node.children[c]);
        }
        else
        {
            for (TrieNode child : node.children)
            {
                if (child != null && searchWord(word, index+1, child))
                {
                    return true;
                }
            }
                
            return false;
        }
    }
    
    class TrieNode 
    {
        public TrieNode[] children;
        public boolean isWord;
        
        public TrieNode ()
        {
            children = new TrieNode[256];
            isWord = false;
        }
    }
}