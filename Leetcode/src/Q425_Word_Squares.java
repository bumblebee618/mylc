import java.util.*;
/*******
 * 
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d col
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

 * 
 * */


public class Q425_Word_Squares {
	private TrieNode root = new TrieNode();
	
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        
        if(words == null || words.length == 0) {
        	return result;
        }
        
        buildTrie(words);
        backtrack(result, words[0].length(), new ArrayList<>());
        return result;
    }
    
    public void backtrack(List<List<String>> result, int targetLen, List<String> list) {
        if(list.size() == targetLen) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        int curIndex = list.size();
        StringBuilder bulider = new StringBuilder();
        
        // Find next word. 
        // Using characters which index is curIndex in each string (vertical direction) to form a new string 
        // and then check whether there is a string which is equals to this new string.
        for(String s : list) {
            bulider.append(s.charAt(curIndex));
        }
        
        String str = bulider.toString();
        TrieNode node = root;
        
        for(int i = 0; i < str.length(); i++) {
            if(node.children[str.charAt(i) - 'a'] != null) {
                node = node.children[str.charAt(i) - 'a'];
            } else {
                node = null;
                break;
            }
        }
        
        // backtracking
        if(node != null) {
            for(String next : node.words) {
                list.add(next);
                backtrack(result, targetLen, list);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public void buildTrie(String[] words) {
        for(String word : words) {
            TrieNode node = root;
            char[] array = word.toCharArray();
            
            for(char c : array) {
                node.words.add(word);
                
                if(node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                
                node = node.children[c - 'a'];
            }
            
            node.words.add(word);
        }
    }
    
    class TrieNode {
        TrieNode[] children; 
        Set<String> words;
        
        public TrieNode() {
        	children = new TrieNode[26];
        	words = new HashSet<>();
        }
    }
}
