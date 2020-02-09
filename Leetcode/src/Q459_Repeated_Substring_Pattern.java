/******
 * 
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.


Example 2:
Input: "aba"

Output: False


Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
Show Company Tags
Show Tags
Show Similar Problems

 * 
 * */

public class Q459_Repeated_Substring_Pattern {
	public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
        {
            return false;
        }
        
        int size = s.length();
        
        for (int subLen = 1; subLen <= size/2; subLen++)
        {
            if (size % subLen != 0)
            {
                continue;
            }
            
            int start = 0;
            String pattern = s.substring(0, subLen);
            
            while (start < size)
            {
                int index = s.indexOf(pattern, start);
                
                if (index != start)
                {
                    break;
                }
                else
                {
                    start += subLen;
                }
            }
            
            if (start == size)
            {
                return true;
            }
        }
        
        return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************** main function ******************************/
	
	public static void main(String[] args) {
		Q459_Repeated_Substring_Pattern t = new Q459_Repeated_Substring_Pattern();
		String str = "aa";
		System.out.println(t.repeatedSubstringPattern(str));
	}
}
