/***
 * 
 * @author jackie
 * 
 * Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:

Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".
Example 2:

Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".
 

Constraints:

1 <= s.length <= 1000
1 <= part.length <= 1000
s​​​​​​ and part consists of lowercase English letters.
 */
public class Q1910_Remove_All_Occurrences_of_a_Substring 
{
	public String removeOccurrences(String s, String part) 
	{
		if (s == null || s.length() == 0 
			|| part == null || part.length() == 0 
			|| s.length() < part.length())
		{
			return s;
		}
		
		char[] letters = s.toCharArray();
		int size1 = s.length(), size2 = part.length();
		int leftCount = size2-1;
		
		for (int i = size2-1; i < size1; i++)
		{
			leftCount++;
			
			if (leftCount >= size2 && isMatch(letters, i, part))
			{
				update(letters, i, size2);
				leftCount -= size2;
			}
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < size1; i++)
		{
			if (letters[i] != '*')
			{
				builder.append(letters[i]);
			}
		}
		
		return builder.toString();
    }
	
	private boolean isMatch(char[] letters, int end1, String part)
	{
		int end2 = part.length()-1;
		
		while (end1 >= 0 && end2 >= 0)
		{
			if (letters[end1] == '*')
			{
				end1--;
				continue;
			}
			
			if (letters[end1] != part.charAt(end2))
			{
				return false;
			}
			
			end1--;
			end2--;
		}
		
		return end2 == -1;
	}
	
	private void update(char[] letters, int end, int count)
	{
		int num = 0;
		
		while (num < count)
		{
			if (letters[end] != '*')
			{
				letters[end] = '*';
				num++;
			}
			
			end--;
		}
	}
}
