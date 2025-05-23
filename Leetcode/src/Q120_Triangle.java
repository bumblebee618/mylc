import java.util.ArrayList;
import java.util.List;

/*******
 * 
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.
 * 
 * */


public class Q120_Triangle {
	// solution 1: using DP, 自底向上, time O(n^2), space O(n^2)
	public int minimumTotal(List<List<Integer>> triangle) 
    {
        if (triangle == null || triangle.size() == 0)
        {
            return 0;
        }
        
        int size = triangle.size();
        int[][] dp = new int[size][size];
        
        for (int row = size-1; row >= 0; row--)
        {
            for (int col = 0; col <= row; col++)
            {
                if (row == size-1)
                {
                    dp[row][col] = triangle.get(row).get(col);
                }
                else
                {
                    dp[row][col] = Math.min(dp[row+1][col], dp[row+1][col+1]) + triangle.get(row).get(col);
                }
            }
        }
        
        return dp[0][0];
    }

	
	
	
	// solution 2: follow up, improve the space, time O(n^2), space O(n)
	public int minimumTotal2(List<List<Integer>> triangle) 
    {
		if (triangle == null || triangle.size() == 0)
        {
            return 0;
        }
        
        int size = triangle.size();
        int[][] dp = new int[2][size];
        
        for (int row = size-1; row >= 0; row--)
        {
            for (int col = 0; col <= row; col++)
            {
                dp[row % 2][col] = (row == size-1) 
                    ? triangle.get(row).get(col) 
                    : Math.min(dp[(row+1) % 2][col], dp[(row+1) % 2][col+1]) + triangle.get(row).get(col);
            }
        }
        
        return dp[0][0];
    }

	
	
	
	/******************************** main ********************************/
	
	public static void main(String[] args) 
	{
		Q120_Triangle t = new Q120_Triangle();
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		int[][] nums = { { -1 }, { 2, 3 }, { 1, -1, -3 } };

		for (int i = 0; i < nums.length; ++i) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < nums[i].length; ++j) {
				list.add(nums[i][j]);
			}
			triangle.add(list);
		}

		System.out.println(t.minimumTotal(triangle));
	}
}
