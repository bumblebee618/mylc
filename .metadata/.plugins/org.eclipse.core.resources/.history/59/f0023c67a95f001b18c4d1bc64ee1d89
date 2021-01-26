/***
 * 
 * @author jackie
 * 
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
 */
public class Q670_Maximum_Swap {
	public int maximumSwap(int num) {
        if (num <= 0)
        {
            return 0;
        }
        
        char[] digits = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        
        for (int i = 0; i < digits.length; i++) 
        {
            last[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) 
        {
            for (int d = 9; d > digits[i] - '0'; d--) 
            {
                if (last[d] > i) 
                {
                    char tmp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        
        return num;
    }
	
	
	// solution 2:
	public int maximumSwap2(int num) {
        if (num <= 0)
        {
            return 0;
        }
        
        char[] digits = Integer.toString(num).toCharArray();
        int[] index = new int[digits.length];
        int maxValueIndex = -1;
        
        for (int i = digits.length-1; i >= 0; i--)
        {
            if (maxValueIndex == -1 || digits[maxValueIndex] < digits[i])
            {
                maxValueIndex = i;
                index[i] = -1;
            }
            else if (digits[maxValueIndex] == digits[i])
            {
                index[i] = -1;
            }
            else
            {
                index[i] = maxValueIndex;
            }
        }
        
        for (int i = 0; i < digits.length; i++)
        {
            if (index[i] != -1)
            {
                char temp = digits[i];
                digits[i] = digits[index[i]];
                digits[index[i]] = temp;
                return Integer.parseInt(new String(digits));
            }
        }
        
        return num;
    }
}
