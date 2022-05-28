import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.

Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.

You may return the answer in any order.

 

Example 1:

Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

Constraints:

2 <= n <= 9
0 <= k <= 9
 */

public class Q967_Numbers_With_Same_Consecutive_Differences {
	public int[] numsSameConsecDiff(int n, int k) {
        if (n < 2 || n > 9 || k < 0 || k > 9) {
            return new int[0];
        }
        
        List<Integer> list = new LinkedList<>();
        backtrack(n, "", k, list);
        int[] result = new int[list.size()];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    private void backtrack(int n, String solution, int k, List<Integer> list) {
        if (solution.length() == n) {
            list.add(Integer.parseInt(solution));
            return;
        }
        
        if (solution.length() == 0) {
            for (char i = '1'; i <= '9'; i++) {
                backtrack(n, String.valueOf(i), k, list);
            }
        } else {
            char last = solution.charAt(solution.length()-1);
            
            for (char i = '0'; i <= '9'; i++) {
                if (Math.abs(last-i) == k) {
                    backtrack(n, solution + i, k, list);
                }
            }
        }
    }
}
