import java.util.Arrays;
import java.util.Comparator;
/***
 * 
 * @author jackie
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 

Constraints:

0 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 104
0 <= t <= 231 - 1
 */


public class Q220_Contains_Duplicate_III {	
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) 
    {      
        if (nums == null || nums.length < 2 || t < 0 || k < 1) 
        {
            return false;
        }
        
        int len = nums.length;
        Pair[] pair = new Pair[len];
        
        for (int i = 0; i < len; i++) 
        {
            pair[i] = new Pair(nums[i], i);
        }

        Arrays.sort(pair, (a, b) -> a.val - b.val);

        for (int i = 0; i < len; i++) 
        {
        	// 注意，这里必需是Math.abs() 且(long) 必须放在Math.abs里面防止溢出，否则会错误 !!! 
            for (int j = i + 1; j < len && Math.abs((long)pair[j].val - (long)pair[i].val) <= (long)t; j++)    
            {
            	int indexDiff = Math.abs(pair[i].index - pair[j].index);                                       // 注意test case: [-1,2147483647], 1, 2147483647 !!!, 否则会等于 －2147483647
                
            	if (indexDiff <= k) 
            	{
                    return true;
                }
            }
        }
        
        return false;
    }
    
    class Pair 
    {
        int val;
        int index;
        
        Pair(int v, int i) 
        {
            this.val = v;
            this.index = i;
        }
    }
    
    
    
    
    
    
    
    
    /*********************************** main ***************************************/
    public static void main(String[] args){
    	Q220_Contains_Duplicate_III t = new Q220_Contains_Duplicate_III();
    	int[] nums = {-1,2147483647};
    	System.out.println(t.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }
}
