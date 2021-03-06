import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
 */
public class Q1209_Remove_All_Adjacent_Duplicates_in_String_II 
{
	public String removeDuplicates(String s, int k) 
	{
        if (s == null || s.length() == 0 || k <= 0 || k > s.length())
        {
            return s;
        }
        
        Stack<Character> characters = new Stack<>();
        Stack<Integer> frequency = new Stack<>();
        StringBuilder builder = new StringBuilder();
        
        for (char c : s.toCharArray())
        {
            if (!characters.isEmpty() && characters.peek() == c)
            {
                frequency.push(frequency.pop()+1);
            }
            else
            {
                characters.push(c);
                frequency.push(1);
            }
            
            if (frequency.peek() == k)
            {
                characters.pop();
                frequency.pop();
            }
        }
        
        while (!characters.isEmpty())
        {
            char c = characters.pop();
            int count = frequency.pop();
            
            for (int i = 0; i < count; i++)
            {
                builder.insert(0, c);
            }
        }
        
        return builder.toString();
    }
}
