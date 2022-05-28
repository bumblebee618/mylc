import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 

Example 1:



Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
 

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class Q743_Network_Delay_Time 
{
	// solution 1: BFS + DP
	public int networkDelayTime(int[][] times, int n, int k) {
        if (times == null || times.length == 0 || n <= 0) {
            return -1;
        }
        
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new HashMap<>()).put(time[1], time[2]);
        }
        
        if (!graph.containsKey(k)) {
            return -1;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {k, 0});
        
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[k] = 0;
        
        while (!queue.isEmpty()) {
            int[] curNode = queue.poll();
            
            if (!graph.containsKey(curNode[0])) {
                continue;
            }
            
            for (Map.Entry<Integer, Integer> entry : graph.get(curNode[0]).entrySet()) {
                int nextNodeIndex = entry.getKey();
                
                if (costs[nextNodeIndex] > curNode[1] + entry.getValue()) {
                    queue.offer(new int[] {nextNodeIndex, curNode[1] + entry.getValue()});
                    costs[nextNodeIndex] = curNode[1] + entry.getValue();
                }
            }
        }
        
        int delayTime = 0;
        
        for (int i = 1; i < costs.length; i++) {
            if (costs[i] == Integer.MAX_VALUE) {
                return -1;
            }
            
            delayTime = Math.max(delayTime, costs[i]);
        }
        
        return delayTime;
    }
	
	
	// solution 2: DFS
	public int networkDelayTime2(int[][] times, int N, int K) 
    {
        if (times == null || times.length == 0 || times[0].length == 0 || N <= 0 || K <= 0)
        {
            return 0; 
        }
        
        Set<Node>[] graph = new Set[N+1];
        int[] cost = new int[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        
        for (int i = 1; i <= N; i++)
        {
            graph[i] = new HashSet<>();
        }
        
        for (int[] time : times)
        {
            graph[time[0]].add(new Node(time[1], time[2]));
        }
        
        cost[K] = 0;
        dfs(graph, cost, K);
        int maxTime = Integer.MIN_VALUE;
        
        for (int i = 1; i <= N; i++)
        {
            maxTime = Math.max(maxTime, cost[i]);
        }
        
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
    
    private void dfs(Set<Node>[] graph, int[] cost, int curNode)
    {
        for (Node next : graph[curNode])
        {
            if (cost[next.node] > cost[curNode] + next.time)
            {
                cost[next.node] = cost[curNode] + next.time;
                dfs(graph, cost, next.node);
            }
        }
    }
    
    
    class Node 
    {
        public int node;
        public int time;
        
        public Node(int n, int t)
        {
            node = n;
            time = t;
        }
    }
}
