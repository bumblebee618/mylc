import java.util.*;
/**
 * 
 There are a number of spherical balloons spread in two-dimensional space. For each balloon, 
 provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, 
 col-coordinates don't matter and hence the row-coordinates of start and end of the diameter suffice. 
 Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the row-axis. 
A balloon with xstart and xend bursts by an arrow shot at row if xstart ≤ row ≤ xend. 
There is no limit to the number of arrows that can be shot. 
An arrow once shot keeps travelling up infinitely.
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at row = 6 (bursting the balloons [2,8] and [1,6]) 
and another arrow at row = 11 (bursting the other two balloons).
 *
 */
public class Q452_Minimum_Number_of_Arrows_to_Burst_Balloons {
	// 类似题435
	public int findMinArrowShots(int[][] points) 
	{
        if (points == null || points.length == 0 || points[0].length == 0) 
        {
            return 0;
        }
        
        // cannot use a[1] - b[1] which will cause the overflow, for example: 
        // {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        Arrays.sort(points, (a, b) -> {
        	if (a[1] < b[1])
        	{
        		return -1;
        	}
        	else if (a[1] > b[1])
        	{
        		return 1;
        	}
        	else
        	{
        		return 0;
        	}
        });
        
        int minArrow = 1;
        int hitPos = points[0][1];
        
        for (int[] point : points) 
        {
            if (hitPos >= point[0]) 
            {
                hitPos = Math.min(hitPos, point[1]);
            } 
            else 
            {
                minArrow++;
                hitPos = point[1];
            }
        }
        
        return minArrow;
    }
	
	
	
	
	
	
	
	/************************************* main *************************************/
	
	public static void main(String[] args)
	{
		Q452_Minimum_Number_of_Arrows_to_Burst_Balloons test = new Q452_Minimum_Number_of_Arrows_to_Burst_Balloons();
		int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
		int[][] points2 = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
		int result = test.findMinArrowShots(points2);
		System.out.print(result);
	}
}
