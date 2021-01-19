/***
 * 
 * @author jackie
 * 
 * Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:

Choosing any row with 0 < row < N and N % row == 0.
Replacing the number N on the chalkboard with N - row.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.

 

Example 1:

Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
Example 2:

Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 */
public class Q1025_Divisor_Game {
	public boolean divisorGame(int N) {
        if (N <= 0)
        {
            return false;
        }
        
        boolean[] dp = new boolean[N+1];
        dp[0] = dp[1] = false;
        
        for (int i = 2; i <= N; i++)
        {
            for (int j = 1; j < i; j++)
            {
                if (i % j == 0)
                {
                    if (dp[i-j] == false)
                    {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        
        return dp[N];
    }
}
