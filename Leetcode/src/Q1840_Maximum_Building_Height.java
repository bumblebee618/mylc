import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.

However, there are city restrictions on the heights of the new buildings:

The height of each building must be a non-negative integer.
The height of the first building must be 0.
The height difference between any two adjacent buildings cannot exceed 1.
Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.

It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.

Return the maximum possible height of the tallest building.

 

Example 1:


Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
Example 2:


Input: n = 6, restrictions = []
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
Example 3:


Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
 

Constraints:

2 <= n <= 109
0 <= restrictions.length <= min(n - 1, 105)
2 <= idi <= n
idi is unique.
0 <= maxHeighti <= 109
 */
public class Q1840_Maximum_Building_Height 
{
	public int maxBuilding(int n, int[][] restrictions) 
    {
        if (n <= 0)
        {
            return 0;
        }
        else if (restrictions == null || restrictions.length == 0)
        {
            return n-1;
        }
        
        int size = restrictions.length;
        Arrays.sort(restrictions, (a,b) -> a[0]-b[0]);
        int[] height = new int[size]; 
        height[0] = Math.min(restrictions[0][1], restrictions[0][0]-1);
        
        for (int i = 1; i < size; i++)
        {
            height[i] = Math.min(restrictions[i][1], restrictions[i][0] - restrictions[i-1][0] + height[i-1]);
        }
        
        for (int i = size-2; i >= 0; i--)
        {
            height[i] = Math.min(height[i], restrictions[i+1][0] - restrictions[i][0] + height[i+1]);
        }
        
        int result = Math.max(height[0], n - restrictions[size-1][0] + height[size-1]);
        
        // max height is between [i-1, i]
        // [a, b, c] -> Ha + (b-a) == Hc + (c-b) -> 2b = a + c + Hc - Ha
        for (int i = 1; i < size; i++)
        {
            int tmpIndex = (restrictions[i][0] + restrictions[i-1][0] + height[i] - height[i-1]) / 2;
        	int tmpHeight = height[i-1] + (tmpIndex-restrictions[i-1][0]);
            result = Math.max(result, tmpHeight);
        }
        
        return result;
    }
}
