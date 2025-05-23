import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
 */
public class Q616_Add_Bold_Tag_in_String 
{
    private Trie root;
    
    public String addBoldTag(String s, String[] dict) 
    {
        if (s == null || s.length() == 0
        	|| dict == null || dict.length == 0)
        {
            return s;
        }
        
        root = new Trie();
        buildTrie(dict);
        List<int[]> intervals = new LinkedList<>();
        
        for (int startIndex = 0; startIndex < s.length(); startIndex++)
        {
            int endIndex = findPos(s, startIndex);
            
            if (endIndex != -1)
            {
                intervals.add(new int[] {startIndex, endIndex});
            }
        }
        
        intervals = mergeInterval(intervals);
        StringBuilder builder = new StringBuilder();
        int pointer = 0;
        
        for (int[] interval : intervals)
        {
            if (pointer < interval[0])
            {
                builder.append(s.substring(pointer, interval[0]));
            }
            
            builder.append("<b>").append(s.substring(interval[0], interval[1]+1)).append("</b>");
            pointer = interval[1]+1;
        }
        
        if (pointer < s.length())
        {
            builder.append(s.substring(pointer));
        }
        
        return builder.toString();
    }
    
    private List<int[]> mergeInterval(List<int[]> intervals)
    {
        if (intervals.size() == 0)
    	{
    		return intervals;
    	}
        
        List<int[]> result = new LinkedList<>();
        int start = intervals.get(0)[0];
        int end = intervals.get(0)[1];
        
        for (int[] interval : intervals)
        {
            if (interval[0] <= end+1)  // test case: [0,1], [2,3] -> [0,3]
            {
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
            else
            {
                result.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        
        result.add(new int[] {start, end});
        return result;
    }
    
    private int findPos(String str, int index)
    {
        Trie node = root;
        
        while (index < str.length())
        {
            char c = str.charAt(index);
            
            if (node.children[c] == null)
            {
                break;
            }
            
            node = node.children[c];
            index++;
        }
        
        return node.isWord ? index-1 : -1;
    }
    
    private void buildTrie(String[] dict)
    {
        for (String word : dict)
        {
            if (word == null || word.length() == 0)
            {
                continue;
            }
            
            Trie node = root;
            
            for (char c : word.toCharArray())
            {
                if (node.children[c] == null)
                {
                    node.children[c] = new Trie();
                }
                
                node = node.children[c];
            }
            
            node.isWord = true;
        }
    }
    
    class Trie
    {
        public Trie[] children;
        public boolean isWord;
        
        public Trie()
        {
            children = new Trie[256];
            isWord = false;
        }
    }


    
    /**************************************** main ****************************************/ 
    
    public static void main(String[] args)
    {
    	/***
    	Q616_Add_Bold_Tag_in_String test1 = new Q616_Add_Bold_Tag_in_String();
    	String s1 = "abcxyz123";
    	String[] dict1 = {"abc","123"};
    	String result1 = test1.addBoldTag(s1, dict1);
    	System.out.println(result1);
    	
    	Q616_Add_Bold_Tag_in_String test2 = new Q616_Add_Bold_Tag_in_String();
    	String s2 = "aaabbcc";
    	String[] dict2 = {"aaa","aab", "bc"};
    	String result2 = test2.addBoldTag(s2, dict2);
    	System.out.println(result2);
    	***/
    	
    	Q616_Add_Bold_Tag_in_String test3 = new Q616_Add_Bold_Tag_in_String();
    	String s3 = "aaabbcc";
    	String[] dict3 = {"d"};
    	String result3 = test3.addBoldTag(s3, dict3);
    	System.out.println(result3);
    }
}
