import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated minValue which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.

The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.

 

Example 1:

Input: values = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
Example 2:

Input: values = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with minValue = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with minValue = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
Example 3:

Input: values = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
Example 4:

Input: values = [1,2,3,-1,-2,-3,7]
Output: "Alice"
Example 5:

Input: values = [-1,-2,-3]
Output: "Tie"
 

Constraints:

1 <= values.length <= 50000
-1000 <= values[i] <= 1000
 */
public class Q1406_Stone_Game_III 
{
	private int[] stoneValue;
    
    public String stoneGameIII(int[] stoneValue) 
    {
        if (stoneValue == null || stoneValue.length == 0)
        {
            return "Tie";
        }
        
        int sum = Arrays.stream(stoneValue).sum();
        this.stoneValue = stoneValue;
        Integer[] memo = new Integer[stoneValue.length];
        int score = search(0, memo);
        
        if (score * 2 > sum)
        {
            return "Alice";
        }
        else if (score * 2 < sum)
        {
            return "Bob";
        }
        else
        {
            return "Tie";
        }
    }
    
    private int search(int startIndex, Integer[] memo)
    {
        if (startIndex >= stoneValue.length)
        {
            return 0;
        }
        else if (memo[startIndex] != null)
        {
            return memo[startIndex];
        }
        
        int take1 = stoneValue[startIndex] + search(startIndex+1, memo);
        
        int take2 = (startIndex+1 < stoneValue.length) ? 
            stoneValue[startIndex] + stoneValue[startIndex+1] + search(startIndex+2, memo) 
            : 0;
        
        int take3 = (startIndex+2 < stoneValue.length) ? 
            stoneValue[startIndex] + stoneValue[startIndex+1] + stoneValue[startIndex+2] + search(startIndex+3, memo) 
            : 0;
        
        memo[startIndex] = Math.max(take1, Math.max(take2, take3)); 
        return memo[startIndex];
    }
	
	
	public String stoneGameIII2(int[] stoneValue) 
    {
        if (stoneValue == null || stoneValue.length == 0)
        {
            return "Tie";
        }
        
        int size = stoneValue.length;
        int[] score = new int[size+1];
        
        for (int i = size-1; i >= 0; i--)
        {
            int take = 0;
            score[i] = Integer.MIN_VALUE;
            
            for (int k = 0; k < 3 && i+k < size; k++)
            {
                take += stoneValue[i+k];
                score[i] = Math.max(score[i], take - score[i+k+1]);
            }
        }
        
        if (score[0] > 0)
        {
            return "Alice";
        }
        else if (score[0] < 0)
        {
            return "Bob";
        }
        else
        {
            return "Tie";
        }
    }
	
	

	
	public static void main(String[] args)
	{
		Q1406_Stone_Game_III test = new Q1406_Stone_Game_III();
		
		int[] stoneValue1 = {1,2,3,7};
		System.out.println(test.stoneGameIII(stoneValue1));
	}
}
