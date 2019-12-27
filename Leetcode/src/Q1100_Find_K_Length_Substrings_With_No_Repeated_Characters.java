/***
 * 
 * @author jackie
 * 
 * Given a string S, return the number of substrings of length K with no repeated characters.

 

Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation: 
Notice K can be larger than the length of S. In this case is not possible to find any substring.
 

Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4
 */
public class Q1100_Find_K_Length_Substrings_With_No_Repeated_Characters {
	public int numKLenSubstrNoRepeats(String s, int k) {
        if (s == null || k <= 0 || s.length() < k)
        {
            return 0;
        }
        
        int[] hash = new int[256];
        int duplicateCount = 0;
        int result = 0;
        
        for (int i = 0; i < s.length(); i++)
        {
            if (hash[s.charAt(i)]++ == 1)
            {
                duplicateCount++;
            }
            
            if (i-k >= 0)
            {
                if (--hash[s.charAt(i-k)] == 1)
                {
                    duplicateCount--;
                }
            }
            
            if (i+1-k >= 0 && duplicateCount == 0)
            {
                result++;
            }
        }
        
        return result;
    }
}
