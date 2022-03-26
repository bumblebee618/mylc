import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".

 

Example 1:

Input: s = "banana"
Output: "ana"
Example 2:

Input: s = "abcd"
Output: ""
 

Constraints:

2 <= s.length <= 3 * 104
s consists of lowercase English letters.
 */
public class Q1044_Longest_Duplicate_Substring {
	// solution 1: binary search + roll hash
	private static final long mod = (1 << 31) - 1;
    private static final long base = 256;
        
    public String longestDupSubstring(String S) {      
    	if (S == null || S.length() <= 1) {
            return "";
        }
    	
        int left = 1, right = S.length();
        int start = 0, maxLen = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean found = false;
            
            Map<Long, List<Integer>> map = new HashMap<>();
            long hash = hash(S, mid);
            map.computeIfAbsent(hash, x -> new ArrayList<>()).add(0);
            long RM = 1l;
            
            for (int i = 1; i < mid; i++) {
            	RM = (base * RM) % mod;
            }
            
            loop:
            for (int i = 1; i + mid <= S.length(); i++) {
                hash = (hash + mod - RM * S.charAt(i - 1) % mod) % mod;
                hash = (hash * base + S.charAt(i + mid - 1)) % mod;
                
                if (!map.containsKey(hash)) {
                    map.put(hash, new ArrayList<>());
                } else {
                    for (int j : map.get(hash)) {
                    	if (S.substring(i, i+mid).equals(S.substring(j, j+mid))) {
                            found = true;
                            start = i;
                            maxLen = mid;
                            break loop;
                        }
                    }
                }
                
                map.get(hash).add(i);
            }
            
            if (found) {
            	left = mid + 1;
            } else {
            	right = mid - 1;
            }
        }
        
        return S.substring(start, start + maxLen);
    }
    
    private long hash(String S, int len) { 
        long h = 0;
        
        for (int j = 0; j < len; j++) {
        	h = (base * h + S.charAt(j)) % mod;
        }
        
        return h;
    }

	
	
	
	// solution 2: Binary Search, TL
	public String longestDupSubstring2(String s) 
    {
        if (s == null || s.length() <= 1)
        {
            return "";
        }
        
        int left = 1, right = s.length();
        
        while (left < right)
        {
            int mid = left + (right - left)/2;
            
            System.out.println("mid = " + mid + ", " + search(s, mid));
            
            if (search(s, mid).length() > 0)
            {
                left = mid+1;
            }
            else
            {
                right = mid;
            }
        }
        
        String result = search(s, left);
        return result.length() > 0 ? result : search(s, left-1);
    }
    
    private String search(String s, int target)
    {
        if (target >= s.length())
        {
            return "";
        }
        
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i <= s.length()-target; i++)
        {
            String str = s.substring(i, i+target);
            
            if (!set.contains(str))
            {
                set.add(str);
            }
            else
            {
                return str;
            }
        }
        
        return "";
    }
    
    
    
    
    
    /************************************** main **************************************/
    
    public static void main(String[] args)
    {
    	Q1044_Longest_Duplicate_Substring test = new Q1044_Longest_Duplicate_Substring();
    	String s = "abcd";
    	System.out.println(test.longestDupSubstring2(s));
    }
}
