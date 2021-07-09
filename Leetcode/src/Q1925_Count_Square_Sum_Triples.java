/***
 * 
 * @author jackie
 * 
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.

Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

 

Example 1:

Input: n = 5
Output: 2
Explanation: The square triples are (3,4,5) and (4,3,5).
Example 2:

Input: n = 10
Output: 4
Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
 

Constraints:

1 <= n <= 250
 */
public class Q1925_Count_Square_Sum_Triples 
{
	public int countTriples(int n) 
    {
        if (n <= 0)
        {
            return 0;
        }
        
        int result = 0;
        
        for (int c = 2; c <= n; c++)
        {
            int a = 1, b = c-1;
            
            while (a <= b)
            {
                int sum = a*a + b*b;
                
                if (sum < c*c)
                {
                    a++;
                }
                else if (sum > c*c)
                {
                    b--;
                }
                else
                {
                    result += (a == b) ? 1 : 2;
                    b--;
                }
            }
        }
        
        return result;
    }
}
