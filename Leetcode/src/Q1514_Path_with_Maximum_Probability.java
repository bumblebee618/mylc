import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

 

Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 

Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
 */
public class Q1514_Path_with_Maximum_Probability {
	// solution 1: bfs + dp
	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> graph = new HashMap<>();        
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], x -> new HashMap<>()).put(edges[i][1], succProb[i]);
            graph.computeIfAbsent(edges[i][1], x -> new HashMap<>()).put(edges[i][0], succProb[i]);
        }
        
        double[] probability = new double[n];
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        probability[start] = 1;
        
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            
            if (curNode == end || !graph.containsKey(curNode)) {
                continue;
            }
            
            for (Map.Entry<Integer, Double> entry : graph.get(curNode).entrySet()) {
                int nextNode = entry.getKey();
                
                if (probability[nextNode] < probability[curNode] * entry.getValue()) {
                    probability[nextNode] = probability[curNode] * entry.getValue();
                    queue.offer(nextNode);
                }
            }
        }
        
        return probability[end];
    }

	
	
	// solution 2:
	public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Double>[] graph = new Map[n];
        
        for (int i = 0; i < n; i++) {
        	graph[i] = new HashMap<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
        	graph[edges[i][0]].put(edges[i][1], succProb[i]);
        	graph[edges[i][1]].put(edges[i][0], succProb[i]);
        }
        
        Queue<Pair> heap = new PriorityQueue<>((a, b) -> (((Double) b.prob).compareTo((Double) a.prob)));
        heap.offer(new Pair(start, 1.0));
        
        double[] dist = new double[n];
        
        while (!heap.isEmpty()) {
        	Pair pair = heap.poll();
            int parent = pair.node;
		    double prob = pair.prob;

		    if (parent == end) {
                return prob;
            }
        	
        	for (int next : graph[pair.node].keySet()) {
        		if (pair.prob * graph[pair.node].get(next) > dist[next]) {
        			dist[next] = pair.prob * graph[pair.node].get(next);
        			heap.offer(new Pair(next, dist[next]));
        		} 
        	}
        }
        
        return dist[n-1] == Double.MIN_VALUE ? 0 : dist[n-1];
    }
    
    class Pair {
    	public int node;
    	public double prob;
    	
    	public Pair (int n, double p) {
    		node = n;
    		prob = p;
    	}
    }
}
