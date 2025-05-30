import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.

 */
public class Q1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters {
	// solution 1: backtrack + bit manipulation
	private int maxLen = 0;
    private Map<String, Integer> identityMap;

    public int maxLength(List<String> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }

        identityMap = new HashMap<>();
        List<String> filtedList = new ArrayList<>();
        for (String str : list) {
            int identity = getStrIdentity(str);
            
            if (identity > 0) {
                filtedList.add(str);
                identityMap.put(str, identity);
            }
        }

        backtrack(filtedList, 0, 0, 0);
        return maxLen;
    }

    private void backtrack(List<String> list, int start, int curIdentity, int curLen) {
        maxLen = Math.max(maxLen, curLen);
        for (int i = start; i < list.size(); i++) {
            int nextIdentity = identityMap.get(list.get(i));
            if ((curIdentity & nextIdentity) == 0) {
                backtrack(list, i+1, (curIdentity | nextIdentity), curLen + list.get(i).length());
            }
        }
    }

    private int getStrIdentity(String s) {
        if (s == null || s.length() == 0 || s.length() > 26) {
            return 0;
        }

        int[] hash = new int[26];
        for (char c : s.toCharArray()) {
            if (hash[c-'a'] > 0) {
                return 0;
            } else {
                hash[c-'a']++;
            }
        }

        int identity = 0;
        for (int i = 0; i < 26; i++) {
            if (hash[i] == 1) {
                identity |= (1 << i);
            }
        }
        
        return identity;
    }
    
    
    // Solution 2:
	public int maxLength2(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        
        List<Integer> solution = new ArrayList<>();
        solution.add(0);
        int result = 0;
        
        for (String s : arr) {
            int status = 0, dup = 0;
            
            // check duplicated char in one word
            for (char c : s.toCharArray()) {
                dup |= status & (1 << (c - 'a'));
                status |= 1 << (c - 'a');
            }
            
            if (dup > 0) {
                continue;
            }
            
            // 从solution.size()-1 开始遍历，即使改变solution也不影响之后的操作
            for (int i = solution.size() - 1; i >= 0; i--) {
                if ((solution.get(i) & status) > 0) {
                    continue;
                }
                
                // merge status
                int nextStatus = (solution.get(i) | status);
                solution.add(nextStatus);
                result = Math.max(result, Integer.bitCount(nextStatus));
            }
        }
        
        return result;
    }
}
