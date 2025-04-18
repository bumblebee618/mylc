import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.

To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.

Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.

An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.

Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.

 

Example 1:



Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
Output: [-1,0,0,1]
Explanation: In the above figure, each node's value is in parentheses.
- Node 0 has no coprime ancestors.
- Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
- Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
  value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
- Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
  closest valid ancestor.
Example 2:



Input: nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
Output: [-1,0,-1,0,0,0,-1]
 

Constraints:

nums.length == n
1 <= nums[i] <= 50
1 <= n <= 105
edges.length == n - 1
edges[j].length == 2
0 <= uj, vj < n
uj != vj
 */
public class Q1766_Tree_of_Coprimes 
{
	private int[] nums;
    private Set<Integer>[] graph;
    
    public int[] getCoprimes(int[] nums, int[][] edges) 
    {
    	if (nums == null || nums.length == 0 
    		|| edges == null || edges.length == 0 || edges[0].length != 2)
    	{
    		return new int[0];
    	}
    	
        this.nums = nums;
        this.graph = new HashSet[nums.length];
        
        // build graph
        for (int i=0; i<nums.length;i++)
        {
            graph[i] = new HashSet<>();
        }
        
        for(int[] e : edges)
        {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
		// dfs the tree from root 0
		int[] result = new int[nums.length];
        
        /*HashMap: value, Node*/
        dfs(0, result, 0, new HashMap<Integer, Node>(), new HashSet<>());
        return result;
    }
    
    private void dfs(
    		int curNode, 
    		int[] result, 
    		int height, 
    		Map<Integer, Node> occurs, 
    		Set<Integer> visited)
    {
        visited.add(curNode);
        int maxHeight = -1;
        int closestAncestor = -1;
        
		// find gcd == 1 between 1 and 50
        for (int i = 1; i <= 50; i++)
        {
            if (occurs.containsKey(i) 
            	&& gcd(nums[curNode], i) == 1 
            	&& maxHeight < occurs.get(i).height)
            {
                maxHeight = occurs.get(i).height;
                closestAncestor = occurs.get(i).id;
            }
        }
        
        result[curNode] = closestAncestor;
        
		// only need to save the latest occur of the value, which is closest ancestor
        // don't change the occurs map, instead create a new map, just like backtrack
        Map<Integer, Node> map = new HashMap<>(occurs);
        map.put(nums[curNode], new Node(curNode, height));
        
        for(int next : graph[curNode])
        {
            if (!visited.contains(next))
            {
                dfs(next, result, height + 1, map, visited);
            }
        }
    }
    
    private int gcd(int a,int b)
    {
        return (b == 0) ? a : gcd(b, a % b);
    }
    
    class Node
    {
    	public int id;
    	public int height;
    	
    	public Node(int id, int height) 
    	{
    		this.id = id;
			this.height = height;
		}
    }
}
