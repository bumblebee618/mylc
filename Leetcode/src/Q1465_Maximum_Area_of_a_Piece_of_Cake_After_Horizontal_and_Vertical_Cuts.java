import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

Example 1:



Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
Example 2:



Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 

Constraints:

2 <= h, w <= 10^9
1 <= horizontalCuts.length < min(h, 10^5)
1 <= verticalCuts.length < min(w, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
It is guaranteed that all elements in horizontalCuts are distinct.
It is guaranteed that all elements in verticalCuts are distinct.
 */
public class Q1465_Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts 
{
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) 
    {
        if (h < 0 || w < 0 || horizontalCuts == null || horizontalCuts.length == 0 || verticalCuts == null || verticalCuts.length == 0)
        {
            return 0;
        }
        
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        int maxRow = Math.max(verticalCuts[0] - 0, w - verticalCuts[verticalCuts.length-1]);
        int maxCol = Math.max(horizontalCuts[0] - 0, h - horizontalCuts[horizontalCuts.length-1]);
        int mod = 1_000_000_007;
        
        for (int i = 1; i < verticalCuts.length; i++)
        {
            maxRow = Math.max(maxRow, verticalCuts[i] - verticalCuts[i-1]);
        }
        
        for (int i = 1; i < horizontalCuts.length; i++)
        {
            maxCol = Math.max(maxCol, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        
        return (int) ((long) maxRow * (long) maxCol % mod); 
    }
}
