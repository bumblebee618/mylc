import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?

 

Example 1:

Input: strs = ["tars","rats","arts","star"]
Output: 2
Example 2:

Input: strs = ["omv","ovm"]
Output: 1
 

Constraints:

1 <= strs.length <= 100
1 <= strs[i].length <= 1000
sum(strs[i].length) <= 2 * 104
strs[i] consists of lowercase letters only.
All words in strs have the same length and are anagrams of each other.
 */
public class Q839_Similar_String_Groups 
{
	public int numSimilarGroups(String[] strs) 
	{
        if (strs == null || strs.length == 0)
        {
        	return 0;
        }
        
        int count = 0;
        Set<String> visited = new HashSet<>();
        
        for (int i = 0; i < strs.length; i++)
        {
        	if (!visited.contains(strs[i]))
        	{
        		count++;
                visited.add(strs[i]);
        	    dfs(strs, strs[i], visited);
        	}
        }
        
        return count;
    }
	
	private void dfs(String[] strs, String target, Set<String> visited)
	{
		for (int i = 0; i < strs.length; i++)
        {
        	if (!visited.contains(strs[i]))
        	{
        		if (isSimilarGroup(target, strs[i]))
        	    {
                    visited.add(strs[i]);
        		    dfs(strs, strs[i], visited);
        	    }
        	}
        }
	}
	
	private boolean isSimilarGroup(String str1, String str2)
	{
        if (str1.equals(str2))
        {
            return true;
        }
        
		int diff = 0;
		
		for (int i = 0; i < str1.length(); i++)
		{
			if (str1.charAt(i) != str2.charAt(i))
			{
				diff++;
			}
		}
		
		return diff == 2;
	}
	
	
	
	
	public static void main(String[] args)
	{
		Q839_Similar_String_Groups test = new Q839_Similar_String_Groups();
		/***
		String[] strs1 = {"tars","rats","arts","star"};
		System.out.println(test.numSimilarGroups(strs1));
		String[] strs2 = {"omv","ovm"};
		System.out.println(test.numSimilarGroups(strs2));
		***/
		String[] strs3 = {"kccomwcgcs","socgcmcwkc","sgckwcmcoc","coswcmcgkc","cowkccmsgc","cosgmccwkc","sgmkwcccoc","coswmccgkc","kowcccmsgc","kgcomwcccs"};
		System.out.println(test.numSimilarGroups(strs3));
	}
}
