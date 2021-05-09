import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

 

Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 

Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
 */

public class Q1854_Maximum_Population_Year {
	public int maximumPopulation(int[][] logs) 
    {
        if (logs == null || logs.length == 0 || logs[0].length != 2)
        {
            return -1;
        }
        
        int curP = 0;
        int maxP = 0;
        int maxY = -1;
        
        Queue<Node> heap = new PriorityQueue<>((a, b) -> {
            if (a.year != b.year)
            {
                return a.year - b.year;
            }
            else if (a.isDeath)
            {
                return -1;
            }
            else if (b.isDeath)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        });
        
        for (int[] log : logs)
        {
        	heap.offer(new Node(log[0], false));
        	heap.offer(new Node(log[1], true));
        }
        
        while (!heap.isEmpty())
        {
        	Node node = heap.poll();
        	
            if (!node.isDeath)
            {
                curP++;
            }
            else
            {
                curP--;
            }
            
            while (!heap.isEmpty() && heap.peek().year == node.year)
            {
            	Node tmp = heap.poll();
            	
                if (!tmp.isDeath)
                {
                    curP++;
                }
                else
                {
                    curP--;
                }
            }
            
            if (curP > maxP)
            {
            	maxP = curP;
            	maxY = node.year;
            }
        }
        
        return maxY;
    }
    
    class Node
    {
        public int year;
        public boolean isDeath;
        
        public Node(int y, boolean d)
        {
            year = y;
            isDeath = d;
        }
    }
}
