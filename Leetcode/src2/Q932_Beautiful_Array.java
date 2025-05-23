import java.util.*;

/***
 * 
 * @author jackie
 * 
 * An array nums of length n is beautiful if:

nums is a permutation of the integers in the range [1, n].
For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.

 

Example 1:

Input: n = 4
Output: [2,1,4,3]
Example 2:

Input: n = 5
Output: [3,1,2,5,4]
 

Constraints:

1 <= n <= 1000
 */
public class Q932_Beautiful_Array {
	public int[] beautifulArray(int n) 
    {
        int[] result = new int[n];
        List<Integer> list = new ArrayList<>();
        list.add(1);
    
        while (list.size() < n) 
        {
            List<Integer> next = new ArrayList<>();
            
            for (int num: list) 
            {
                if (2 * num - 1 <= n) 
                {
                    next.add(2 * num - 1);    
                }                
            }
            
            for (int num: list) 
            {
                if (2 * num <= n) 
                {
                    next.add(2 * num);    
                }                
            }
            
            list = next;
        }

        for (int i = 0; i < n; ++i) 
        {
            result[i] = list.get(i);
        }
        
        return result;
    }
}
