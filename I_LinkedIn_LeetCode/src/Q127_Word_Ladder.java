import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
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
        if(beginWord == null || endWord == null){
            return 0;
        } else if(beginWord.equals(endWord)){
            return 0;
        }
        
        int step = 1;
        Queue<String> queue = new LinkedList<String>();
        HashSet<String> visited =  new HashSet<String>();
        queue.offer(beginWord);
        visited.add(beginWord);
        wordList.add(endWord);
        
        while(!queue.isEmpty()){
            step++;
            int size = queue.size();
            
            for(int i = 0; i < size; ++i){
                String str = queue.poll();
                
                for(String s : Expends(str, wordList)){
                	System.out.print(s + ", ");
                    if(visited.contains(s)){
                        continue;
                    } else if(s.equals(endWord)){
                        return step;
                    }
                    
                    visited.add(s);
                    queue.offer(s);
                }
            }
            
            System.out.println();
        }
        
        return 0;
    }
    
    
    public List<String> Expends(String str, List<String> wordList){
        List<String> list = new LinkedList<String>();
        char[] array = str.toCharArray();
        
        for(int i = 0; i < array.length; ++i){
            char temp = array[i];
            
            for(char c = 'a'; c <= 'z'; ++c){
                if(c == temp){
                    continue;
                }
                
                array[i] = c;
                String newWord = new String(array);
                
                if(wordList.contains(newWord)){
                    list.add(newWord);
                }
            }
            
            array[i] = temp;
        }
        
        return list;
    }
    
    
    
    
    
    public static void main(String[] args) {
    	Q127_Word_Ladder test = new Q127_Word_Ladder();
    	String beginWord = "hit";
    	String endWord = "cog";
    	List<String> wordList = new LinkedList<>();
    	wordList.add("hot");
    	wordList.add("dot");
    	wordList.add("dog");
    	wordList.add("lot");
    	wordList.add("log");
    	test.ladderLength(beginWord, endWord, wordList);
    }
}
