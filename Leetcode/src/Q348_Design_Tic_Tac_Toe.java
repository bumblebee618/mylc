import java.util.*;

/*******
 * 
Design a Tic-tac-toe game that is played between two players on a n row n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?

 * 
 * */

public class Q348_Design_Tic_Tac_Toe {
	private int[] rowStatus;
    private int[] colStatus;
    private int diag = 0;
    private int antidiag = 0;
    private int size = 0;
    private Set<Integer> visited;
    
    /** Initialize your data structure here. */
    public Q348_Design_Tic_Tac_Toe(int n) 
    {
        if (n <= 0)
        {
            return;
        }
        
        size = n;
        rowStatus = new int[n];
        colStatus = new int[n];
        visited = new HashSet<>();
    }
    
    /** Player {player} makes a move at ({row}, {size}).
        @param row The row of the board.
        @param size The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) 
    {
        if (row < 0 || row >= size || col < 0 || col >= size)
        {
            return 0;
        }
        else if (visited.contains(row * size + col))
        {
            return 0;
        }
        
        visited.add(row * size + col);
        int step = (player == 1) ? 1 : -1;
        rowStatus[row] += step;
        colStatus[col] += step;
        
        if (row == col)
        {
            diag += step;
        }
        
        if (row+col == size-1)
        {
            antidiag += step;
        }
        
        if (Math.abs(rowStatus[row]) == size 
        	|| Math.abs(colStatus[col]) == size 
        	|| Math.abs(diag) == size 
        	|| Math.abs(antidiag) == size)
        {
            return player;
        }
        else
        {
            return 0;
        }
    }
}
