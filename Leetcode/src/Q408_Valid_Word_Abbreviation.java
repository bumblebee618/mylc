/***
 * 
 * @author jackie
 *
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
 */

public class Q408_Valid_Word_Abbreviation {
	// test case: [hi] [02] -> false,  [a] [1] -> true,  
	
	// using two pointers, time is O(n)
	public boolean validWordAbbreviation(String word, String abbr) 
	{
        if (word == null || word.length() == 0 || abbr == null || abbr.length() == 0 || abbr.length() > word.length()) 
        {
            return false;
        }
        
        int len1 = word.length(), len2 = abbr.length();
        int index1 = 0, index2 = 0;
        char[] letters1 = word.toCharArray();
        char[] letters2 = abbr.toCharArray();
        
        while (index1 < len1 && index2 < len2) 
        {
        	// skip the letters
            if (Character.isDigit(letters2[index2])) 
            {
            	if (letters2[index2] == '0') 
            	{
            		return false;
            	}
            	
                int skipCount = 0;
                
                while (index2 < len2 && Character.isDigit(letters2[index2])) 
                {
                    skipCount = skipCount * 10 + (letters2[index2] - '0');
                    index2++;
                }
                
                index1 += skipCount;   // 注意这里是 +sum !!!
            } 
            else 
            {
                if (letters1[index1++] != letters2[index2++]) 
                {
                    return false;
                } 
            }
        }
        
        return index1 == len1 && index2 == len2;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************* main function **********************************/
	
	public static void main(String[] args) {
		Q408_Valid_Word_Abbreviation t = new Q408_Valid_Word_Abbreviation();
//		String word = "internationalization";
//		String abbr = "i12iz4n";
		String word = "a";
		String abbr = "1";
		
		System.out.println(t.validWordAbbreviation(word, abbr));
	}
}
