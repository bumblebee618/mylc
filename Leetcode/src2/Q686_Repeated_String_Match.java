/***
 * 
 * @author jackie
 * 
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.

Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".

 

Example 1:

Input: a = "abcd", b = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
Example 2:

Input: a = "a", b = "aa"
Output: 2
Example 3:

Input: a = "a", b = "a"
Output: 1
Example 4:

Input: a = "abc", b = "wxyz"
Output: -1
 

Constraints:

1 <= a.length <= 104
1 <= b.length <= 104
a and b consist of lower-case English letters.
 */
public class Q686_Repeated_String_Match 
{
	public int repeatedStringMatch(String a, String b) 
    {
        if (a == null || a.length() == 0 || b == null || b.length() == 0)
        {
            return -1;
        }
        else if (!canMatch(a, b))
        {
            return -1;
        }
        
        StringBuilder builderA = new StringBuilder();
        int count = 0;
        
        while (builderA.length() < b.length())
        {
            builderA.append(a);
            count++;
        }
        
        int limit = count * 2;
        
        while (builderA.indexOf(b) < 0 && count <= limit)
        {
            builderA.append(a);
            count++;
        }
        
        return builderA.indexOf(b) < 0 ? -1 : count;
    }
    
    private boolean canMatch(String a, String b)
    {
        int[] hashA = new int[256];
        int[] hashB = new int[256];
        
        for (char c : a.toCharArray())
        {
            hashA[c]++;
        }
        
        for (char c : b.toCharArray())
        {
            hashB[c]++;
        }
        
        for (int i = 0; i < 256; i++)
        {
            if (hashA[i] == 0 && hashB[i] > 0)
            {
                return false;
            }
        }
        
        return true;
    }
	
	
	
	public static void main(String[] args)
	{
		Q686_Repeated_String_Match test = new Q686_Repeated_String_Match();
		String a1 = "abcd";
		String b1 = "cdabcdab";
		String a2 = "a";
		String b2 = "aa";
		System.out.println(test.repeatedStringMatch(a2, b2));
	}
}
