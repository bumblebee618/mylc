import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.

The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.

To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi].

In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.

Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.

 

Example 1:


Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
Output: 13
Explanation: The edge subdivisions are shown in the image above.
The nodes that are reachable are highlighted in yellow.
Example 2:

Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
Output: 23
Example 3:

Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
Output: 1
Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 

Constraints:

0 <= edges.length <= min(n * (n - 1) / 2, 104)
edges[i].length == 3
0 <= ui < vi < n
There are no multiple edges in the graph.
0 <= cnti <= 104
0 <= maxMoves <= 109
1 <= n <= 3000
 */
public class Q882_Reachable_Nodes_In_Subdivided_Graph {
	// solution 1:
	public int reachableNodes(int[][] edges, int maxMoves, int n) {
        if (edges == null || edges.length == 0 || edges[0].length == 0 || maxMoves <= 0 || n <= 0) {
            return 0;
        }

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new HashMap<>()).put(edge[1], edge[2]);
            graph.computeIfAbsent(edge[1], x -> new HashMap<>()).put(edge[0], edge[2]);
        }

        int result = 0;
        Queue<Tuple> queue = new PriorityQueue<>((a, b) -> b.moveLeft - a.moveLeft);
        queue.offer(new Tuple(0, maxMoves));

        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            int startIndex = tuple.startIndex;
            int moveLeft = tuple.moveLeft;

            // 防止当前节点已经通过其他path访问过了
            if (visited.contains(startIndex)) {
                continue;
            }

            visited.add(startIndex);
            result++;

            if (!graph.containsKey(startIndex)) {
                continue;
            }

            for (Map.Entry<Integer, Integer> nextEntry : graph.get(startIndex).entrySet()) {
                if (moveLeft > nextEntry.getValue() && !visited.contains(nextEntry.getKey())) {
                    queue.offer(new Tuple(nextEntry.getKey(), moveLeft - nextEntry.getValue() - 1));
                }

                graph.get(nextEntry.getKey()).put(startIndex, nextEntry.getValue() - Math.min(nextEntry.getValue(), moveLeft));
                result += Math.min(nextEntry.getValue(), moveLeft);
            }
        }

        return result;
    }

    class Tuple {
        public int startIndex;
        public int moveLeft;

        public Tuple(int index, int move) {
            startIndex = index;
            moveLeft = move;
        }
    }
	
	// solution 2:
	public int reachableNodes2(int[][] edges, int maxMoves, int n) {
        int[][] graph = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], -1);
        }
        
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        
        int result = 0;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        boolean[] visited = new boolean[n];
        pq.offer(new int[]{0, maxMoves});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int start = cur[0];
            int move = cur[1];
            
            if (visited[start]) {
                continue;
            }
            
            visited[start] = true;
            result++;
            
            for (int i = 0; i < n; i++) {
                if (graph[start][i] > -1) {
                    if (move > graph[start][i] && !visited[i]) {
                        pq.offer(new int[]{i, move - graph[start][i] - 1});
                    }
                    
                    graph[i][start] -= Math.min(move, graph[start][i]);
                    result += Math.min(move, graph[start][i]);
                }
            }
        }
        
        return result;
    }
