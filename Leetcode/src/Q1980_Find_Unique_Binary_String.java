import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

 

Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 

Constraints:

n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
 */
public class Q1980_Find_Unique_Binary_String {
	public String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (String num : nums) {
        	int sum = 0;
        	
            for (int i = 0; i < num.length(); i++) {
            	if (num.charAt(i) == '1') {
            		sum += (1 << (num.length() - 1 - i));
            	}
            }
            
            set.add(sum);
        }
        
        int n = nums.length;
        int end = (1 << n) - 1; 
        
        int target = -1;
        
        for (int i = 0; i <= end; i++) {
        	if (!set.contains(i)) {
        		target = i;
        		break;
        	}
        }

        StringBuilder builder = new StringBuilder();
        
        while (target > 0) {
        	builder.insert(0, target % 2);
        	target >>= 1;
        }
        
        while (builder.length() < n) {
        	builder.insert(0, '0');
        }
        
        return builder.toString();
    }
}
