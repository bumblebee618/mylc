import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000
 

Note：

1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
If A.length > 1, then A[0] != 0
 */
public class Q989_Add_to_Array_Form_of_Integer {
	public List<Integer> addToArrayForm(int[] A, int K) 
    {
        List<Integer> result = new LinkedList<>();
        
        if (A == null || A.length == 0 || K < 0)
        {
            return result;
        }
        
        char[] digits2 = Integer.toString(K).toCharArray();
        int index1 = A.length-1, index2 = digits2.length-1;
        int sum = 0, flag = 0;
        
        while (index1 >= 0 || index2 >= 0)
        {
            if (index1 >= 0 && index2 >= 0)
            {
                sum = A[index1--] + (digits2[index2--] - '0') + flag;
            }
            else if (index1 >= 0)
            {
                sum = A[index1--] + flag;
            }
            else
            {
                sum = (digits2[index2--] - '0') + flag;
            }
            
            flag = sum / 10;
            result.add(0, sum % 10);
        }
        
        if (flag > 0)
        {
            result.add(0, flag);
        }
        
        return result;
    }
}
