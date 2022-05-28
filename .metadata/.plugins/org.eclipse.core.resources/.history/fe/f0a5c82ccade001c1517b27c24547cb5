import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 */
public class Q684_Redundant_Connection {
	public int[] findRedundantConnection(int[][] edges) {
        int[] result = {-1, -1};
        
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return result;
        }
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            if (bfs(graph, edge[0], edge[1])) {
                return edge;
            }
            
            graph.computeIfAbsent(edge[0], x -> new HashSet<Integer>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new HashSet<Integer>()).add(edge[0]);
        }
        
        return new int[] {-1, -1};
    }
    
    private boolean bfs(Map<Integer, Set<Integer>> graph, int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            if (node == target) {
                return true;
            } 
            
            if (graph.get(node) == null) {
                return false;
            }
            
            for (int next : graph.get(node)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        
        return false;
    }
	
    
    
    
    
    /*************************************************************************/
    
	public static void main(String[] args) {
		Q684_Redundant_Connection test = new Q684_Redundant_Connection();
		int[][] edges = {{1,2}, {1,3}, {2,3}};
		int[] result = test.findRedundantConnection(edges);
		System.out.println(result[0] + ", " + result[1]);
	}
}
