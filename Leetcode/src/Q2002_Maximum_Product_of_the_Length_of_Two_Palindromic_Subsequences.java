import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.

 

Example 1:

example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.
Example 2:

Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.
Example 3:

Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.
 

Constraints:

2 <= s.length <= 12
s consists of lowercase English letters only.
 */
public class Q2002_Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences {
private Map<Integer, Integer> memo = new HashMap<>();
	
	public int maxProduct(String s) {
		int result = 0;
		backtrack(s, 0, s.length()-1, 0, 0);
       
		for (Map.Entry<Integer, Integer> entry1 : memo.entrySet()) {
			for (Map.Entry<Integer, Integer> entry2 : memo.entrySet()) {
    		   if ((entry1.getKey() & entry2.getKey()) == 0) {
    			   result = Math.max(result, entry1.getValue() * entry2.getValue());
    		   }
			}
		}
		
		return result;
    }
	
	private void backtrack(String s, int start, int end, int status, int count) {
		for (int i = start; i <= end; i++) {
			for (int j = end; j >= start; j--) {
				if (i < j && s.charAt(i) == s.charAt(j)) {
					int nextStatus = status | (1 << i) | (1 << j);
					memo.put(nextStatus, count+2);
					backtrack(s, i+1, j-1, nextStatus, count+2);
				} else {
					int nextStatus = status | (1 << j);
					memo.put(nextStatus, count+1);
				}
			}
		}
	}
}	
