import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 */
public class Q685_Redundant_Connection_II {
	public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] result = {-1, -1};
        
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return result;
        }
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            if (bfs(graph, 1, edge[0]) && bfs(graph, 1, edge[1])) {
                return edge;
            }
            
            graph.computeIfAbsent(edge[0], x -> new HashSet<Integer>()).add(edge[1]);
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
    
    
    
    public static void main(String[] args) {
    	Q685_Redundant_Connection_II test = new Q685_Redundant_Connection_II();
    	int[][] edges = {{1,2}, {2, 3}};
    	test.findRedundantDirectedConnection(edges);
    }
}
