import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2

Explanation: 

 

Note:

The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
 */
public class Q554_Brick_Wall {
	public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0)
        {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (List<Integer> list : wall)
        {
            int sum = 0;
            
            for (int i = 0; i < list.size()-1; i++)
            {
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        int result = wall.size();
        
        for (int key : map.keySet())
        {
            result = Math.min(result, wall.size()-map.get(key));
        }
        
        return result;
    }
    
    // Scan line, time complexity is O(nm*log(nm))
    public int leastBricks2(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0)
        {
            return 0;
        }
        
        Queue<Tuple> heap = new PriorityQueue<>(wall.size(), new Comparator<Tuple>(){
            @Override
            public int compare(Tuple t1, Tuple t2)
            {
                if (t1.index != t2.index)
                {
                    return t1.index - t2.index;
                }
                else if (!t1.isStart)
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
        });
        
        int left = 0, right = 0;
        
        for (List<Integer> list : wall)
        {
            int sum = 0;
            
            for (int index : list)
            {
                heap.offer(new Tuple(sum, true));
                heap.offer(new Tuple(sum+index, false));
                sum += index;
                right = Math.max(right, sum);
            }
        }
        
        int count = 0;
        int result = Integer.MAX_VALUE;
        int curIndex = 0;
        
        while (!heap.isEmpty())
        {
            while (!heap.isEmpty() && heap.peek().index <= curIndex)
            {
                Tuple t = heap.poll();
                
                if (t.isStart)
                {
                    count++;
                }
                else
                {
                    count--;
                }
                
                if (curIndex > 0 && curIndex < right && !t.isStart)
                {
                    result = Math.min(result, count);
                }
            }
            
            curIndex = heap.isEmpty() ? curIndex : heap.peek().index;
        }
        
        return result == Integer.MAX_VALUE ? wall.size() : result;
    }
    
    class Tuple
    {
        public int index;
        public boolean isStart;
        
        public Tuple(int index, boolean isStart)
        {
            this.index = index;
            this.isStart = isStart;
        }
    }
}
