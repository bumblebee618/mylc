import java.util.BitSet;

/***
 * 
 * @author jackie
 * 
 * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.

 

Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Example 2:

Input: n = 1
Output: 2
Example 3:

Input: n = 2
Output: 3
 

Constraints:

1 <= n <= 109
 */
public class Q600_Non_negative_Integers_without_Consecutive_Ones {
	public int findIntegers(int n) {
		int[] bit = new int[32];
        int firstOne = -1;
        
        for (int i = 0; i < 32; i++)
        {
        	bit[i] = (n & (1 << i)) > 0 ? 1 : 0;
        	
        	if (bit[i] == 1)
        	{
        		firstOne = i;
        	}
        }
        
        int[][] dp = new int[firstOne+1][2]; 
        dp[0][0] = dp[0][1] = 1;
        
        for (int i = 1; i < dp.length; i++)
        {
        	dp[i][0] = dp[i-1][1] + dp[i-1][0];
        	dp[i][1] = dp[i-1][0];
        }
        
        int result = dp[dp.length-1][0] + dp[dp.length-1][1];
        int pointer = firstOne;
        
        while (pointer-2 >= 0)
        {
        	// 11...
        	if (pointer-1 >= 0 && bit[pointer-1] == 1)
        	{
        		break;
        	}
        	
        	// 101...
        	if (pointer-2 >= 0 && bit[pointer-2] == 1)
        	{
        		pointer -= 2;
        		continue;
        	}
        	
        	// 100...
        	int start = pointer-2, end = pointer-2;
        	
        	result -= end+1 >= 0 ? dp[end+1][0] : 0;
        		
        	while (start >= 0 && bit[start] == 0)
        	{
        		start--;
        	}
        	
        	result += start+1 >= 0 ? dp[start+1][0] : 0;
        	pointer = start;
        }
        
        return result;
    }
	
	
	
	
	
	
	/********************************* main *********************************/
	
	public static void main(String[] args)
	{
		Q600_Non_negative_Integers_without_Consecutive_Ones test = new Q600_Non_negative_Integers_without_Consecutive_Ones();
		System.out.println(test.findIntegers(1));
		System.out.println(test.findIntegers(2));
		System.out.println(test.findIntegers(3));
		
		System.out.println(test.findIntegers(4));
		
		System.out.println(test.findIntegers(5));
		System.out.println(test.findIntegers(6));
		System.out.println(test.findIntegers(8));
	}
}
