/**
 * 
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False
 *
 */
public class Q633_Sum_of_Square_Numbers {
	// test case: 2147483646
	public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        
        for (long i = 0; i * i <= c; i++) { // 注意这里是long type !!!
            double value = Math.sqrt(c - i*i);
            
            if (value == (int) value) {
                return true;
            }
        }
        
        return false;
    }
}
