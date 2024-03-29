/***
 * 
 * @author jackie
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

 

Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 

Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
 */
public class Q1055_Shortest_Way_to_Form_String 
{
	public int shortestWay(String source, String target) 
	{
        if (source == null || source.length() == 0 || target == null || target.length() == 0)
        {
            return -1;
        }
        else if (!isValid(source, target))
        {
        	return -1;
        }
        
        int s_index = 0, t_index = 0;
        int step = 0;
        
        // while (t_index < target.length() && step <= t_index) // step <= t_index 防止死循环
        while (t_index < target.length())
        {
            while (s_index < source.length() && source.charAt(s_index) != target.charAt(t_index))
            {
                s_index++;
            }
            
            if (s_index < source.length() && source.charAt(s_index) == target.charAt(t_index))
            {
                s_index++;
                t_index++;
            }
            else  // s_index == source.length(), find another iteration for string s 
            {
                s_index = 0;
                step++;
            }
        }
        
        return step <= t_index ? step+1 : -1;
    }
	
	private boolean isValid(String source, String target)
	{
		int[] sHash = new int[256];
		int[] tHash = new int[256];
		
		for (char c : source.toCharArray())
		{
			sHash[c]++;
		}
		
		for (char c : target.toCharArray())
		{
			tHash[c]++;
		}
		
		for (int i = 0; i < sHash.length; i++)
		{
			if (tHash[i] > 0 && sHash[i] == 0)
			{
				return false;
			}
		}
		
		return true;
	}
}
