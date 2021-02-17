/***
 * 
 * @author jackie
 * 
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */

public class Q137_Single_Number_II {
	// Solution 1:
	public int singleNumber(int[] nums) 
	{
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int result = 0;
        
        for (int i = 0; i < 32; i++)
        {
            int count = 0;
            
            for (int num : nums)
            {
                count += (num >> i) & 1;
            }    
            
            count %= 3;
            result |= (count << i);
        }
        
        return result;
    }
	
	
	
	
	
	

	// Solution 2: using bit manipulation, nice
	public int singleNumber2(int[] nums) {
        int one = 0; int two = 0; int three = 0;
        for(int i = 0; i < nums.length; i++){
            // if now one is nums[i], by ANDing and ORing, two now equals to nums[i]
            two |= one & nums[i];
            // if now one is zero, it stores the nums[i] in it
            // if now one is nums[i], it clears the content since a^a = 0
            // if now one is other number, it just accumulates
            one ^= nums[i]; 
            // if now one and two has the same minValue, it means the 
            // number shows 3 times, thus
            three = one & two;
            // if three is the number, clear this number from the one and two
            // a &= ~b => a - b
            // 0100 | 0011 = 3+4 = 7  (0111)
            // 7 &= ~3 => 0111 & 1100 = 0100 = 4 (7 - 3)
            one &= ~three;
            two &= ~three;
        }
        return one;
    }
}
