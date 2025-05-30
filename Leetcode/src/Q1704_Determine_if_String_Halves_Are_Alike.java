import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

 

Example 1:

Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
Example 2:

Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
Example 3:

Input: s = "MerryChristmas"
Output: false
Example 4:

Input: s = "AbCdEfGh"
Output: true
 

Constraints:

2 <= s.length <= 1000
s.length is even.
s consists of uppercase and lowercase letters.
 */
public class Q1704_Determine_if_String_Halves_Are_Alike 
{
	public boolean halvesAreAlike(String s) 
    {
        if (s == null || s.length() == 0 || s.length() % 2 != 0)
        {
            return false;
        }
        
        s = s.toLowerCase();
        int count = 0, size = s.length();
        
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        
        for (int i = 0; i < size; i++)
        {
            if (vowels.contains(s.charAt(i)))
            {
                count += (i >= size/2) ? -1 : 1; 
            }
        }
        
        return count == 0;
    }
}
