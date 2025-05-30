import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).

Each replacement operation has 3 parameters: a starting index i, a source word row and a target word col.  The rule is that if row starts at position i in the original string S, then we will replace that occurrence of row with col.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2, row = "cd", col = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0, row = "ab", col = "eee", as well as another replacement operation i = 2, row = "ec", col = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match row[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.

Example 1:

Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
Output: "eeebffff"
Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".
Example 2:

Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
"ec" doesn't starts at index 2 in the original S, so we do nothing.
Notes:

0 <= indexes.length = sources.length = targets.length <= 100
0 < indexes[i] < S.length <= 1000
All characters in given inputs are lowercase letters.
 */
public class Q833_Find_And_Replace_in_String {
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if (S == null || S.length() == 0)
        {
            return S;
        }
        else if (indexes == null || indexes.length == 0)
        {
            return S;
        }
        else if (sources == null || sources.length == 0)
        {
            return S;
        }
        else if (targets == null || targets.length == 0)
        {
            return S;
        }
        else if (indexes.length != sources.length || indexes.length != sources.length)
        {
            return S;
        }
        
        int size = S.length();
        int[] match = new int[size];
        Arrays.fill(match, -1);
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < indexes.length; i++)
        {
           int index = indexes[i];
           String subStr = S.substring(index, index+sources[i].length());
            
           if (subStr.equals(sources[i]))
           {
               match[index] = i;
           }
        }
        
        int curIndex = 0;
        
        while (curIndex < size)
        {
            if (match[curIndex] >= 0)
            {
                builder.append(targets[match[curIndex]]);
                curIndex += sources[match[curIndex]].length();
            }
            else
            {
                builder.append(S.charAt(curIndex++));
            }
        }
        
        return builder.toString();
    }
}
