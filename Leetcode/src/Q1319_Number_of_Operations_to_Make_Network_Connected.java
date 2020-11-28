import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.

Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1. 

 

Example 1:



Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:



Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
Example 4:

Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0
 

Constraints:

1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
There are no repeated connections.
No two computers are connected by more than one cable.
 */

public class Q1319_Number_of_Operations_to_Make_Network_Connected 
{
	public int makeConnected(int n, int[][] connections) 
    {
        if (n <= 0 || connections == null || connections.length == 0 || connections[0].length == 0)
        {
            return 0;
        }
        
        UnionFind uf = new UnionFind(n);
        int availableCable = 0;
        int part = n;
        
        for (int[] connection : connections)
        {   
            if (connection[0] == connection[1])
            {
                continue;
            }
            
            int parent1 = uf.find(connection[0]);
            int parent2 = uf.find(connection[1]);
            
            if (parent1 != parent2)
            {
                uf.union(connection[0], connection[1]);
                part--;
            }
            else
            {
                availableCable++;
            }
        }
        
        int needCable = part - 1;
        return availableCable >= needCable ? needCable : -1;
    }
    
    class UnionFind
    {
        private Map<Integer, Integer> father;
        
        public UnionFind(int n)
        {
            father = new HashMap<>();
            
            for (int i = 0; i < n; i++)
            {
                father.put(i, i);
            }
        }
        
        public int find(int id)
        {   
            int parent = father.get(id);
            
            while (parent != father.get(parent))
            {
                parent = father.get(parent);
            }
            
            while (id != father.get(id))
            {
                int temp = father.get(id);
                father.put(id, parent);
                id = temp;
            }
            
            return parent;
        }
        
        public void union(int id1, int id2)
        {
            int parent1 = find(id1);
            int parent2 = find(id2);
            
            if (parent1 != parent2)
            {
                father.put(parent1, parent2);
            }
        }
        
        public int getSize()
        {
            return father.size();
        }
    }
}
