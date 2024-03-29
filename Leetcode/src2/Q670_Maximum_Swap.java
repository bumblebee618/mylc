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
	// solution 1: O(nlogn)
	public int maximumSwap(int num) {
        if (num < 0) {
            return num;
        }
        
        char[] digits = Integer.toString(num).toCharArray();
        int[] array = new int[digits.length];
        Arrays.fill(array, -1);
        Queue<Integer> heap = new PriorityQueue<>((a, b) -> digits[b] - digits[a]);
        
        for (int i = digits.length-1; i >= 0; i--) {
            if (!heap.isEmpty() && digits[heap.peek()] > digits[i]) {
                array[i] = heap.peek();
            }
            
            heap.offer(i);
        }
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                char temp = digits[i];
                digits[i] = digits[array[i]];
                digits[array[i]] = temp;
                break;
            }
        }
        
        return Integer.parseInt(new String(digits));
    }
	
	
	public int maximumSwap2(int num) 
    {
        if (num <= 0)
        {
            return 0;
        }
        
        char[] digits = Integer.toString(num).toCharArray();
        int[] lastPos = new int[10];
        
        for (int i = 0; i < digits.length; i++)
        {
            lastPos[digits[i] - '0'] = i;
        }
        
        for (int i = 0; i < digits.length; i++)
        {
            for (int j = 9; j > digits[i] - '0'; j--)
            {
                if (lastPos[j] > i)
                {
                    char temp = digits[i];
                    digits[i] = digits[lastPos[j]];
                    digits[lastPos[j]] = temp;
                    return Integer.parseInt(new String(digits));
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
