import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.

Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.
Example 2:


Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.
Example 3:

Input: m = 5, n = 5
Output: 580986
 

Constraints:

1 <= m <= 5
1 <= n <= 1000
 */
public class Q1931_Painting_a_Grid_With_Three_Different_Colors 
{
	public int colorTheGrid(int m, int n) 
    {
        if (m <= 0 || n <= 0)
        {
            return 0;
        }
        
        int mod = 1_000_000_007;
        Map<Integer, Long> totalStates = new HashMap<>();
        search(totalStates, m, -1, 0);
        Map<Integer, Long> curStates = new HashMap<>(totalStates);
        
        for (int i = 1; i < n; i++) 
        {
            Map<Integer, Long> nextStates = new HashMap<>();
            
            for (int state1 : totalStates.keySet()) 
            {
                for (int state2 : curStates.keySet()) 
                {
                    if ((state1 & state2) == 0) 
                    {
                    	nextStates.put(state1, (nextStates.getOrDefault(state1, 0L) + curStates.get(state2)) % mod);
                    }
                }
            }
            
            curStates = nextStates;
        }
        
        long result = 0L;
        
        for (long count : curStates.values()) 
        {
        	result = (result + count) % mod;	
        }
        
        return (int) result;
    }
    
    private void search(Map<Integer, Long> totalStates, int m, int prevColor, int state) 
    {
        if (m == 0) 
        {
            totalStates.put(state, totalStates.getOrDefault(state, 0L) + 1);
            return;
        }
        
        for (int color = 0; color < 3; color++) 
        {
            if (color == prevColor) 
            {
            	continue;
            }
            
            search(totalStates, m-1, color, (state << 3) | (1 << color));
        }
    }
}
