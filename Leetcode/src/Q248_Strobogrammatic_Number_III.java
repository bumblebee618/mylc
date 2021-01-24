/***
 * 
 * @author jackie
 * 
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */
public class Q248_Strobogrammatic_Number_III {
	private char[] pair1 = {'0', '1', '6', '8', '9'};
    private char[] pair2 = {'0', '1', '9', '8', '6'};
    private char[] single = {'0', '1', '8'};
    private int count = 0;
    
    public int strobogrammaticInRange(String low, String high) 
    {
        for (int i = low.length(); i <= high.length(); i++)
        {
            backtrack("", i, low, high);
        }
        
        return count;
    }
    
    private void backtrack(String solution, int n, String low, String high)
    {
        if (solution.length() == n)
        {
            if (n > 1 && solution.charAt(0) == '0')
            {
                return;
            } 
            
            if ((solution.length() == low.length() && solution.compareTo(low) < 0) || 
         		   (solution.length() == high.length() && solution.compareTo(high) > 0)) 
            {
            	return;
            }
            
            count++;
            return;
        } 
        
        if (solution.length() == 0 && n % 2 == 1)
        {
            for (char c : single)
            {
                backtrack(solution + c, n, low, high);
            }
        } 
        else 
        {
            for (int i = 0; i < pair1.length; i++)
            {
                backtrack(pair1[i] + solution + pair2[i], n, low, high);
            }
        }
    }

    
    
    
    
    /*****************************************************************************/
    public static void main(String[] args){
    	Q248_Strobogrammatic_Number_III t = new Q248_Strobogrammatic_Number_III();
    	
    	System.out.println(t.strobogrammaticInRange("50", "100"));
    }
}
