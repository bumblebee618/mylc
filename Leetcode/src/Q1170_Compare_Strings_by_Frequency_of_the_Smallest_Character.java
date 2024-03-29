import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 

Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.
 */
public class Q1170_Compare_Strings_by_Frequency_of_the_Smallest_Character {
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
        if (queries == null || queries.length == 0 || words == null || words.length == 0)
        {
            return new int[0];
        }
        
        Integer[] indexs = new Integer[words.length];
	    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	    for (int i=0; i<words.length; i++) {
		    indexs[i] = getFreq(words[i]);
		    min = Math.min(min, indexs[i]);
		    max = Math.max(max, indexs[i]);
	    }
	    
        Arrays.sort(indexs, (i1, i2) -> i2-i1);
	    int[] result = new int[queries.length];
        
	    for (int i=0; i<queries.length; i++) 
        {
		    int freq = getFreq(queries[i]);
		    
            if (max <= freq) 
            {
			    result[i] = 0;
		    } 
            else if (min > freq) {
			    result[i] = words.length;
		    } 
            else {
			    int pos = binarySearch(indexs, freq);
                result[i] = pos+1;
		    }
	    }
	    
        return result;
    }
    
    private int binarySearch(Integer[] indexs, int target)
    {   
        int left = 0, right = indexs.length-1;
        
        while (left+1 < right)
        {
            int mid = left + (right-left) / 2;
            
            if (indexs[mid] > target)
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        
        return indexs[left] > target ? left : right;
    }
    
    private Integer getFreq(String word)
    {
        int[] hash = new int[256];
        
        for (char c : word.toCharArray())
        {
            hash[c]++;
        }
        
        for (int i = 0; i < 256; i++)
        {
            if (hash[i] != 0)
            {
                return hash[i];
            }
        }
        
        return -1;
    }

    
    
    public static void main(String[] args)
    {
    	Q1170_Compare_Strings_by_Frequency_of_the_Smallest_Character test = new Q1170_Compare_Strings_by_Frequency_of_the_Smallest_Character();
    	String[] queries = {"aabbabbb","abbbabaa","aabbbabaa","aabba","abb","a","ba","aa","ba","baabbbaaaa","babaa","bbbbabaa"};
    	String[] queries1 = {"aabbbabaa"};
    	String[] words = {"b","aaaba","aaaabba","aa","aabaabab","aabbaaabbb","ababb","bbb","aabbbabb","aab","bbaaababba","baaaaa"};
    	
    	test.numSmallerByFrequency(queries1, words);
    }
}
