/**
 * 
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Note:
The n will be in the range [1, 1000].
 *
 */
public class Le_650_2_Keys_Keyboard {
	public int minSteps(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n+1];
        dp[1] = 0;
        
        for (int i = 2; i <= n; i++) {
            int factor = findPrimeFactor(i);
            dp[i] = dp[factor] + i/factor;
        }
        
        return dp[n];
    }
    
    private int findPrimeFactor(int n) {
        for (int i = n/2; i>= 1; i--) {
            if (n % i == 0) {
                return i;
            }
        }
        
        return 1;
    }
}
