import java.util.*;

/***
 * 
 * @author jackie
 *We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

 

Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]
 

Note:

0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.
 *
 */
public class Q884_Uncommon_Words_from_Two_Sentences {
	public String[] uncommonFromSentences(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0)
        {
            return new String[0];
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String str : A.split(" "))
        {
            map.put(str, map.getOrDefault(str, 0)+1);
        }
        
        for (String str : B.split(" "))
        {
            map.put(str, map.getOrDefault(str, 0)+1);
        }
        
        List<String> list = new LinkedList<>();
        
        for (String key : map.keySet())
        {
            if (map.get(key) == 1)
            {
                list.add(key);
            }
        }
        
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
}
