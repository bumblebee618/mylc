import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given an undirected graph represented by an integer n, which is the number of nodes, and edges, where edges[i] = [ui, vi] which indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.

The answer to the jth query is the number of pairs of nodes (a, b) that satisfy the following conditions:

a < b
cnt is strictly greater than queries[j], where cnt is the number of edges incident to a or b.
Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.

Note that there can be repeated edges.

 

Example 1:


Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
Output: [6,5]
Explanation: The number of edges incident to at least one of each pair is shown above.
Example 2:

Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
Output: [10,10,9,8,6]
 

Constraints:

2 <= n <= 2 * 104
1 <= edges.length <= 105
1 <= ui, vi <= n
ui != vi
1 <= queries.length <= 20
0 <= queries[j] < edges.length
 * 
 */
public class Q1782_Count_Pairs_Of_Nodes 
{
	public int[] countPairs(int n, int[][] edges, int[] queries) 
    {
    	if (n < 2 
           || edges == null || edges.length == 0 || edges[0].length != 2
           || queries == null || queries.length == 0)
        {
            return new int[0];
        }
    	
        int[] degree = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        
        for (int[] edge : edges)
        {
            degree[--edge[0]]++;
            degree[--edge[1]]++;
            
            if (edge[0] > edge[1]) 
            {
                int temp = edge[0];
                edge[0] = edge[1];
                edge[1] = temp;
            }
            
            // 在set/map 存int[2] {x, y}的方法，很巧，前提必须知道input num的范围！
            // 或者用 string "x_y"来作为key存在set/map里
			// store the number of edges for each node pair
            cnt.put(edge[0] * 20000 + edge[1], cnt.getOrDefault(edge[0] * 20000 + edge[1], 0) + 1);
        }
        
        int[] sortedDegree = degree.clone();
        Arrays.sort(sortedDegree);
        
        int[] result = new int[queries.length];
        
        for (int i = 0; i < queries.length; ++i) 
        {
            int count = 0;      
            
			// using two pointers to find how many potential node pairs
            int left = 0, right = sortedDegree.length - 1;
            
            while (left < right) 
            {
                if (sortedDegree[left] + sortedDegree[right] > queries[i]) 
                {
                    count += (right - left);
                    right--;
                }
                else 
                {
                    left++;
                }
            }
            
			// remove all invalid pairs
            for (int k : cnt.keySet()) 
            {
                int start = k / 20000;
                int end = k % 20000;
                
                if (degree[start] + degree[end] > queries[i] && degree[start] + degree[end] - cnt.get(k) <= queries[i])
                {
                	count--;
                }
            }
            
            result[i] = count;
        }
        
        return result;
    }
}
