/***
 * 
 * @author jackie
 * 
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).

You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.

Return the minimum number of flips to make s monotone increasing.

 

Example 1:

Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: s = "00011000"
Output: 2
Explanation: We flip to get 00000000.
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
 */
public class Q926_Flip_String_to_Monotone_Increasing {
	public int minFlipsMonoIncr(String s) {
        int size = s.length();
        int[][] dp = new int[size][2];
        
        // dp[i][0]: flip time when current digit is 0
        // dp[i][1]: flip time when current digit is 1
        dp[size-1][0] = s.charAt(size-1) == '0' ? 0 : 1;
        dp[size-1][1] = s.charAt(size-1) == '1' ? 0 : 1;
        
        for (int i = size-2; i >= 0; i--) {
            dp[i][0] = dp[i+1][0] + (s.charAt(i) == '0' ? 0 : 1);
            dp[i][1] = dp[i+1][1] + (s.charAt(i) == '1' ? 0 : 1);  
        }
        
        int result = Math.min(dp[0][0], dp[0][1]), flipTime = 0;
        
        for (int i = 0; i < size-1; i++) {
            flipTime += s.charAt(i) == '0' ? 0 : 1;
            int totalFlipTime = Math.min(dp[i+1][0], dp[i+1][1]) + flipTime;
            result = Math.min(result, totalFlipTime);
        }
        
        return result;
    }
	
	 
	
	
	
	
	
	/************************************* main *************************************/
	
	public static void main(String[] args) {
		Q926_Flip_String_to_Monotone_Increasing test = new Q926_Flip_String_to_Monotone_Increasing();
		String s = "11011";
		System.out.println(test.minFlipsMonoIncr(s));
	}
}
