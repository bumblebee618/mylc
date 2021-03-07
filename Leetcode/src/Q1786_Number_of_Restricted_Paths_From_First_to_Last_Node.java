import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.

A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.

The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.

Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.

 

Example 1:


Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
Output: 3
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
Example 2:


Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
Output: 1
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 

Constraints:

1 <= n <= 2 * 104
n - 1 <= edges.length <= 4 * 104
edges[i].length == 3
1 <= ui, vi <= n
ui != vi
1 <= weighti <= 105
There is at most one edge between any two nodes.
There is at least one path between any two nodes.
 */

public class Q1786_Number_of_Restricted_Paths_From_First_to_Last_Node
{
	private Map<Integer, Integer>[] graph;
    private long[] dist;
    private int mod = 1_000_000_007;
    private Long[] memo;
    
    public int countRestrictedPaths(int n, int[][] edges) 
    {
        if (edges == null || edges.length == 0 || edges[0].length != 3 || n <= 0)
        {
            return 0;
        }
        
        graph = new Map[n+1];
        
        for (int i = 0; i < graph.length; i++)
        {
            graph[i] = new HashMap<>();
        }
        
        for (int[] edge : edges)
        {
            graph[edge[0]].put(edge[1], edge[2]);
            graph[edge[1]].put(edge[0], edge[2]);
        }
        
        dist = new long[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        
        // bfs
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int node = queue.poll();
                
                for (Map.Entry<Integer, Integer> entry : graph[node].entrySet())
                {
                	int next = entry.getKey();
                	int weight = entry.getValue();
                	
                    if (dist[next] > dist[node] + weight)
                    {
                        dist[next] = dist[node] + weight;
                        queue.offer(next);
                    }
                }
            }
        }
        
        memo = new Long[n+1];
        memo[n] = 1L;
        return (int) search(1, n);
    }
    
    private long search(int start, int end)
    {
    	if (start == end)
    	{
    		return 1;
    	}
    	else if (memo[start] != null)
    	{
    		return memo[start];
    	}
    	
    	long count = 0;
    	
    	for (int next : graph[start].keySet())
    	{
    		if (dist[start] > dist[next])
    		{
    			count = (count + search(next, end)) % mod;
    		}
    	}
    	
    	memo[start] = count;
    	return memo[start];
    }
}
