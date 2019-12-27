/***
 * 
 * @author jackie
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 *
 */
public class Q767_Reorganize_String {
	public String reorganizeString(String s) {
        if (s == null || s.length() == 0)
        {
            return s;
        }
        
        int[] frequency = new int[256];
        int[] validIndex = new int[256];
        StringBuilder builder = new StringBuilder();
        
        for (char c : s.toCharArray())
        {
            frequency[c]++;
        }
        
        for (int index = 0; index < s.length(); index++)
        {
            int candidate = getNextCandidate(frequency, validIndex, index);
            
            if (candidate == -1)
            {
                return "";    
            }
            
            frequency[candidate]--;
            validIndex[candidate] = index+2;
            builder.append((char) candidate);
        }
        
        return builder.toString();
    }
    
    private int getNextCandidate(int[] frequency, int[] validIndex, int curIndex)
    {
        int maxFreq = 0;
        int candidate = -1;
        
        for (int i = 0; i < 256; i++)
        {
            if (frequency[i] > maxFreq && curIndex >= validIndex[i])
            {
                maxFreq = frequency[i];
                candidate = i;
            }
        }
        
        return candidate;
    }
}
