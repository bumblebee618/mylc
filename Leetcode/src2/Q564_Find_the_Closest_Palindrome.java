import java.util.ArrayList;
import java.util.List;

/***
 * 
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.
The closest is defined as the absolute difference minimized between two integers.
 
Example 1:
Input: n = "123"
Output: "121"
Example 2:
Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 
Constraints:
	• 1 <= n.length <= 18
	• n consists of only digits.
	• n does not have leading zeros.
	• n is representing an integer in the range [1, 1018 - 1].

 *
 */

public class Q564_Find_the_Closest_Palindrome {
	// test case: 10 -> 9 (not 11)
	// test case: 99 -> 101 (not 88)
	public String nearestPalindromic(String n) {
		if (n == null || n.length() == 0) {
			return "";
		}
        
		int size = n.length();
        int leftEnd = size % 2 == 0 ? size/2 - 1: size/2;
        long leftPart = Long.parseLong(n.substring(0, leftEnd+1));
        
        // input: n = 12345
        List<Long> candidates = new ArrayList<>();
        candidates.add(getPalindrome(leftPart, size % 2 == 0));    // 12321
        candidates.add(getPalindrome(leftPart+1, size % 2 == 0));  // 12421
        candidates.add(getPalindrome(leftPart-1, size % 2 == 0));  // 12221
        candidates.add((long) Math.pow(10, size-1) - 1);      // 9999
        candidates.add((long) Math.pow(10, size) + 1);        // 100001
        
        long minDiff = Long.MAX_VALUE, result = 0, num = Long.parseLong(n);
        
        for (long candidate : candidates) {
            if (candidate == num) {
            	continue;
            }
            
            if (Math.abs(candidate - num) < minDiff) {
            	minDiff = Math.abs(candidate - num);
                result = candidate;
            } else if (Math.abs(candidate - num) == minDiff) {
                result = Math.min(result, candidate);
            }
        }
        
        return String.valueOf(result);
    }
    
    private long getPalindrome(long left, boolean even){
        long result = left;
        
        if (!even) {
        	left = left / 10;
        }
        
        while (left > 0) {
            result = result * 10 + left % 10;
            left /= 10;
        }
        
        return result;
    }

}
