/********
 * 
Write a program to solve a Sudoku puzzle by filling the empty cells.
Empty cells are indicated by the character '.'.
You may assume that there will be only one unique solution.

A sudoku puzzle...
 * 
 * */

public class Q037_Sudoku_Solver {
	public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        backtrack(board);
    }
    
    private boolean backtrack(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {  //trial. Try 1 through 9 for each cell
                        if (isValid(i, j, board, c)) {
                            board[i][j] = c;
                            
                            if (backtrack(board)) {
                                return true;
                            }
                            
                            board[i][j] = '.';
                        }
                    }
                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isValid(int row, int col, char[][] board, char target) {
        for (int i = 0; i < 9; i++) {    
            if (board[row][i] == target) {
                return false;
            }
            
            if (board[i][col] == target) {
                return false;
            }
        }
        
        // Check 3 row 3 block
        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                if (board[i][j] == target) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
