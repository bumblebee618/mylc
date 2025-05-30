/*******
 * 
Validate if a given string is numeric.

Some examples:
	"0" => true
	" 0.1 " => true
	"abc" => false
	"1 a" => false
	"2e10" => true

Note: 
	It is intended for the problem statement to be ambiguous. 
	You should gather all requirements up front before implementing one.
	
 * 
 * */

public class Q065_Valid_Number {
	// test case: 
    // 		blank in front or back -> valid
    // 		blank in the middle
    // 		more than one dot
    // 		more than one e
    // 		has e after dot
    // 		need to have num before e
    // 		+, - should be put before all the number
    // 		invalid input: 6 + 1
    // 		valid input: 11e+6
    
	public boolean isNumber(String s) 
    {
        if (s == null)
        {
            return false;
        }
        
        s = s.trim();
        
        if (s.length() == 0)
        {
            return false;
        }
        
        boolean hasNum = false;
        boolean hasNumAfterE = false;
        boolean hasDot = false;
        boolean hasE = false;
        
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            if (Character.isDigit(c))
            {
                hasNum = hasNumAfterE = true;
            }
            else if (c == '.')
            {
                if (hasDot || hasE)
                {
                    return false;
                }
                
                hasDot = true;
            }
            else if (c == 'e' || c == 'E')
            {
                if (hasE || !hasNum)
                {
                    return false;
                }
                
                hasE = true;
                hasNumAfterE = false;
            }
            else if (c == '+' || c == '-')
            {
                if (i == 0 || s.charAt(i-1) == 'e' || s.charAt(i-1) == 'E')
                {
                    continue;
                }
                
                return false;
            }
            else
            {
                return false;
            }
        }
        
        return hasNum && hasNumAfterE;
    }
	
	
    
    
    
    
    
    
    
    
    
    /****************************** main function ***********************************/
	
	public static void main(String[] args){
		Q065_Valid_Number t = new Q065_Valid_Number();
		String str = "0e123";
		System.out.println(t.isNumber(str));
	}
}
