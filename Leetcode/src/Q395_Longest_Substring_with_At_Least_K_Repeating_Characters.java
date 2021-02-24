import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**************
 * 
 * @author jackie
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

 

Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 

Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 105
 */

public class Q395_Longest_Substring_with_At_Least_K_Repeating_Characters 
{
	// solution 1:
	public int longestSubstring(String s, int k) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        else if (k <= 0 || k > s.length())
        {
            return 0;
        }
        
        int totalUnique = findTotalUniqueLetters(s);
        int maxLen = 0;
        
        for (int curUnique = 1; curUnique <= totalUnique; curUnique++)
        {
            int[] countMap = new int[256];
            int unique = 0, kCount = 0;
            int front = 0, back = 0;
            
            while (front < s.length())
            {
            	// increase window size
                if (unique <= curUnique)
                {
                    char c = s.charAt(front++);
                
                    if (countMap[c]++ == 0)
                    {
                        unique++;
                    }
                
                    if (countMap[c] == k)
                    {
                        kCount++;
                    }
                }
                // decrease window size
                else
                {
                    char c = s.charAt(back++);
                    
                    if (countMap[c]-- == 1)
                    {
                        unique--;
                    }
                
                    if (countMap[c] == k-1)
                    {
                        kCount--;
                    }
                }
                
                if (unique == curUnique && unique == kCount)
                {
                    maxLen = Math.max(maxLen, front-back);
                }
            }
        }
        
        return maxLen;
    }
    
    private int findTotalUniqueLetters(String s)
    {
        int[] hash = new int[256];
        int unique = 0;
        
        for (char c : s.toCharArray())
        {
            if (hash[c]++ == 0)
            {
                unique++;
            }
        }
        
        return unique;
    }


	
    
    
    
    
    
	// solution 2:
	public int longestSubstring2(String s, int k) 
	{
        if(s == null || k <= 0)
        {
            return 0;
        }
        
        int len = s.length();
        int maxLen = 0;      
        int front = 0, back = 0;
        Set<Character> set = buildSet(s, k);
        
        while(back < len){
        	while(front < len && set.contains(s.charAt(front))){
        		front++;
        	}
        	
        	int localMax = getMaxLen(s, back, front - 1, k);
        	maxLen = Math.max(maxLen, localMax);
        	front++;
        	back = front;
        }
        
        return maxLen;
    }
    
    public int getMaxLen(String s, int start, int end, int k){
		int[] hash = new int[256];
		int localMax = 0;
		Set<Character> numSet = new HashSet<Character>();
		int front = start, back = start;
		
		for(int i = start; i <= end; i++){
    		hash[s.charAt(i)]++;
    	}
		
		for(int i = 0; i < 256; i++){
			if(hash[i] > 0 && hash[i] < k){
				numSet.add((char) i);
				System.out.println("char = " + (char) i);
			}
		}
		
		while(back <= end){
			int[] validHash = new int[256];
			
			while(front <= end && !numSet.contains(s.charAt(front))){
				validHash[s.charAt(front)]++;
				front++;
			}
			
			if(isValid(validHash, k)){
				localMax = Math.max(localMax, front - back);
			}
			
			front++;
			back = front;
		}
		
		return localMax;
	}
	
	public Set<Character> buildSet(String s, int k){
		int[] hash = new int[256];
		Set<Character> set = new HashSet<Character>();
		
		for(char c : s.toCharArray()){
			hash[c]++;
		}
		
		for(int i = 0; i < 256;i ++){
			if(hash[i] >= k){
				set.add((char) i);
			}
		}
		
		return set;
	}
	
	public boolean isValid(int[] hash, int k){
		int count = 0;
		
		for(int i = 0; i < 256; i++){
			if(hash[i] > 0){
				count++;
			}
			
			if(hash[i] > 0 && hash[i] < k){
				return false;
			}
		}

		return count > 0;
	}
	
	
	
	
	
	public static void main(String[] args){
		Q395_Longest_Substring_with_At_Least_K_Repeating_Characters t = new Q395_Longest_Substring_with_At_Least_K_Repeating_Characters();
//		String s = "aaaaaaaaaaaaaaaabbbbbbbbbbbbaaaaaaabbbbbbbbbbbbcccccccccccdddddddddddddddddddeeeeeeeeeeeeeeefffffffffffffffgggggggggggggggggggghhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkk";
		String s = "ababacb";
		int k = 3;
		System.out.println(t.longestSubstring(s, k));
	}
}
