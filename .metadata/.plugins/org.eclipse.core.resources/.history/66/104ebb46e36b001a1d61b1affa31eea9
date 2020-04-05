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
            return num;
        }
        
        char[] digits = Integer.toString(num).toCharArray();
        int size = digits.length;
        int[] indexs = new int[size];
        indexs[size-1] = size-1;
        
        for (int i = size-2; i >= 0; i--)
        {
            indexs[i] = (digits[i+1] > digits[indexs[i+1]]) ? i+1 : indexs[i+1];
        }
        
        for (int i = 0; i < size; i++)
        {
            if (digits[i] < digits[indexs[i]])
            {
                char temp = digits[i];
                digits[i] = digits[indexs[i]];
                digits[indexs[i]] = temp;
                break;
            }
        }
        
        return Integer.parseInt(new String(digits));
    }
}
