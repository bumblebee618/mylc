/******
 * 
Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:
	k will be a positive integer and encoded string will not be empty or have extra space.
	You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
	If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.

Example 1:
	Input: "aaa"
	Output: "aaa"
	Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.

Example 2:
	Input: "aaaaa"
	Output: "5[a]"
	Explanation: "5[a]" is shorter than "aaaaa" by 1 character.

Example 3:
	Input: "aaaaaaaaaa"
	Output: "10[a]"
	Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".

Example 4:
	Input: "aabcaabcd"
	Output: "2[aabc]d"
	Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".

Example 5:
	Input: "abbbabbbcabbbabbbc"
	Output: "2[2[abbb]c]"
	Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
	
 * 
 * */

public class Q471_Encode_String_with_Shortest_Length 
{
	/*******************************************************************************************************
	 * 区间dp:
	 * We will form 2-D array of Strings. dp[i][j] = string from index i to index j in encoded form.
	 * We can write the following formula as:-
	 * 		dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) or if we can find some pattern in string from i to j which will result in more less length.
	 * Time Complexity = O(n^3)
	 * 
	 *******************************************************************************************************/
	
	public String encode(String s) 
	{
		if (s == null || s.length() == 0) 
		{
			return s;
		}
		
		int len = s.length();
		
		// this DP array keeps the best solution for substring[i, j] 
		String[][] subStrSolution = new String[len][len];

		for (int length = 1; length <= len; length++) 
		{
			for (int start = 0; start + length <= len; start++) 
			{
				int end = start + length - 1;
				String substr = s.substring(start, end + 1);
				subStrSolution[start][end] = substr;
				
				// Checking if string length < 5. In that case, we know that encoding will not help.
				if (length >= 5) 
				{
					// Loop for trying all results that we get after dividing
					// the strings into 2 and combine the results of 2 substrings
					for (int k = start; k < end; k++) 
					{
						if ((subStrSolution[start][k] + subStrSolution[k + 1][end]).length() < subStrSolution[start][end].length())
						{
							subStrSolution[start][end] = subStrSolution[start][k] + subStrSolution[k + 1][end];
						}
					}

					// Loop for checking if string can itself found some pattern in it which could be repeated.
					for (int k = 0; k < substr.length(); k++) 
					{
						String repeatStr = substr.substring(0, k + 1);
						
						if (repeatStr != null 
							&& substr.length() % repeatStr.length() == 0 
							&& substr.replaceAll(repeatStr, "").length() == 0) 
						{
							// 注意这里用subStrSolution[start][start + k] 而不是repeatStr！
							String ss = String.format("%d[%s]", substr.length() / repeatStr.length(), subStrSolution[start][start + k]);
							
							if (subStrSolution[start][end].length() > ss.length()) 
							{
								subStrSolution[start][end] = ss;
							}
						}
					}
				}
			}
		}

		return subStrSolution[0][len - 1];
	}
}
