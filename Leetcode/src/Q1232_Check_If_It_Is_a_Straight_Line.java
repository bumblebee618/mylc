/***
 * 
 * @author jackie
 * 
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

 

 

Example 1:



Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true
Example 2:



Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false
 

Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
 */
public class Q1232_Check_If_It_Is_a_Straight_Line {
	public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length == 0 || coordinates[0].length == 0)
        {
            return false;
        }
        else if (coordinates.length <= 1 || coordinates[0].length != 2)
        {
            return false;
        }
        
        double slipe = (coordinates[0][1] - coordinates[1][1])*1.0 /(coordinates[0][0] - coordinates[1][0]);
        
        for (int i = 1; i < coordinates.length-1; i++)
        {
            double nextSlipe = (coordinates[i][1] - coordinates[i+1][1])*1.0 /(coordinates[i][0] - coordinates[i+1][0]);
            
            if (slipe != nextSlipe)
            {
                return false;
            }
        }
        
        return true;
    }
}
