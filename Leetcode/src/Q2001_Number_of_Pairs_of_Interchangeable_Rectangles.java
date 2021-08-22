import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.

Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).

Return the number of pairs of interchangeable rectangles in rectangles.

 

Example 1:

Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
Output: 6
Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
- Rectangle 0 with rectangle 1: 4/8 == 3/6.
- Rectangle 0 with rectangle 2: 4/8 == 10/20.
- Rectangle 0 with rectangle 3: 4/8 == 15/30.
- Rectangle 1 with rectangle 2: 3/6 == 10/20.
- Rectangle 1 with rectangle 3: 3/6 == 15/30.
- Rectangle 2 with rectangle 3: 10/20 == 15/30.
Example 2:

Input: rectangles = [[4,5],[7,8]]
Output: 0
Explanation: There are no interchangeable pairs of rectangles.
 

Constraints:

n == rectangles.length
1 <= n <= 105
rectangles[i].length == 2
1 <= widthi, heighti <= 105
 */
public class Q2001_Number_of_Pairs_of_Interchangeable_Rectangles {
	public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> map = new HashMap<>();
        
        for (int[] rectangle : rectangles) {
        	double ratio = (double) rectangle[0] / rectangle[1];
        	map.put(ratio, map.getOrDefault(ratio, 0L)+1);
        }
        
        long result = 0;
        
        for (Map.Entry<Double, Long> entry : map.entrySet()) {
        	long count = entry.getValue();
        	
        	if (count > 1) {
        		result += count * (count-1) / 2;
        	}
        }
        
        return result;
    }
}
