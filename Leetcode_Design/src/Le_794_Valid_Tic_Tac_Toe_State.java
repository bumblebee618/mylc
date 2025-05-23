/**
 * 
A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
 *
 */
public class Le_794_Valid_Tic_Tac_Toe_State {
	public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0) {
            return true;
        }
        
        int x_count = 0;
        int o_count = 0;
        int size = 3;
        int[] rowStatus = new int[size];
        int[] colStatus = new int[size];
        int diagonal = 0;
        int anti_diagonal = 0;
        boolean x_win = false;
        boolean o_win = false;
        
        for (int row = 0; row < size; row++) {
            if (board[row] == null || board[row].length() != size) {
                return false;
            }
            
            for (int col = 0; col < size; col++) {
                int step = 0;
                
                if (board[row].charAt(col) == 'X') {
                    step = 1;
                    x_count++;
                } else if (board[row].charAt(col) == 'O') {
                    step = -1;
                    o_count++;
                }
                
                rowStatus[row] += step;
                colStatus[col] += step;
                
                if (row == col) {
                    diagonal += step;
                }
                
                if (row + col == size-1) {
                    anti_diagonal += step;
                }
            }
        }
        
        for (int i = 0; i < size; i++) {
            if (rowStatus[i] == size || colStatus[i] == size || diagonal == size || anti_diagonal == size) {
                x_win = true;
            } 
            
            if (rowStatus[i] == -size || colStatus[i] == -size || diagonal == -size || anti_diagonal == -size) {
                o_win = true;
            } 
        }
        
        if (x_count != o_count && x_count != o_count+1) {
            return false;
        } else if (x_win && o_win) {
            return false;
        } else if (x_win && x_count != o_count+1) {
            return false;
        } else if (o_win && x_count != o_count) {
            return false;
        } else {
            return true;
        }
    }
}
