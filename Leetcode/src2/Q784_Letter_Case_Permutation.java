import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.

 

Example 1:

Input: S = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: S = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: S = "12345"
Output: ["12345"]
Example 4:

Input: S = "0"
Output: ["0"]
 

Constraints:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */

public class Q784_Letter_Case_Permutation 
{
	public List<String> letterCasePermutation(String S) 
    {
        List<String> result = new LinkedList<>();
        
        if (S == null || S.length() == 0)
        {
            return result;
        }
        
        backtrack(result, S, 0);
        return result;
    }
    
    private void backtrack(List<String> result, String S, int start)
    {
        result.add(S);
        
        if (start == S.length())
        {
            return;
        }
        
        char[] letters = S.toCharArray();
        
        for (int i = start; i < letters.length; i++)
        {
            if (Character.isLetter(letters[i]))
            {
                char tmp = letters[i];
                
                if (letters[i] >= 'A' && letters[i] <= 'Z')
                {
                    letters[i] = (char) (letters[i] - 'A' + 'a');
                }
                else
                {
                    letters[i] = (char) (letters[i] - 'a' + 'A');
                }
                
                String newStr = new String(letters);
                backtrack(result, newStr, i+1);
                letters[i] = tmp;
            }
        }
    }
}
