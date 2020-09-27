import java.util.*;
/*******
 * 
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

 * 
 * */


public class Q212_Word_Search_II {
	private TrieNode root = new TrieNode();
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new LinkedList<>();
        
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) 
        {
            return result;
        }
        
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        
        for (String word : words) 
        {
            addWord(word);
        }
        
        for (int i = 0; i < row; i++) 
        {
            for (int j = 0; j < col; j++) 
            {
                dfs(board, visited, i, j, result, "", root);
            }
        }
        
        return result;
    }
    
    private void dfs(char[][] board, boolean[][] visited, int x, int y, List<String> result, String solution, TrieNode node) 
    {
        int pos = board[x][y] - 'a';
        
        if (node.children[pos] == null) 
        {
            return;
        }
        
        node = node.children[pos];
        solution += board[x][y];
        
        if (node.isWord) 
        {
            result.add(solution);
            node.isWord = false;
        }
        
        visited[x][y] = true;
        
        for (int i = 0; i < dx.length; i++) 
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !visited[newX][newY]) 
            {
                dfs(board, visited, newX, newY, result, solution, node);
            }
        }
        
        visited[x][y] = false;
    }
    
    private void addWord(String word) 
    {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) 
        {
            int pos = word.charAt(i) - 'a';
            
            if (node.children[pos] == null) 
            {
                node.children[pos] = new TrieNode();
            }
            
            node = node.children[pos];
        }
        
        node.isWord = true;
    }
    
    class TrieNode 
    {
        public TrieNode children[];
        public boolean isWord;
        
        public TrieNode() 
        {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    /****************************** main function ****************************************/
    
    public static void main(String[] args){
    	Q212_Word_Search_II t = new Q212_Word_Search_II();
    	char[][] board = {
    			{'a'},
    			{'a'}
    	};
    	String[] words = {"a"};
    	List<String> res = t.findWords(board, words);
    	
    	for(int i = 0; i < res.size(); ++i){
    		System.out.println(res.get(i));
    	}
    }
}
