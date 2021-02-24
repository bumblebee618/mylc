/***
 * 
 * @author jackie
 * 
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

 

Example 1:

Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
Example 2:

Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
Example 3:

Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
 

Constraints:

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 */
public class Q1768_Merge_Strings_Alternately 
{
	public String mergeAlternately(String word1, String word2) 
    {
        if (word1 == null || word1.length() == 0)
        {
            return word2;   
        }
        else if (word2 == null || word2.length() == 0)
        {
            return word1;
        }
        
        int size1 = word1.length(), size2 = word2.length();
        int index1 = 0, index2 = 0;
        StringBuilder builder = new StringBuilder();
        
        while (index1 < size1 && index2 < size2)
        {
            builder.append(word1.charAt(index1++)).append(word2.charAt(index2++));
        }
        
        if (index1 < size1)
        {
            builder.append(word1.substring(index1));
        }
        
        if (index2 < size2)
        {
            builder.append(word2.substring(index2));
        }
        
        return builder.toString();
    }
}
