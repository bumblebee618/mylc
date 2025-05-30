import java.util.*;

/***
 * 
 * @author jackie
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the row and col axes.

If there isn't any rectangle, return 0.

 

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 

Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */
public class Q939_Minimum_Area_Rectangle {
	// solution 1
	public int minAreaRect(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        
        Set<Point> allPoints = new HashSet<>();
        int result = Integer.MAX_VALUE;
        
        for(int[] point : points) {
            allPoints.add(new Point(point[0], point[1]));
        }
        
        /* Assume p1 and p2 are diagonals, check if there are p3, p4 available in given points*/
        for (Point p1 : allPoints) {
            for (Point p2 : allPoints) {
                if (p1.equals(p2) || p1.x == p2.x || p1.y == p2.y) {
                    continue;
                }
                
                Point p3 = new Point(p1.x, p2.y);
                Point p4 = new Point(p2.x, p1.y);
                
                if (allPoints.contains(p3) && allPoints.contains(p4)) {
                    int area = Math.abs(p1.x-p2.x) * Math.abs(p1.y-p2.y);
                    result = Math.min(area, result);
                }
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
    
	
    class Point {
        public int x;
        public int y;
        
        Point(int i, int j) { 
            x = i; 
            y = j; 
        }
        
        @Override
        public boolean equals(Object obj) {
            return ((Point) obj).x == this.x && ((Point) obj).y == this.y;
        }
        
        @Override
        public int hashCode() { 
            return Objects.hash(x, y); 
        }
    }
    
    
    // solution 2:
    public int minAreaRect2(int[][] points) 
    {
        if (points == null || points.length == 0 || points[0].length == 0)
        {
            return 0;
        }
        
        Map<String, int[]> map = new HashMap<>();
        int result = Integer.MAX_VALUE;
        
        for(int[] point : points)
        {
            map.put(String.format("%s,%s", point[0],point[1]), point);
        }
 
        for(int[] p1 : map.values())
        {
            for(int[] p2 : map.values())
            {
                if(p1[0] == p2[0] || p1[1] == p2[1]) 
                {
                    continue;
                }
                
                String p3Str = String.format("%s,%s", p1[0],p2[1]);
                String p4Str = String.format("%s,%s", p2[0],p1[1]);
                
                if(map.containsKey(p3Str) && map.containsKey(p4Str))
                {
                    int area = Math.abs(p1[0]-p2[0]) * Math.abs(p1[1]-p2[1]);
                    result = Math.min(area, result);
                }
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
    
    
    // solution 3
    public int minAreaRect3(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0)
        {
            return 0;
        }
        
        Map<Integer, List<Integer>> rows = new TreeMap();
        
        for (int[] point: points) 
        {
            int x = point[0], y = point[1];
            rows.computeIfAbsent(x, z-> new ArrayList()).add(y);
        }

        int result = Integer.MAX_VALUE;
        Map<Integer, Integer> lastx = new HashMap();
        
        for (int x: rows.keySet()) 
        {
            List<Integer> row = rows.get(x);
            Collections.sort(row);
            
            for (int i = 0; i < row.size(); ++i)
            {
                for (int j = i+1; j < row.size(); ++j) 
                {
                    int y1 = row.get(i), y2 = row.get(j);
                    int code = 40001 * y1 + y2;
                    
                    if (lastx.containsKey(code))
                    {
                        result = Math.min(result, (x - lastx.get(code)) * (y2-y1));
                    }
                    
                    lastx.put(code, x);
                }
            }
        }

        return result < Integer.MAX_VALUE ? result : 0;
    }
}
