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
public class Q1055_Shortest_Way_to_Form_String {
	public int shortestWay(String source, String target) {
        if (source == null || source.length() == 0 || target == null || target.length() == 0)
        {
            return -1;
        }
        
        int s_index = 0, t_index = 0;
        int count = 0;
        
        while (t_index < target.length() && count <= t_index)
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
            else
            {
                s_index = 0;
                count++;
            }
        }
        
        return count <= t_index ? count+1 : -1;
    }
}