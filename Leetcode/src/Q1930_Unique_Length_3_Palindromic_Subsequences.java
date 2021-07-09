import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")
 

Constraints:

3 <= s.length <= 105
s consists of only lowercase English letters.
 */
public class Q1930_Unique_Length_3_Palindromic_Subsequences 
{
	public int countPalindromicSubsequence(String s) 
    {
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < s.length(); i++)
		{
			set.add(s.charAt(i));
		}
		
		int result = 0;
		
		for (char c : set)
		{
			result += findCount(s, c);
		}
		
		return result;
    }
	
	private int findCount(String s, char target)
	{
		int left = 0, right = s.length()-1;
		
		while (left < right && s.charAt(left) != target)
		{
			left++;
		}
		
		while (left < right && s.charAt(right) != target)
		{
			right--;
		}
		
		Set<Character> set = new HashSet<>();
		
		for (int i = left+1; i < right; i++)
		{
			set.add(s.charAt(i));
		}
		
		return set.size();
	}
}
