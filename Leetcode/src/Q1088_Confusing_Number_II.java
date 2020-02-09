import java.util.*;

/***
 * 
 * @author jackie
 * 
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.

 

Example 1:

Input: 20
Output: 6
Explanation: 
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation: 
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 

Note:

1 <= N <= 10^9
 */
public class Q1088_Confusing_Number_II {
	private int[] digits = new int[]{0, 1, 6, 8, 9};
    private Map<Character, Character> map = new HashMap<>();

    public int confusingNumberII(int N) {
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
       
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int result = 0;
       
        while (!queue.isEmpty()) 
        {
            int num = queue.poll();
            
            for (int i = 0; i < digits.length; i++) 
            {
                int newNum = num * 10 + digits[i];
                
                if (newNum > N) 
                {
                    return result;
                }
                
                if (newNum != 0) 
                {
                    queue.offer(newNum);
                }
                    
                if (isConfusing(newNum)) 
                {
                    result++;
                }
            }
        }
        
        return -1;
    }
    
    private boolean isConfusing(int num)
    {
        num = Math.abs(num);
        String str = Integer.toString(num);
        int left = 0, right = str.length()-1;
        
        while (left <= right)
        {
            char c1 = str.charAt(left);
            char c2 = str.charAt(right);
            
            if (map.get(c1) != c2)
            {
                return true;
            }
            
            left++;
            right--;
        }
        
        return false;
    }

    
    
    
    public static void main(String[] args)
    {
    	Q1088_Confusing_Number_II test = new Q1088_Confusing_Number_II();
    	System.out.println(test.confusingNumberII(1000000000));
    }
}
