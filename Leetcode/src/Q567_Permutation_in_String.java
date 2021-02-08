import org.omg.CORBA.SystemException;

/**
 * 
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 *
 */
public class Q567_Permutation_in_String 
{
	public boolean checkInclusion(String s1, String s2) 
    {
        if (s1 == null || s1.length() == 0)
        {
            return true;
        }
        else if (s2 == null || s2.length() == 0)
        {
            return false;
        }
        else if (s1.length() > s2.length())
        {
            return false;
        }
        
        int[] hash1 = new int[256];
        int[] hash2 = new int[256];
        
        for (char c : s1.toCharArray())
        {
            hash1[c]++;
        }
        
        for (int i = 0; i < s2.length(); i++)
        {
            hash2[s2.charAt(i)]++;
            
            if (i >= s1.length()-1)
            {
                if (isValid(hash1, hash2))
                {
                    return true;
                }
                
                hash2[s2.charAt(i - (s1.length()-1))]--;
            }
        }
        
        return false;
    }
    
    private boolean isValid(int[] hash1, int[] hash2)
    {
        for (int i = 0; i < hash1.length; i++)
        {
            if (hash1[i] != hash2[i])
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    
    
    
    
    
    /******************************* main **************************************/
    public static void main (String[] args) {
    	Q567_Permutation_in_String test = new Q567_Permutation_in_String();
    	String str1 = "adc";
    	String str2 = "dcda";
    	System.out.println(test.checkInclusion(str1, str2));
    }
}
