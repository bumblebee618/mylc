import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

 */
public class Q524_Longest_Word_in_Dictionary_through_Deleting 
{
	public String findLongestWord(String s, List<String> d) 
	{
        if (s == null || s.length() == 0 || d == null || d.size() == 0)
        {
            return "";
        }
        
        int maxLen = 0;
        String result = "";
        
        for (String word : d)
        {
            if (isSubString(s, word))
            {
                if (maxLen < word.length() || (maxLen == word.length() && word.compareTo(result) < 0))
                {
                    maxLen = word.length();
                    result = word;
                }
            }
        }
        
        return result;
    }
    
    private boolean isSubString(String s, String word)
    {
    	int index1 = 0, index2 = 0;
        int size1 = s.length(), size2 = word.length();
        
        while (index1 < size1 && index2 < size2)
        {
            while (index1 < size1 && s.charAt(index1) != word.charAt(index2))
            {
                index1++;
            }
            
            if (index1 == size1)
            {
                return false;
            }
            
            index1++;
            index2++;
        }
        
        return index2 == size2;
    }
}
