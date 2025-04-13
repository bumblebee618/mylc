import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).

Return the largest possible value of num after any number of swaps.

 

Example 1:

Input: num = 1234
Output: 3412
Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
Example 2:

Input: num = 65875
Output: 87655
Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
 

Constraints:

1 <= num <= 109
 * 
 */
public class Q2231_Largest_Number_After_Digit_Swaps_by_Parity {
	public int largestInteger(int num) {
        if (num <= 0) {
            return -1;
        } 

        Queue<Integer> oddHeap = new PriorityQueue<>((a, b) -> b - a); 
        Queue<Integer> evenHeap = new PriorityQueue<>((a, b) -> b - a); 
        char[] digits = Integer.toString(num).toCharArray();

        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i] - '0';

            if (digit % 2 == 0) {
                evenHeap.offer(digit);
            } else {
                oddHeap.offer(digit);
            }
        }
        
        int result = 0;
        for (int i = 0; i < digits.length; i++) {
            if ((digits[i] - '0') % 2 == 0) {
                result = result * 10 + evenHeap.poll();
            } else {
                result = result * 10 + oddHeap.poll();
            }
        }
            
        return result;
    }
}
