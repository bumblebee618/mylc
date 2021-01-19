import java.util.*;

/***
 * 
 * @author jackie
 *
 * Tic-tac-toe is played by two players A and B on a 3 row 3 grid.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player A always places "X" characters, while the second player B always places "O" characters.
"X" and "O" characters are always placed into empty squares, never on filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.

Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".

You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.

 

Example 1:

Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
Output: "A"
Explanation: "A" wins, he always plays first.
"X  "    "X  "    "X  "    "X  "    "X  "
"   " -> "   " -> " X " -> " X " -> " X "
"   "    "O  "    "O  "    "OO "    "OOX"
Example 2:

Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
Output: "B"
Explanation: "B" wins.
"X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
"   " -> " O " -> " O " -> " O " -> "XO " -> "XO " 
"   "    "   "    "   "    "   "    "   "    "O  "
Example 3:

Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
Output: "Draw"
Explanation: The game ends in a draw since there are no moves to make.
"XXO"
"OOX"
"XOX"
Example 4:

Input: moves = [[0,0],[1,1]]
Output: "Pending"
Explanation: The game has not finished yet.
"X  "
" O "
"   "
 

Constraints:

1 <= moves.length <= 9
moves[i].length == 2
0 <= moves[i][j] <= 2
There are no repeated elements on moves.
moves follow the rules of tic tac toe.
 */
public class Q1275_Find_Winner_on_a_Tic_Tac_Toe_Game {
	private Set<Integer> visited = new HashSet<>();
    private int[] rowStatus = new int[3];
    private int[] colStatus = new int[3];
    private int diag = 0;
    private int antidiag = 0;
    
    public String tictactoe(int[][] moves) {
        if (moves == null || moves.length == 0)
        {
            return "";
        }

        int result = 0;
            
        for (int i = 0; i < moves.length; i++)
        {
            result = move(moves[i][0], moves[i][1], i % 2 == 0 ? 1 : -1);
            
            if (result != 0)
            {
                break;
            }
        }
        
        switch (result)
        {
            case 1: return "A";
            case -1: return "B"; 
            default: return moves.length == 9 ? "Draw" : "Pending";
        }
    }
    
    private int move(int row, int col, int player)
    {
        if (visited.contains(row*3+col))
        {
            return 0;
        }
        
        visited.add(row*3+col);
        rowStatus[row] += player;
        colStatus[col] += player;
        
        if (row == col)
        {
            diag += player;
        }
        
        if (row + col == 2)
        {
            antidiag += player;
        }
        
        if (Math.abs(rowStatus[row]) == 3 || Math.abs(colStatus[col]) == 3 || Math.abs(diag) == 3 || Math.abs(antidiag) == 3)
        {
            return player;
        }
        else
        {
            return 0;
        }
    }
}
