import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
 

Note:

1 <= N <= 10^9

 */
public class Q869_Reordered_Power_of_2 
{
	public boolean reorderedPowerOf2(int N) 
    {
        if (N <= 0)
        {
            return false;
        }
        
        String orgStr = reorderString(N);
        
        for (int i = 0; i < 32; i++)
        {
            String candidate = reorderString((1 << i));
            
            if (orgStr.equals(candidate))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private String reorderString(int num)
    {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }
	
	
	
	
	/*************************** main ***************************/
	
	public static void main(String[] args)
	{
		Q869_Reordered_Power_of_2 test = new Q869_Reordered_Power_of_2();
		int N = 46;
		System.out.println(test.reorderedPowerOf2(N));
	}
}


