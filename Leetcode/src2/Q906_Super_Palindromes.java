/***
 * 
 * @author jackie
 * 
 * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.

Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].

 

Example 1:

Input: left = "4", right = "1000"
Output: 4
Explanation: 4, 9, 121, and 484 are superpalindromes.
Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
Example 2:

Input: left = "1", right = "2"
Output: 1
 

Constraints:

1 <= left.length, right.length <= 18
left and right consist of only digits.
left and right cannot have leading zeros.
left and right represent integers in the range [1, 1018].
left is less than or equal to right.
 */

public class Q906_Super_Palindromes 
{
	private int result = 0;
    
    public int superpalindromesInRange(String L, String R) 
    {
        if (L == null || R == null)
        {
            return 0;
        }
        
        long l = Long.parseLong(L);
        long r = Long.parseLong(R);
        
        // Generate even digits palindromes
        backtrack("", l, r);
        
        // Generate odd digits palindromes
        for (int i = 0; i < 10; i++) 
        {
            backtrack("" + i, l, r);
        }
        
        return result;
    }
    
    private void backtrack(String s, long l, long r) 
    {
        //If s.length() > 9, super-palindrome's length must larger than 18
        if (s.length() > 9) 
        {
            return;
        }
        
        //Verify if s is within the required boundary.
        if (s.length() > 0 && s.charAt(0) != 0) 
        {
            long num = Long.parseLong(s);
            num *= num;
            
            //Prune the unqualified palindromes branch
            if (num > r) 
            { 
                return; 
            }
            
            if (num >= l && isPalindrome("" + num)) 
            {
                result++;
            }
        }
        
        // Generate the next palindromes
        for (int i = 0; i < 10; i++) 
        {
            String next = "" + i + s + i;
            backtrack(next, l, r);
        }
    }
    
    private boolean isPalindrome(String s) 
    {
        int head = 0;
        int tail = s.length() - 1;
        
        while (head < tail) 
        {
            if (s.charAt(head++) != s.charAt(tail--)) 
            {
                return false;
            }
        }
        
        return true;
    }
}
