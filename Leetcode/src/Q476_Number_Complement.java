/***
 * 
 * @author jackie
 * 
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

 

Example 1:

Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 

Example 2:

Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 

Note:

The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class Q476_Number_Complement {
	public int findComplement(int num) {
        if (num < 0)
        {
            return 0;
        }
        
        int result = 0;
        boolean found = false;
        
        for (int i = 31; i >= 0; i--)
        {
            int digit = (num >> i) & 1;
            
            if (digit == 1)
            {
                found = true;
            }
            else if (found)
            {
                result |= 1 << i;
            }
        }
        
        return result;
    }
}
