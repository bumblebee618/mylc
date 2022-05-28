import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***
 * 
 * @author jackie
 * 
 * We are given a list of (axis-aligned) rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] , where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:


Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: rectangles = [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 1018 modulo (109 + 7), which is (109)2 = (-7)2 = 49.
 

Constraints:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 109
The total area covered by all rectangles will never exceed 263 - 1 and thus will fit in a 64-bit signed integer.
 */

public class Q850_Rectangle_Area_II {
	private int mod = 1_000_000_007;
	
    public int rectangleArea(int[][] rectangles) {
        long result = 0;
        
        for (int[] r: rectangles) {
        	result += areaOf(r[0], r[1], r[2], r[3]);
        }
        
        for (int i = 0; i < rectangles.length; i++) {
            long overlap = overlap(rectangles, rectangles[i], i+1);
            result = (result-overlap+mod) % mod;
        }
        
        return (int) result;
    }
    
    private long areaOf(int x1, int y1, int x2, int y2) {
        return (long) (x2-x1) * (y2-y1);
    }
    
    private long overlap(int[][] recs, int[] r1, int idx) {
        if (idx == recs.length) {
        	return 0;
        }
        
        int[] r2 = recs[idx++];
        int left = Math.max(r1[0], r2[0]), 
        	right = Math.min(r1[2], r2[2]), 
        	down = Math.max(r1[1], r2[1]), 
        	up = Math.min(r1[3], r2[3]);
        
        if (left >= right || up <= down) {
        	return overlap(recs, r1, idx);
        }
        
        long result = areaOf(left, down, right, up);
        
        if (r1[0] < r2[0]) result = (result+overlap(recs, new int[]{r1[0], r1[1], r2[0], r1[3]}, idx)) % mod;
        if (r2[2] < r1[2]) result = (result+overlap(recs, new int[]{r2[2], r1[1], r1[2], r1[3]}, idx)) % mod;
        if (r1[1] < r2[1]) result = (result+overlap(recs, new int[]{left, r1[1], right, r2[1]}, idx)) % mod;
        if (r2[3] < r1[3]) result = (result+overlap(recs, new int[]{left, r2[3], right, r1[3]}, idx)) % mod;
        return result;
    }
}
