/***
 * 
 * @author jackie
 * 
 * Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
Example 2:
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
Note:
The n and k are in the range 1 <= k < n <= 104.
 */
public class Q667_Beautiful_Arrangement_II 
{
	public int[] constructArray(int n, int k) 
    {
        if (n <= 0 || k <= 0 || k >= n)
        {
            return new int[0];
        }
        
        int[] result = new int[n];
        int index = 0;
        
        for (int num = 1; num < n-k; num++)
        {
            result[index++] = num;
        }
        
        for (int i = 0; i <= k; i++)
        {
            result[index++] = (i % 2 == 0) ? (n-k + i/2) : (n - i/2);
        }
        
        return result;
    }
}
