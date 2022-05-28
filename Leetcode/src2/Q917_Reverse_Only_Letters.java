/***
 * 
 * @author jackie
 * 
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

 

Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
 

Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122 
S doesn't contain \ or "
 */
public class Q917_Reverse_Only_Letters {
	public String reverseOnlyLetters(String s) {
        if (s == null || s.length() == 0)
        {
            return s;
        }
        
        char[] letters = s.toCharArray();
        int left= 0, right = s.length()-1;
        
        while (left < right)
        {
            while (left < right && !Character.isLetter(letters[left]))
            {
                left++;
            }
            
            while (left < right && !Character.isLetter(letters[right]))
            {
                right--;
            }
            
            char temp = letters[left];
            letters[left] = letters[right];
            letters[right] = temp;
            left++;
            right--;
        }
        
        return new String(letters);
    }
}
