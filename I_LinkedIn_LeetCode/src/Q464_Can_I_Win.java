import java.util.*;
/**
 * 
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
 *
 */
public class Q464_Can_I_Win {
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal || desiredTotal <= 0) {
            return true;
        }
        
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        
        if (sum < desiredTotal) {
            return false;
        }
        
        Map<Integer, Boolean> map = new HashMap<>();
        boolean[] used = new boolean[maxChoosableInteger + 1];
        return helper(map, used, desiredTotal);        
    }
    
    private boolean helper(Map<Integer, Boolean> map, boolean[] used, int desiredTotal) {
        if (desiredTotal <= 0) {
            return false;
        }
        
        int key = formate(used);
        
        if (!map.containsKey(key)) {
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    
                    if (!helper(map, used, desiredTotal - i)) {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    
                    used[i] = false;
                }
            }
            
            map.put(key, false);
        }
        
        return map.get(key);
    }
    
    private int formate(boolean[] used) {
        int result = 0;
        
        for (boolean flag : used) {
            result <<= 1;
            result |= (flag ? 1 : 0);
        }
        
        return result;
    }
}
