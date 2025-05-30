import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

 

Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.
 

Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
 */
public class Q1135_Connecting_Cities_With_Minimum_Cost {
	public int minimumCost(int n, int[][] connections) {
        if (n <= 0 || connections == null || connections.length == 0 || connections[0].length == 0)
        {
            return -1;
        }
        
        int size = connections.length;
        Queue<int[]> heap = new PriorityQueue<>(size, (a,b)->(a[2]-b[2]));
        Set<Integer> visited = new HashSet<>();
        int cost = 0;
        int partNum = 0;
        UnionFind uf = new UnionFind(n);
        
        for (int[] connection : connections)
        {
            heap.offer(connection);
        }
        
        while (!heap.isEmpty())
        {
            int[] connection = heap.poll();
            int parent1 = uf.find(connection[0]);
            int parent2 = uf.find(connection[1]);
            
            if (parent1 == parent2)
            {
                continue;
            }
            
            if (!visited.contains(connection[0]) && !visited.contains(connection[1]))
            {
                partNum++;
            }
            else if (visited.contains(connection[0]) && visited.contains(connection[1]))
            {
                partNum--;
            }
            
            visited.add(connection[0]);
            visited.add(connection[1]);            
            cost += connection[2];
            uf.union(connection[0], connection[1]);
        }
        
        return partNum == 1 && visited.size() == n ? cost : -1;
    }
    
    class UnionFind
    {
        private Map<Integer, Integer> father;
        
        public UnionFind(int n)
        {
            father = new HashMap<>();
            
            for (int i = 1; i <= n; i++)
            {
                father.put(i, i);
            }
        }
        
        public int find(int x)
        {
            int parent = father.get(x);
            
            while (parent != father.get(parent))
            {
                parent = father.get(parent);
            }
            
            int tempParent = -1;
            int fa = x;
            
            while (fa != father.get(fa))
            {
                tempParent = father.get(fa);
                father.put(fa, parent);
                fa = tempParent;
            }
            
            return parent;
        }
        
        public void union(int x, int y)
        {
            int parentX = father.get(x);
            int parentY = father.get(y);
            
            if (parentX != parentY)
            {
                father.put(parentX, parentY);
            }
        }
    }
}
