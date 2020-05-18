import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
 

Note:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
 */
public class Q846_Hand_of_Straights {
	public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0 || W <= 0)
        {
            return false;
        }
        else if (hand.length % W != 0)
        {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : hand)
        {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        Arrays.sort(hand);
         
        for (int i = 0; i < hand.length; i++)
        {
            if (map.get(hand[i]) == 0)
            {
                continue;
            }
            
            for (int j = 0; j < W; j++)
            {
                if (!map.containsKey(hand[i]+j) || map.get(hand[i]+j) == 0)
                {
                    return false;
                }
                
                map.put(hand[i]+j, map.get(hand[i]+j)-1);
            }
        }
        
        return true;
    }
}
