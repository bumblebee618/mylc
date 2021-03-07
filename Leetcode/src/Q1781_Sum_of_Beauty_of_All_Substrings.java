/***
 * 
 * @author jackie
 * 
 * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.

For example, the beauty of "abaacc" is 3 - 1 = 2.
Given a string s, return the sum of beauty of all of its substrings.

 

Example 1:

Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
Example 2:

Input: s = "aabcbaa"
Output: 17
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters.

 */
public class Q1781_Sum_of_Beauty_of_All_Substrings 
{
	public int beautySum(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int size = s.length();
        int result = 0;
        
        for (int start = 0; start <= size-3; start++)
        {
            int[] hash = new int[26];
            
            for (int end = start; end < size; end++)
            {
                hash[s.charAt(end)-'a']++;
                
                if (end-start >= 2)
                {
                    result += findDiff(hash);
                }
            }
        }
        
        return result;
    }
    
    private int findDiff(int[] hash)
    {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int num : hash)
        {
            max = Math.max(max, num);
            min = (num == 0) ? min : Math.min(min, num);
        }
        
        return max-min;
    }
}
