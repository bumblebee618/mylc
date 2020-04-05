/***
 * 
 * @author jackie
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 

Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
 */
public class Q809_Expressive_Words {
	public int expressiveWords(String str, String[] words) {
        if (str == null || str.length() == 0 || words == null || words.length == 0)
        {
            return 0;
        }
        
        int result = 0;
        
        for (String word : words)
        {
            if (isExpressive(str, word))
            {
                result++;
            }
        }
        
        return result;
    }
    
    private boolean isExpressive(String str, String input)
    {
        int index1 = 0, index2 = 0;
        int len1 = str.length();
        int len2 = input.length();
        
        while (index1 < len1 && index2 < len2)
        {
            if (str.charAt(index1) != input.charAt(index2))
            {
                return false;
            }
            
            int count1 = 1, count2 = 1;
            
            while (index1+1 < len1 && str.charAt(index1) == str.charAt(index1+1))
            {
                index1++;
                count1++;
            }
            
            while (index2+1 < len2 && input.charAt(index2) == input.charAt(index2+1))
            {
                index2++;
                count2++;
            }
            
            index1++;
            index2++;
            
            if (count1 < count2)
            {
                return false;
            }
            else if (count1 == count2)
            {
                continue;
            }
            else if (count1 < 3)
            {
                return false;
            }
        }
        
        return index1 == len1 && index2 == len2;
    }
}
