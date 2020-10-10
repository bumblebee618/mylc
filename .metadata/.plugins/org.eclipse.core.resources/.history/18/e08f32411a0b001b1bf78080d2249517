import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
/********
 * 
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:
	The number of buildings in any input list is guaranteed to be in the range [0, 10000].
	The input list is already sorted in ascending order by the left x position Li.
	The output list must be sorted by the x position.
	There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 * 
 * */


public class Q218_The_Skyline_Problem {
	/******************************************************************
	 * (1). 使用扫描线方法按position先后顺序记录下对应node（heap）
	 * (2). 使用currentMaxHeight (queue) 存放当前位置最大的高度
	 * (3). 注意实时处理同一position上的高度值在currentMaxHeight里的维护状况
	 *
	 ******************************************************************/
	//test case: [[0,2,3],[2,5,3]]两段高度相同
	//  正确结果：[[0,3],[2,3],[5,0]]
	//  错误结果：[[0,3],[5,0]]
	
	// solution 1: using scan line, time is O(nlogn), space is O(n)
	public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0)
        {
            return result;
        }
        
        int size = buildings.length;   
        
        Queue<Tuple> heap = new PriorityQueue<>(size * 2, new Comparator<Tuple>() {
            @Override
            public int compare(Tuple p1, Tuple p2) 
            {
                if (p1.index != p2.index) 
                {
                    return p1.index - p2.index;
                } 
                else if (p1.isStart == false && p2.isStart == true) 
                {
                    return -1;
                }
                else if (p1.isStart == true && p2.isStart == false) 
                {
                    return 1;
                } 
                else 
                {
                    return 0;
                }
            }
        });

        Queue<Integer> curHeight = new PriorityQueue<>(size, (a, b) -> b - a);   
        
        for (int[] building : buildings) {
            heap.offer(new Tuple(building[0], building[2], true));
            heap.offer(new Tuple(building[1], building[2], false));
        }
        
        while (!heap.isEmpty())
        {
            Tuple node = heap.poll();
            
            if (node.isStart)
            {
                curHeight.offer(node.height);
            }
            else
            {
                curHeight.remove(node.height);
            }
            
            while (!heap.isEmpty() && heap.peek().index == node.index)
            {
                Tuple nextNode = heap.poll();
            
                if (nextNode.isStart)
                {
                    curHeight.offer(nextNode.height);
                }
                else
                {
                    curHeight.remove(nextNode.height);
                }
            }
            
            int height = curHeight.isEmpty() ? 0 : curHeight.peek();
            
            if (result.size() > 0 && result.get(result.size()-1).get(1) == height)
            {
                continue;
            }
            
            List<Integer> list = new ArrayList<>();
            list.add(node.index);
            list.add(height);
            result.add(list);
        }
        
        return result;
    }
    
    class Tuple
    {
        public int index;
        public int height;
        public boolean isStart;
        
        public Tuple(int index, int height, boolean isStart)
        {
            this.index = index;
            this.height = height;
            this.isStart = isStart;
        }
    }


	
	
	
	
	// solution 2: using TreeMap
	public List<int[]> getSkyline2(int[][] buildings) {
		TreeMap<Integer, Integer> availableHeights = new TreeMap<>();
		List<int[]> res = new ArrayList<>(buildings.length);

		int len = buildings.length;
		if (len == 0) {
			return res;
		}

		Edge[] edges = new Edge[len * 2];
		for (int i = 0; i < len; ++i) {
			edges[i * 2] = new Edge(buildings[i][0], buildings[i][2], true);
			edges[1 + (i * 2)] = new Edge(buildings[i][1], buildings[i][2], false);
		}

		Arrays.sort(edges);
		int currentHeight = 0;
		availableHeights.put(0, 1);
		
		for (int i = 0, j; i < len * 2; i = j) {
			for (j = i; j < len * 2 && edges[i].x == edges[j].x; ++j) { // 同一个pos的结点需要放在一起处理
				Edge event = edges[j];
				if (!event.isStart) {
					int counter = availableHeights.get(event.height);
					if (counter == 1) {
						availableHeights.remove(event.height);
					} else {
						availableHeights.put(event.height, counter - 1);
					}
				} else {
					Integer counter = availableHeights.get(event.height);
					if (counter == null) {
						availableHeights.put(event.height, 1);
					} else {
						availableHeights.put(event.height, counter + 1);
					}
				}
			}

			int x = edges[i].x;
			int height = availableHeights.lastKey();
			if (height != currentHeight) {
				res.add(new int[] { x, height });
				currentHeight = height;
			}
		}
		return res;
	}

	class Edge implements Comparable<Edge> {
		int x, height;
		boolean isStart;

		public Edge(int a, int b, boolean c) {
			x = a;
			height = b;
			isStart = c;
		}

		@Override
		public int compareTo(Edge that) {
			return (x != that.x) ? x - that.x : Boolean.compare(that.isStart, isStart);
		}
	}
	
	
	
	
	
	
	
	
	

	
	
	/************************* main function ***************************/

	public static void main(String[] args) {
		Q218_The_Skyline_Problem t = new Q218_The_Skyline_Problem();
		int[][] buildings = { 
			{1, 3, 3},
			{2, 4, 4},
			{5, 6, 1}
		};

		/***
		List<int[]> res = t.getSkyline(buildings);

		for (int i = 0; i < res.size(); ++i) {
			System.out.print("[" + res.get(i)[0] + ", " + res.get(i)[1] + "], ");
		}
		***/
	}
}
