import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 *
 * Given a binary string s and an integer k.

Return True if every binary code of length k is a substring of s. Otherwise, return False.

 

Example 1:

Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
Example 2:

Input: s = "00110", k = 2
Output: true
Example 3:

Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 
Example 4:

Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
Example 5:

Input: s = "0000000001011100", k = 4
Output: false
 

Constraints:

1 <= s.length <= 5 * 10^5
s consists of 0's and 1's only.
1 <= k <= 20
 */
public class Q1461_Check_If_a_String_Contains_All_Binary_Codes_of_Size_K 
{
	// time is O(n), space is O(n*k)
	public boolean hasAllCodes(String s, int k) {
        if (s == null || s.length() < k) {
            return false;
        }
        
        Set<String> set = new HashSet<>();
        
        for (int start = 0; start+k-1 < s.length(); start++) {
            String str = s.substring(start, start+k);
            set.add(str);
        }
        
        int expected = (1 << k);
        return set.size() == expected;
    }
}
