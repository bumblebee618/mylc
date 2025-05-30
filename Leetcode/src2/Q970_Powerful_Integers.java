import java.util.*;

/***
 * 
 * @author jackie
 *
 * Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal to bound.

An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.

You may return the answer in any order. In your answer, each value should occur at most once.

 

Example 1:

Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation:
2 = 20 + 30
3 = 21 + 30
4 = 20 + 31
5 = 21 + 31
7 = 22 + 31
9 = 23 + 30
10 = 20 + 32
Example 2:

Input: x = 3, y = 5, bound = 15
Output: [2,4,6,8,10,14]
 

Constraints:

1 <= x, y <= 100
0 <= bound <= 106
*
*/

public class Q970_Powerful_Integers 
{
	// Time O(logx(bound) * logy(bound))
	public List<Integer> powerfulIntegers(int x, int y, int bound) 
    {
        List<Integer> result = new LinkedList<>();
        
        if (x > bound && y > bound)
        {
            return result;   
        }
        
        int v1 = 1;
        Set<Integer> visited = new HashSet<>();
        
        while (v1 <= bound)
        {
            int v2 = 1;
            
            while (v1 + v2 <= bound)
            {
                if (!visited.contains(v1+v2))
                {
                    visited.add(v1+v2);
                    result.add(v1+v2);
                }
                
                if (y == 1)
                {
                    break;
                }
                
                v2 *= y;
            }
            
            if (x == 1)
            {
                break;
            }
            
            v1 *= x;
        }
        
        return result;
    }
}
