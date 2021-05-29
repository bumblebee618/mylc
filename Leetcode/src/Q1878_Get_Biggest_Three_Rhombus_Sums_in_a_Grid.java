import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.

 

Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
1 <= grid[i][j] <= 105
 */
public class Q1878_Get_Biggest_Three_Rhombus_Sums_in_a_Grid 
{
	public int[] getBiggestThree(int[][] grid) 
	{
		if (grid == null || grid.length == 0 || grid[0].length == 0)
		{
			return new int[] {0};
		}
		
		int row = grid.length, col = grid[0].length;
		int minSide = Math.min(row, col);
		int bounce = (minSide + 1) / 2;
		Integer[][][] left = new Integer[row][col][bounce];
		Integer[][][] right = new Integer[row][col][bounce];
		Queue<Integer> heap = new PriorityQueue<>();
		
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				if (!heap.contains(grid[i][j]))
				{
					heap.offer(grid[i][j]);
					
					if (heap.size() > 3)
					{
						heap.poll();
					}
				}
				
				// left
				int leftSum = 0;
				
				for (int k = 0; k < bounce && i + k < row && j - k >= 0; k++)
				{
					leftSum += grid[i+k][j-k];
					left[i][j][k] = leftSum;
				}
				
				// right
				int rightSum = 0;
				
				for (int k = 0; k < bounce && i + k < row && j + k < col; k++)
				{
					rightSum += grid[i+k][j+k];
					right[i][j][k] = rightSum;
				}
			}
		}
		
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				for (int k = 1; k < bounce; k++)
				{
					if (i + 2 * k >= row 
						|| j - k < 0
						|| j + k >= col)
					{
						break;
					}
					
					int sum = left[i][j][k] 
							+ right[i][j][k]
							+ right[i+k][j-k][k] 
							+ left[i+k][j+k][k]
							- grid[i][j]
							- grid[i+k][j-k]
							- grid[i+k][j+k]
							- grid[i+2*k][j];
					
					if (!heap.contains(sum))
					{
						heap.offer(sum);
						
						if (heap.size() > 3)
						{
							heap.poll();
						}
					}
				}
			}
		}
		
		int size = heap.size();
		int[] result = new int[size];
		
		for (int i = 0; i < size; i++)
		{
			result[size-i-1] = heap.poll();
		}
		
		return result;
    }
}
