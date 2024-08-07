import java.util.*;

/***
 * 
 * @author jackie
 * 
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: 
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000

Accepted
18.4K
Submissions
40.2K
 */
public class Q834_Sum_of_Distances_in_Tree 
{
	// solution 1: time is O(N), space is O(N)
	private int[] result, nodeCount;
    private Set<Integer>[] graph;
    private int N;
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) 
    {
        if (N <= 0)
        {
            return new int[0];           
        }
        else if (edges == null || edges.length == 0 || edges[0].length != 2)
        {
            return new int[N];
        }
        
        this.N = N;
        graph = new Set[N];
        result = new int[N];
        nodeCount = new int[N];
        Arrays.fill(nodeCount, 1);

        // build graph
        for (int i = 0; i < N; ++i)
        {
            graph[i] = new HashSet<>();
        }
        
        for (int[] edge: edges) 
        {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        // 自底向上后续遍历后，parent有其所有子节点的distance (但不含父节点distance)
        postOrder(0, -1);
        
        // 自后续遍历后，只有root节点有正确的distance minValue，因此需要前序遍历
        preOrder(0, -1);
        return result;
    }

    private void postOrder(int node, int parent) 
    {
        for (int child: graph[node])
        {
            if (child != parent) 
            {
            	// 先遍历子树
                postOrder(child, node);
                nodeCount[node] += nodeCount[child];
                result[node] += result[child] + nodeCount[child];
            }
        }
    }

    private void preOrder(int node, int parent) 
    {
        for (int child: graph[node])
        {
            if (child != parent) 
            {
            	// 从parent角度update children的result值
                result[child] = result[node] - nodeCount[child] + (N - nodeCount[child]);
                preOrder(child, node);
            }
        }
    }
    
    
    
    // solution 2: time O(N^2) and space is O(N^2)
    public int[] sumOfDistancesInTree2(int N, int[][] edges) 
    {
        if (N <= 0)
        {
            return new int[0];           
        }
        else if (edges == null || edges.length == 0 || edges[0].length != 2)
        {
            return new int[N];
        }
        
        Set<Integer>[] graph = new Set[N];
        int[][] memo = new int[N][N];
        int[] result = new int[N];
        
        for (int i = 0; i < N; i++)
        {
            graph[i] = new HashSet<>();
        }
        
        for (int[] edge : edges)
        {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            memo[edge[0]][edge[1]] = 1;
            memo[edge[1]][edge[0]] = 1;
        }
        
        for (int i = 0; i < N; i++)
        {   
            for (int j = 0; j < N; j++)
            {
                if (i == j)
                {
                    continue;
                }
                
                result[i] += bfs(graph, memo, i, j);
            }
        }
        
        return result;
    }
    
    private int bfs(Set<Integer>[] graph, int[][] memo, int start, int end)
    {
        if (memo[start][end] > 0)
        {
            return memo[start][end];
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int distance = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            distance++;
            
            for (int i = 0; i < size; i++)
            {
                int curNode = queue.poll();
                
                if (curNode == end)
                {
                    return memo[start][end];
                }
                
                for (int next : graph[curNode])
                {
                    if (memo[start][next] == 0)
                	{
                		memo[start][next] = memo[next][start] = distance;
                	}
                    
                    memo[curNode][next] = memo[next][curNode] = 1;
                    
                    if (!visited.contains(next))
                    {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        
        return 0;
    }
    
    
    
    
    
    
    /************************************ main ************************************/ 
    
    public static void main(String[] args)
    {
    	Q834_Sum_of_Distances_in_Tree test = new Q834_Sum_of_Distances_in_Tree();
    	int[][] edges1 = {{0,1},{0,2},{2,3},{2,4},{2,5}};
    	int N1 = 6;
    	
    	int[][] edges2 = {{1,2},{2,0},{0,3}};
    	int N2 = 4;
    	
    	int[] result = test.sumOfDistancesInTree(N2, edges2);
    	
    	for (int sum : result)
    	{
    		System.out.print(sum + ", ");
    	}
    }
}
