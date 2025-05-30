import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.
 

Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.
 */
public class Q1153_String_Transforms_Into_Another_String {
	public boolean canConvert(String str1, String str2) {
        if (str1 == null || str2 == null)
        {
            return false;        
        }
        else if (str1.length() == 0 || str1.length() != str2.length())
        {
            return false;
        }
        else if (str1.equals(str2))
        {
            return true;
        }
            
        boolean[] used = new boolean[26];
        int[] mapping = new int[26];
        int countUsed = 0;
        Arrays.fill(mapping, -1);
    
        for (int i = 0; i < str1.length(); i++) 
        {
            int idx1 = str1.charAt(i) - 'a';
            int idx2 = str2.charAt(i) - 'a';

            if (mapping[idx1] != -1 && mapping[idx1] != idx2)
            {
                return false;
            }

            mapping[idx1] = idx2;

            if (!used[idx2]) 
            {
                used[idx2] = true;
                ++countUsed;
            }
        }

        return countUsed < 26;
    }
}
