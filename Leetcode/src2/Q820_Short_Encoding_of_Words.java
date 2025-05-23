import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:

words.length == indices.length
The reference string s ends with the '#' character.
For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

 

Example 1:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
Example 2:

Input: words = ["t"]
Output: 2
Explanation: A valid encoding would be s = "t#" and indices = [0].

 

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 7
words[i] consists of only lowercase letters.
 */
public class Q820_Short_Encoding_of_Words 
{
	// solution 1: use Trie, time is O(n * word.length)
	// test case 1: ["time", "time"]
    // test case 2: ["t", "time", "time"]
	// test case 3: ["time", "me", "bell"]
	private Trie root = new Trie();

    public int minimumLengthEncoding(String[] words) 
	{
		if (words == null || words.length == 0)
		{
			return 0;
		}
		
		int result = 0;
		
        for (String word : words)
        {
            addWord(word);
        }
        
        for (String word : words)
        {
            if (!canMerge(word))
            {
                result += word.length()+1;
            }
        }
        
        return result;
    }
    
    private void addWord(String word)
    {
        if (word == null || word.length() == 0)
        {
            return;
        }
        
        Trie node = root;
        
        for (int i = word.length()-1; i >= 0; i--)
        {
            char c = word.charAt(i);
            
            if (node.children[c-'a'] == null)
            {
                node.children[c-'a'] = new Trie();
            }
            
            node = node.children[c-'a'];
            node.count++;
        }
    }
    
    private boolean canMerge(String word)
    {
        if (word == null || word.length() == 0)
        {
            return false;
        }
        
        Trie node = root;
        
        for (int i = word.length()-1; i >= 0; i--)
        {
            char c = word.charAt(i);
            
            if (node.children[c-'a'].count == 1)
            {
                return false;
            }
            
            node = node.children[c-'a'];
        }
        
        for (Trie child : node.children)
        {
        	if (child != null)
        	{
        		return true;
        	}
        }
        
        if (node.count > 0)
        {
        	node.count = 0;
        	return false;
        }
        else
        {
        	return true;
        }
    }
    
    class Trie
    {
        public Trie[] children;
        public int count;
        
        public Trie()
        {
            children = new Trie[26];
            count = 0;
        }
    }
	
	
    // solution 2: sort+string, time is O(n^2)
	public int minimumLengthEncoding2(String[] words) 
	{
		if (words == null || words.length == 0)
		{
			return 0;
		}
		
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		int size = words.length, result = 0;
		
		for (int i = 0; i < size; i++)
		{
			boolean canMerge = false;
			
			for (int j = i+1; j < size; j++)
			{
				if (words[j].endsWith(words[i]))
				{
					canMerge = true;
					break;
				}
			}
			
			result += canMerge ? 0 : words[i].length()+1;
		}
		
		return result;
    }
	
	
	
	/********************************** main **********************************/
	
	public static void main(String[] args)
	{
		Q820_Short_Encoding_of_Words test = new Q820_Short_Encoding_of_Words();
		String[] words1 = {"time", "me", "bell"};
		String[] words2 = {"t"}; 
		String[] words3 = {"time", "time"}; 
		String[] words4 = {"t", "time", "time"}; 
		
		System.out.println(test.minimumLengthEncoding(words1));
		System.out.println(test.minimumLengthEncoding(words2));
		System.out.println(test.minimumLengthEncoding(words3));
		System.out.println(test.minimumLengthEncoding(words4));
	}
}
