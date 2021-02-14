/***
 * 
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

A connected trio is a set of three nodes where there is an edge between every pair of them.

The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.

 

Example 1:


Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
Output: 3
Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
Example 2:


Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
Output: 0
Explanation: There are exactly three trios:
1) [1,4,3] with degree 0.
2) [2,5,6] with degree 2.
3) [5,6,7] with degree 2.
 

Constraints:

2 <= n <= 400
edges[i].length == 2
1 <= edges.length <= n * (n-1) / 2
1 <= ui, vi <= n
ui != vi
There are no repeated edges.
 */

import java.util.*;


public class Q1761_Minimum_Degree_of_a_Connected_Trio_in_a_Graph 
{
	public int minTrioDegree(int n, int[][] edges) 
    {
        if (n <= 0 || edges == null || edges.length == 0 || edges[0].length != 2)
        {
            return 0;
        }
        
        Set<Integer>[] graph = new Set[n+1];
        
        for (int i = 1; i <= n; i++)
        {
            graph[i] = new HashSet<>();
        }
        
        List<int[]> edgeList = new LinkedList<>();
        
        for (int[] edge : edges)
        {
            if (!graph[edge[0]].contains(edge[1]))
            {
                edgeList.add(edge);
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
        }
        
        // Collections.sort(edgeList, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);
        
        int result = Integer.MAX_VALUE;
        
        for (int[] edge : edgeList)
        {
            int first = edge[0];
            int second = edge[1];
 
            for (int candidate : graph[second])
            {
                if (graph[candidate].contains(first))
                {
                    int degree = graph[first].size() 
                    		+ graph[second].size() 
                    		+ graph[candidate].size()
                    		- 6;
                    
                    result = Math.min(result, degree);
                }
            }
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    
	
    
    
    
    /*********************************** main ***********************************/ 
    
    public static void main(String[] args)
    {
    	Q1761_Minimum_Degree_of_a_Connected_Trio_in_a_Graph test = new Q1761_Minimum_Degree_of_a_Connected_Trio_in_a_Graph();
    	int[][] edges = {{1,2},{1,3},{3,2},{4,1},{5,2},{3,6}};
    	int[][] edges2 = {{1,3},{4,1},{4,3},{2,5},{5,6},{6,7},{7,5},{2,6}};
    	int n2 = 7;
    	int[][] edges3 = {{6,5},{4,3},{5,1},{1,4},{2,3},{4,5},{2,6},{1,3}};
    	int n3 = 6;
    	
    	
    	System.out.println(test.minTrioDegree(n2, edges2));
    }
}
