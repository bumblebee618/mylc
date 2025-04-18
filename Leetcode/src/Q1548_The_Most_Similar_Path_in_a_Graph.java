import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly 3 upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).
You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
You need to return the order of the nodes in the path with the minimum edit distance, The path should be of the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
The edit distance is defined as follows:

Follow-up: If each node can be visited only once in the path, What should you change in your solution?
 
Example 1:

Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
Output: [0,2,4,2]
Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
[0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
[0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
[0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
Example 2:

Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
Output: [0,1,0,1,0,1,0,1]
Explanation: Any path in this graph has edit distance = 8 with targetPath.
Example 3:

Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
Output: [3,4,5,4,3,2,1]
Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 
Constraints:
	• 2 <= n <= 100
	• m == roads.length
	• n - 1 <= m <= (n * (n - 1) / 2)
	• 0 <= ai, bi <= n - 1
	• ai != bi 
	• The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
	• names.length == n
	• names[i].length == 3
	• names[i] consists of upper-case English letters.
	• There can be two cities with the same name.
	• 1 <= targetPath.length <= 100
	• targetPath[i].length == 3
	• targetPath[i] consists of upper-case English letters.

 */
public class Q1548_The_Most_Similar_Path_in_a_Graph 
{
	public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) 
    {
        List<Integer> result = new ArrayList<>();
        
        if (n <= 0 || roads == null || roads.length == 0 || roads[0].length != 2 || names == null || names.length != n || targetPath == null || targetPath.length == 0)
        {
            return result;
        }
        
        int[] target = findTargetPathInInteger(names, targetPath);
        Set<Integer>[] graph = new Set[n];
        
        for (int[] road : roads)
        {
            if (graph[road[0]] == null)
            {
                graph[road[0]] = new HashSet<>();
            }
            
            graph[road[0]].add(road[1]);
            
            if (graph[road[1]] == null)
            {
                graph[road[1]] = new HashSet<>();
            }
            
            graph[road[1]].add(road[0]);
        }
        
        Node[][] dp = new Node[target.length][n];
        
        for (int i = 0; i < dp.length; i++)
        {
            for (int node = 0; node < dp[i].length; node++)
            {
                if (i == 0)
                {
                    dp[i][node] = (node == target[i]) ? new Node(0, 0) : new Node(1, 0);
                }
                else
                {
                    dp[i][node] = new Node(i+1, -1);
                    
                    if (graph[node] == null)
                    {
                        continue;
                    }
                    
                    int curEditDist = (node == target[i]) ? 0 : 1;
                    
                    for (int prev : graph[node])
                    {
                    	// System.out.println(String.format("i=%s, prev=%s, cur=%s, prev dist=%s", i, prev, node, dp[i-1][prev].editDist));
                    	
                        if (dp[i-1][prev].prevNode != -1 
                        	&& dp[i][node].editDist > dp[i-1][prev].editDist + curEditDist)
                        {
                            dp[i][node].editDist = dp[i-1][prev].editDist + curEditDist;
                            dp[i][node].prevNode = prev;
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < dp.length; i++)
        {
            for (int j = 0; j < dp[i].length; j++)
            {
            	System.out.print(String.format("[%s, %s], ", dp[i][j].editDist, dp[i][j].prevNode));
            }
            
            System.out.println();
        }
        
        
        int minPath = Integer.MAX_VALUE;
        int node = -1;
        
        for (int i = 0; i < n; i++)
        {
            if (minPath > dp[target.length-1][i].editDist)
            {
                minPath = dp[target.length-1][i].editDist;
                node = i;
            }
        }
        
        for (int i = target.length-1; i >= 0; i--)
        {
            result.add(0, (node != -1) ? node : 0);
            node = (node != -1) ? dp[i][node].prevNode : -1;
        }
        
        return result;
    }
    
    private int[] findTargetPathInInteger(String[] names, String[] targetPath)
    {
        int[] target = new int[targetPath.length];
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < names.length; i++)
        {
            map.put(names[i], i);
        }
        
        for (int i = 0; i < targetPath.length; i++)
        {
            target[i] = map.getOrDefault(targetPath[i], -1);
        }
        
        return target;
    }
    
    class Node 
    {
        public int editDist;
        public int prevNode;
        
        public Node (int editDist, int prevNode)
        {
            this.editDist = editDist;
            this.prevNode = prevNode;
        }
    }

    
    
    
    
    public static void main(String[] args)
    {
    	Q1548_The_Most_Similar_Path_in_a_Graph test = new Q1548_The_Most_Similar_Path_in_a_Graph();
    	int[][] roads = {{0,2},{0,3},{1,2},{1,3},{1,4},{2,4}};
    	String[] names = {"ATL","PEK","LAX","DXB","HND"};
    	String[] targetPath = {"ATL","DXB","HND","LAX"};
    	List<Integer> result = test.mostSimilar(5, roads, names, targetPath);
    	
    	for (int node : result)
    	{
    		System.out.print(node + ", ");
    	}
    }
}
