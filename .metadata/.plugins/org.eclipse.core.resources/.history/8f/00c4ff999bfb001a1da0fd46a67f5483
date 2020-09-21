import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 *
 */
public class Q973_K_Closest_Points_to_Origin {
	public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || points[0].length == 0 || k <= 0)
        {
            return new int[0][0];
        }
        
        int[][] result = new int[k][2];
        
        Queue<Tuple> maxHeap = new PriorityQueue<>(k+1, new Comparator<Tuple>(){
            @Override
            public int compare(Tuple t1, Tuple t2)
            {
                return t2.distance - t1.distance;
            }
        });
        
        for (int[] point : points)
        {
            maxHeap.offer(new Tuple(point));
            
            if (maxHeap.size() > k)
            {
                maxHeap.poll();
            }
        }
        
        int index = 0;
        
        while (!maxHeap.isEmpty())
        {
            Tuple t = maxHeap.poll();
            result[index++] = t.point;
        }
        
        return result;
    }
    
    class Tuple
    {
        int[] point;
        int distance;
        
        public Tuple(int[] point)
        {
            this.point = point;
            distance = point[0]*point[0] + point[1]*point[1];
        }
    }
}
