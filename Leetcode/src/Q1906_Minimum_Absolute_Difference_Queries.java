import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * The minimum absolute difference of an array a is defined as the minimum value of |a[i] - a[j]|, where 0 <= i < j < a.length and a[i] != a[j]. If all elements of a are the same, the minimum absolute difference is -1.

For example, the minimum absolute difference of the array [5,2,3,7,2] is |2 - 3| = 1. Note that it is not 0 because a[i] and a[j] must be different.
You are given an integer array nums and the array queries where queries[i] = [li, ri]. For each query i, compute the minimum absolute difference of the subarray nums[li...ri] containing the elements of nums between the 0-based indices li and ri (inclusive).

Return an array ans where ans[i] is the answer to the ith query.

A subarray is a contiguous sequence of elements in an array.

The value of |x| is defined as:

x if x >= 0.
-x if x < 0.
 

Example 1:

Input: nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
Output: [2,1,4,1]
Explanation: The queries are processed as follows:
- queries[0] = [0,1]: The subarray is [1,3] and the minimum absolute difference is |1-3| = 2.
- queries[1] = [1,2]: The subarray is [3,4] and the minimum absolute difference is |3-4| = 1.
- queries[2] = [2,3]: The subarray is [4,8] and the minimum absolute difference is |4-8| = 4.
- queries[3] = [0,3]: The subarray is [1,3,4,8] and the minimum absolute difference is |3-4| = 1.
Example 2:

Input: nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
Output: [-1,1,1,3]
Explanation: The queries are processed as follows:
- queries[0] = [2,3]: The subarray is [2,2] and the minimum absolute difference is -1 because all the
  elements are the same.
- queries[1] = [0,2]: The subarray is [4,5,2] and the minimum absolute difference is |4-5| = 1.
- queries[2] = [0,5]: The subarray is [4,5,2,2,7,10] and the minimum absolute difference is |4-5| = 1.
- queries[3] = [3,5]: The subarray is [2,7,10] and the minimum absolute difference is |7-10| = 3.
 

Constraints:

2 <= nums.length <= 105
1 <= nums[i] <= 100
1 <= queries.length <= 2 * 104
0 <= li < ri < nums.length
 *
 */

public class Q1906_Minimum_Absolute_Difference_Queries 
{
	// prefix sum, time O(100 * (m+n)), space O(100 * n)
	public int[] minDifference(int[] nums, int[][] queries) 
    {
        if (nums == null || nums.length <= 1 || queries == null || queries.length == 0 || queries[0].length != 2)
        {
            return new int[0];
        }
        
        int[][] count = new int[nums.length][101];
        
        // time O(n * 100)
        for (int i = 0; i < nums.length; i++)
        {
            for (int j = 0; i > 0 && j <= 100 ; j++)
            {
                count[i][j] = count[i-1][j];
            }
            
            count[i][nums[i]]++;
        }
        
        int[] result = new int[queries.length];
        
        // time O(m * 100)
        for (int i = 0; i < queries.length; i++)
        {
            List<Integer> list = new ArrayList<>();
            int min = 100;
            
            int low = queries[i][0];
            int high = queries[i][1];
            
            for (int j = 1; j <= 100; j++)
            {
            	int diff = (low == 0) ? count[high][j] : count[high][j] - count[low-1][j];
            
                if (diff > 0)
                {
                    list.add(j);
                }
            }
            
            for (int j = 0; j < list.size()-1; j++)
            {
                min = Math.min(min, list.get(j+1) - list.get(j));
            }
            
            result[i] = list.size() == 1 ? -1 : min;
        }
        
        return result;
    }
	
	
	
	
	
	/**************************** main ****************************/
	
	public static void main(String[] args)
	{
		Q1906_Minimum_Absolute_Difference_Queries test = new Q1906_Minimum_Absolute_Difference_Queries();
		int[] nums = {1,3,4,8};
		int[][] queries = {{0,1},{1,2},{2,3},{0,3}};
		int[] result = test.minDifference(nums, queries);
		
		for (int num : result)
		{
			System.out.print(num + ", ");
		}
	}
}
