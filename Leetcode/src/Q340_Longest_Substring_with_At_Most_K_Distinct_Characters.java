/*****************************************************************************
 * 	此题为159题的follow up
 * 
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
 *   
 *****************************************************************************/

public class Q340_Longest_Substring_with_At_Most_K_Distinct_Characters {
	// solution 1: time O(n), space O(1)
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 0)
        {
            return 0;
        }
        
        int[] hash = new int[256];
        int count = 0;
        int maxLen = 0;
        
        for (int front = 0, back = 0; front < s.length(); front++)
        {
            if (hash[s.charAt(front)]++ == 0)
            {
                count++;
            }
            
            while (count > k)
            {
                if (hash[s.charAt(back++)]-- == 1)
                {
                    count--;
                }
            }
            
            maxLen = Math.max(maxLen, front - back + 1);
        }
        
        return maxLen;
    }
}
