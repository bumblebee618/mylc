import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

 

Example 1:



Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: 
A longest path of the tree is the path 1 - 0 - 2.
Example 2:



Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: 
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 

Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
 */
public class Q1245_Tree_Diameter 
{	
	// solution 1:
	private int diameter = 0;
    
    public int treeDiameter(int[][] edges) 
    {
        if (edges == null || edges.length == 0 || edges[0].length != 2)
        {
            return 0;
        }
        
        int n = edges.length + 1;
        Set<Integer>[] graph = new Set[n];
        
        for (int i = 0; i < n; ++i) 
        {
            graph[i] = new HashSet<>();
        }
        
        for (int[] edge : edges) 
        {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        dfs(0, -1, graph);
        return diameter;
    }
    
    // return children's depth
    private int dfs(int root, int parent, Set<Integer>[] graph) 
    {
    	// path is not need to go through root, pick two longest path
        int maxDepth1st = 0, maxDepth2nd = 0;
        
        for (int child : graph[root]) 
        {
            // Only one way from root node to child node, don't allow child node go to root node again!
            if (child == parent) 
            {
                continue; 
            }
            
            int childDepth = dfs(child, root, graph);
            
            if (childDepth > maxDepth1st) 
            {
                maxDepth2nd = maxDepth1st;
                maxDepth1st = childDepth;
            } 
            else if (childDepth > maxDepth2nd) 
            {
                maxDepth2nd = childDepth;
            }
        }
        
        // the number of nodes in the longest path
        int longestPathThroughRoot = maxDepth1st + maxDepth2nd + 1; 
        
        // diameter = number of edges = number of nodes - 1
        diameter = Math.max(diameter, longestPathThroughRoot - 1); 
        return maxDepth1st + 1;
    }
	
	
	
	// solution 2: dfs
	private int result = 0;
    
    public int treeDiameter2(int[][] edges) 
    {
        if (edges == null || edges.length == 0 || edges[0].length != 2)
        {
            return 0;
        }
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges)
        {
            graph.computeIfAbsent(edge[0], x -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new HashSet<>()).add(edge[0]);
        }
        
        List<Integer> list = new LinkedList<>();
        
        for (int key : graph.keySet())
        {
        	System.out.println("size = " + graph.keySet().size());
        	
            if (graph.get(key).size() == 1)
            {
                list.add(key);
            }
        }
        
        for (int node : list)
        {
        	System.out.println("node = " + node);
            dfs(node, graph, new HashSet<Integer>(), 0);
        }
        
        return result;
    }
    
    private void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited, int depth)
    {
    	System.out.println("depth = " + depth);
        result = Math.max(result, depth);
        visited.add(node);
        
        for (int next : graph.get(node))
        {
            if (!visited.contains(next))
            {
                dfs(next, graph, visited, depth+1);
            }
        }
    }

    
    
    
	
	
	public static void main(String[] args)
	{
		Q1245_Tree_Diameter test = new Q1245_Tree_Diameter();
		int[][] edge = {{0,1},{1,2},{2,3},{1,4},{4,5}};
		System.out.println(test.treeDiameter(edge));
	}
}