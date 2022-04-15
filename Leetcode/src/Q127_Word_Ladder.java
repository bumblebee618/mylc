import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.List;

/*****
 * 
Given two words (beginWord and endWord), and a dictionary's word list, 
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

 *
 * */

public class Q127_Word_Ladder {
	/*******************************************************************
	 * 此题可以理解为是一种状态图，从而进行bfs查找
	 *  
	 *******************************************************************/
	// test case: 
    // beginWord == null || endWord == null
    // beginWord == endWord
    // wordList is empty
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return 0;
        } else if (beginWord.equals(endWord)) {
            return 0;
        }
        
        Set<String> wordSet = new HashSet<>();
        
        for (String word : wordList) {
            wordSet.add(word);
        }
        
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        int step = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                
                if (curWord.equals(endWord)) {
                    return step;
                }
                
                for (String nextWord : findNextWords(curWord, wordSet, visited)) {
                    queue.offer(nextWord);
                    visited.add(nextWord);
                }
            }
            
            step++;
        }
        
        return 0;
    }
    
    private List<String> findNextWords(String curWord, Set<String> wordSet, Set<String> visited) {
        List<String> nextWords = new LinkedList<>();
        char[] letters = curWord.toCharArray();
        
        for (int i = 0; i < letters.length; i++) {
            char tmp = letters[i];
            
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmp == c) {
                    continue;
                }
                
                letters[i] = c;
                String newWord = new String(letters);
                
                if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
            
            letters[i] = tmp;
        }
        
        return nextWords;
    }
}
