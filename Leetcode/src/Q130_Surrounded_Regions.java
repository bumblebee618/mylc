import java.util.LinkedList;
import java.util.Queue;
/******
 * 
Given a 2D board containing 'X' and 'O' (the letter O), 
capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 * 
 * */

public class Q130_Surrounded_Regions {
	/*******************************************************************
	 * 矩阵用iterator进行bfs的方法： q里存 i * col + col
	 *  (1). 结点引入3态。
	 *  (2). 从边界入手，可以遍历到的结点改为第三态D
	 *  (3). 将第三态D的结点改为O, 将原来为O的结点改为X
	 *  
	 *******************************************************************/
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
        {
            return;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        for (int i = 0; i < row; i++)
        {
            if (board[i][0] == 'O')
            {
                dfs(board, i, 0);
            }
            
            if (board[i][col-1] == 'O')
            {
                dfs(board, i, col-1);
            }
        }
        
        for (int i = 0; i < col; i++)
        {
            if (board[0][i] == 'O')
            {
                dfs(board, 0, i);
            }
            
            if (board[row-1][i] == 'O')
            {
                dfs(board, row-1, i);
            }
        }
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (board[i][j] == 'O')
                {
                    board[i][j] = 'X'; 
                }
                else if (board[i][j] == 'D')
                {
                    board[i][j] = 'O'; 
                }
            }
        }
    }
    
    private void dfs(char[][] board, int x, int y)
    {
        board[x][y] = 'D';
        
        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] == 'O')
            {
                dfs(board, newX, newY);
            }
        }
    }

	
	
	public void solve2(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        Queue<Integer> queue = new LinkedList();
        int row = board.length, col = board[0].length;
        
        for(int i = 0; i < row; i++) {
            enqueue(board, i, 0, col, queue);
            enqueue(board, i, col-1, col, queue);
        }
        
        for(int i = 0; i < col; i++) {
            enqueue(board, 0, i, col, queue);
            enqueue(board, row-1, i, col, queue);
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        while(!queue.isEmpty()) {
            int index = queue.poll();
            int x = index / col;
            int y = index % col;
            board[x][y] = 'D';
            
            for(int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                
                if(newX >= 0 && newX < row && newY >= 0 && newY < col && board[newX][newY] == 'O') {
                    enqueue(board, newX, newY, col, queue);
                }
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'D'){
                	board[i][j] = 'O';
                } else if (board[i][j] == 'O'){
                	board[i][j] = 'X';
                }
            }
        }
    }
    
    public void enqueue(char[][] board, int x, int y, int col, Queue<Integer> queue) {
        if(board[x][y] == 'O') {
            queue.offer(x * col + y);
        }
    }
	
    
    
    
    
    
    
    
 
	// by ninechapter
//	private Queue<Integer> queue = new LinkedList<Integer>();
//	private char[][]board;
//	private int row, col;
//	
//	public void solve(char[][] board) {
//        if (board.length == 0 || board[0].length == 0){
//        	return;
//        }
//        
//        queue = new LinkedList<Integer>();
//        this.board = board;
//        row = board.length;
//        col = board[0].length;
//
//        for (int i = 0; i < row; i++) { // **important**
//            enqueue(i, 0);
//            enqueue(i, col - 1);
//        }
//
//        for (int j = 1; j < col - 1; j++) { // **important**  
//            enqueue(0, j);
//            enqueue(row - 1, j);
//        }
//
//        while (!queue.isEmpty()) {
//            int cur = queue.poll();
//            int row = cur / col,
//                col = cur % col;
//
//            if (board[row][col] == 'O') {
//                board[row][col] = 'D';
//            }
//
//            enqueue(row - 1, col);
//            enqueue(row + 1, col);
//            enqueue(row, col - 1);
//            enqueue(row, col + 1);
//        }
//
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (board[i][j] == 'D'){
//                	board[i][j] = 'O';
//                } else if (board[i][j] == 'O'){
//                	board[i][j] = 'X';
//                }
//            }
//        }
//        
//        queue = null;
//        board = null;
//        row = 0;
//        col = 0;
//    }
//
//    public void enqueue(int row, int col) {
//        if (row >= 0 && row < row && col >= 0 && col < col && board[row][col] == 'O'){  
//            queue.offer(row * col + col);
//        }
//    }
	

    
/********************** main function *****************************/    
    
    public static void main(String[] args){
    	Q130_Surrounded_Regions t = new Q130_Surrounded_Regions();
//    	char[][] board = {
//    			{'X', 'X', 'X', 'X'},
//    			{'X', 'O', 'O', 'X'},
//    			{'X', 'X', 'O', 'X'},
//    			{'X', 'O', 'X', 'X'}
//    	};
    	
    	char[][] board = {
    			{'O','O', 'O'},
    			{'O','O', 'O'},
    			{'O','O', 'O'}
    	};
    	
    	t.solve(board);
    	for(int i = 0; i < board.length; ++i){
    		for(int j = 0; j < board[i].length; ++j){
    			System.out.print(board[i][j] + ", ");
    		}
    		System.out.println();
    	}
    }
}
