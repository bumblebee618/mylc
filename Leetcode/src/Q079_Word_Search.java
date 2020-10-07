/********
 * 
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
Show Company Tags
Hide Tags

 * 
 * */


public class Q079_Word_Search {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0)
        {
            return false;
        }
        
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (dfs(board, visited, i, j, word, 0))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int index)
    {
        if (board[x][y] != word.charAt(index))
        {
            return false;
        }
        else if (index == word.length()-1)  // 必须在这里判断，防止test case: [a], a  ！！！
        {
            return true;
        }
        
        visited[x][y] = true;
        boolean result = false;
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && !visited[newX][newY])
            {
                if (dfs(board, visited, newX, newY, word, index+1))
                {
                    result = true;
                    break;
                }
            }
        }
        
        visited[x][y] = false;
        return result;
    }

    
    
    
    /****************************************************************************************************************/
    
 // 此方法不行，因为test case["a", a]无法通过，需要用if(index == word.length() - 1 && board[x][y] == word.charAt(index))
    // 判断，不能到index == word.length() 判断 !!!
    public boolean backtrack2(char[][] board, boolean[][] visited, int x, int y, String word, int index){
        if(index == word.length()){
            return true;
        } 
        if(visited[x][y] == true){
            return false;
        } 
        if(board[x][y] != word.charAt(index)){
            return false;
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length){
                if(backtrack2(board, visited, newX, newY, word, index + 1) == true){
                    return true;
                }
            }
        }
        
        visited[x][y] = false;
        return false;
    }
    
    
    public static void main(String[] args){
    	Q079_Word_Search t = new Q079_Word_Search();
//    	char[][] board = {
//    						{'a','a','a','a'},
//    						{'a','a','a','a'},
//    						{'a','a','a','a'},
//    						{'a','a','a','a'},
//    						{'a','a','a','b'}    			
//    					 };
//    	System.out.println(t.exist(board, "aaaaaaaaaaaaaaaaaaaa"));
    	
    	char[][] board2 = {
    						{'A','B','C','E'},
    						{'S','F','C','S'},
    						{'A','D','E','E'}
    					  };
    	System.out.println(t.exist(board2, "ABCCED"));
    }
}
