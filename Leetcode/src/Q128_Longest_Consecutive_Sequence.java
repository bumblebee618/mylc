import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*******
 * 
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

	Your algorithm should run in O(n) complexity.
	
 * 
 * */

public class Q128_Longest_Consecutive_Sequence {
	// solution 1: time is O(n), space is O(n)
	public int longestConsecutive(int[] nums) 
	{
        if (nums == null || nums.length == 0) 
        {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        int maxLen = 0;
        
        for (int num : nums) 
        {
            set.add(num);
        }
        
        for (int i = 0; i < nums.length; i++) 
        {
            int left = nums[i] - 1;
            int right = nums[i] + 1;
            
            while (set.contains(left)) 
            {
                set.remove(left);
                left--;
            }
            
            while (set.contains(right)) 
            {
                set.remove(right);
                right++;
            }
            
            maxLen = Math.max(maxLen, right - left - 1);
        }
        
        return maxLen;
    }
	
	
	
	
	
	// solution 2: 
	public int longestConsecutive2(int[] nums) {
		if (nums == null || nums.length == 0) {
            return 0;
        }
		
        HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        int maxLong = 1;
        int temp = 0, currentMaxLong = 0;
        
        for(int i : nums) {
            myMap.put(i, 0);
        }
        
        for(int i : nums){
            if(myMap.get(i) == 1) {
            	continue;
            }
            
            temp = i;
            currentMaxLong = 1;
            
            while(myMap.containsKey(temp + 1)){
                currentMaxLong++;
                temp++;
                myMap.put(temp, 1);
            }
            
            temp = i;
            
            while(myMap.containsKey(temp - 1)){
                currentMaxLong++;
                temp--;
                myMap.put(temp, 1);
            }
            
            maxLong = Math.max(currentMaxLong, maxLong);
        }
        
        return maxLong;
    }
	
	
	// Solution2: use recursion and may cause stack over flow
	public int longest2Consecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int result = 1;
        
        for (int num : nums) {
            map.put(num, 0);
        }
        
        for (int num : nums) {
            result = Math.max(result, helper(map, num));
        }
        
        return result;
    }
    
    private int helper(Map<Integer, Integer> map, int num) {
        if (!map.containsKey(num)) {
            return 0;
        }
        
        int len = map.get(num);
            
        if (map.get(num) == 0) {
            int count = helper(map, num-1);
            map.put(num, count+1);
            return count+1;
        } else {
            return len;
        }
    }
    
    
    
    public static void main(String[] args) {
    	Q128_Longest_Consecutive_Sequence test = new Q128_Longest_Consecutive_Sequence();
    	int[] array = {100, 4, 200, 1, 3, 2};
    	System.out.println(test.longest2Consecutive(array));
    }
}
