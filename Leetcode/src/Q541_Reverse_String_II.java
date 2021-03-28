/**
 * 
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 *
 */
public class Q541_Reverse_String_II 
{
	public String reverseStr(String s, int k) 
    {
        if (s == null || s.length() == 0 || k <= 0)
        {
            return s;
        }
        
        char[] letters = s.toCharArray();
        int size = s.length();
        
        for (int i = 0; i < size; i += 2*k)
        {
            reverse(letters, i, Math.min(i+k-1, size-1));
        }
        
        return new String(letters);
    }
    
    private void reverse(char[] letters, int left, int right)
    {
        while (left < right)
        {
            char tmp = letters[left];
            letters[left] = letters[right];
            letters[right] = tmp;
            left++;
            right--;
        }
    }
}
