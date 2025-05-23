/***
 * 
 * @author jackie
 * 
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

Implement the MagicDictionary class:

MagicDictionary() Initializes the object.
void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 

Example 1:

Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False
 

Constraints:

1 <= dictionary.length <= 100
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case English letters.
All the strings in dictionary are distinct.
1 <= searchWord.length <= 100
searchWord consists of only lower-case English letters.
buildDict will be called only once before search.
At most 100 calls will be made to search.
 */
public class Q676_Implement_Magic_Dictionary {
	private Trie root;

    /** Initialize your data structure here. */
    public Q676_Implement_Magic_Dictionary() 
    {
        root = new Trie();    
    }
    
    public void buildDict(String[] dictionary) 
    {
        if (dictionary == null || dictionary.length == 0)
        {
            return;
        }
        
        for (String word : dictionary)
        {
            insertWord(word);
        }
    }
    
    public boolean search(String searchWord) 
    {
        if (searchWord == null || searchWord.length() == 0)
        {
            return false;
        }
        
        return searchWord(root, searchWord, 0, false);
    }
    
    private boolean searchWord(Trie node, String word, int index, boolean alreadyUpdateOneChar)
    {
        if (index == word.length())
        {
            return node.isWord && alreadyUpdateOneChar;
        }
        
        for (int i = 0; i < node.children.length; i++)
        {
            if (node.children[i] == null)
            {
                continue;
            }
            
            if (word.charAt(index) == (char) i)
            {
                if (searchWord(node.children[i], word, index+1, alreadyUpdateOneChar))
                {
                    return true;
                }
            }
            else
            {
                if (!alreadyUpdateOneChar)
                {
                    if (searchWord(node.children[i], word, index+1, true))
                    {
                        return true;
                    }
                }
            }
        }
            
        return false;
    }
    
    private void insertWord(String word)
    {
        if (word == null || word.length() == 0)
        {
            return;
        }
        
        Trie node = root;
        
        for (char c : word.toCharArray())
        {
            if (node.children[c] == null)
            {
                node.children[c] = new Trie();
            }
            
            node = node.children[c];
        }
        
        node.isWord = true;
    }
    
    class Trie
    {
        public Trie[] children;
        public boolean isWord;
        
        public Trie()
        {
            children = new Trie[256];
            isWord = false;
        }
    }
}
