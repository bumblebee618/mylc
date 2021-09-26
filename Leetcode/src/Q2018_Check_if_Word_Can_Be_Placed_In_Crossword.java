/***
 * 
 * @author jackie
 *
 * You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.

A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:

It does not occupy a cell containing the character '#'.
The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
Given a string word, return true if word can be placed in board, or false otherwise.

 

Example 1:


Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
Output: true
Explanation: The word "abc" can be placed as shown above (top to bottom).
Example 2:


Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
Output: false
Explanation: It is impossible to place the word because there will always be a space/letter above or below it.
Example 3:


Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
Output: true
Explanation: The word "ca" can be placed as shown above (right to left). 
 

Constraints:

m == board.length
n == board[i].length
1 <= m * n <= 2 * 105
board[i][j] will be ' ', '#', or a lowercase English letter.
1 <= word.length <= max(m, n)
word will contain only lowercase English letters.
 */
public class Q2018_Check_if_Word_Can_Be_Placed_In_Crossword {
	public boolean placeWordInCrossword(char[][] board, String word) {
        String revertedWord = new StringBuilder(word).reverse().toString();
        return checkWord(board, word) || checkWord(board, revertedWord);
    }
    
    private boolean checkWord(char[][] board, String word) {
        int row = board.length, col = board[0].length, size = word.length();
        
        for (int i = 0; i < col; i++) {
            int startRow = 0;
            
            while (startRow + size <= row) {
                while (startRow + size <= row && board[startRow][i] == '#') {
                    startRow++;
                }
                
                if (startRow + size > row) {
                    break;
                }
                
                boolean found = true;
            
                for (int j = 0; j < size; j++, startRow++) {
                    if (board[startRow][i] != ' ' && board[startRow][i] != word.charAt(j)) {
                        found = false;
                        break;
                    }
                }
            
                if (found && (startRow == row || board[startRow][i] == '#')) {
                    return true;
                }
                
                while (startRow + size <= row && board[startRow][i] != '#') {
                    startRow++;
                }
            }
        }
        
        for (int i = 0; i < row; i++) {
            int startCol = 0;
            
            while (startCol + size <= col) {
                while (startCol + size <= col && board[i][startCol] == '#') {
                    startCol++;
                }
                
                if (startCol + size > col) {
                    break;
                }
            
                boolean found = true;
            
                for (int j = 0; j < size; j++, startCol++) {
                    if (board[i][startCol] != ' ' && board[i][startCol] != word.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                
                if (found && (startCol == col || board[i][startCol] == '#')) {
                    return true;
                }
                
                while (startCol + size <= col && board[i][startCol] != '#') {
                    startCol++;
                }
            }
        }
        
        return false;
    }
}
