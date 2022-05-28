import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

 

Example 1:

Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:

Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:

Input: [1,2,3,4,4,5]
Output: False
 

Constraints:

1 <= nums.length <= 10000
 */
public class Q659_Split_Array_into_Consecutive_Subsequences {
	public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3)
        {
            return false;
        }
        
        Map<Integer, Integer> frequency = new HashMap<>();
        
        // the map used to keep the tail of the sub-array
        Map<Integer, Integer> tail = new HashMap<>();
        
        for (int num : nums)
        {
            frequency.put(num, frequency.getOrDefault(num, 0)+1);
        }
        
        for (int num : nums)
        {
            if (frequency.get(num) == 0)
            {
                continue;
            }
            
            frequency.put(num, frequency.get(num)-1);
            
            // 尽量先往前靠
            if (tail.getOrDefault(num-1, 0) > 0)
            {
                tail.put(num-1, tail.get(num-1)-1);
                tail.put(num, tail.getOrDefault(num, 0)+1);
            }
            // 然后再考虑重新建一个新的subarray
            else if (frequency.getOrDefault(num+1, 0) > 0 && frequency.getOrDefault(num+2, 0) > 0)
            {
                frequency.put(num+1, frequency.get(num+1)-1);
                frequency.put(num+2, frequency.get(num+2)-1);
                tail.put(num+2, tail.getOrDefault(num+2, 0)+1);
            }
            else
            {
                return false;
            }
        }
        
        return true;
    }
}
