import java.util.*;

/***
 * 
 * @author jackie
 * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi with the cost edgeCosti.

Implement the Graph class:

Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that there is no edge between the two nodes before adding this one.
int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
 

Example 1:


Input
["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
Output
[null, 6, -1, null, 6]

Explanation
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.
 

Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1)
edges[i].length == edge.length == 3
0 <= fromi, toi, from, to, node1, node2 <= n - 1
1 <= edgeCosti, edgeCost <= 106
There are no repeated edges and no self-loops in the graph at any point.
At most 100 calls will be made for addEdge.
At most 100 calls will be made for shortestPath.

 */
public class Q2642_Design_Graph_With_Shortest_Path_Calculator {
	private Map<Integer, Map<Integer, Integer>> graph;
    private int nodeCount = 0;

    public Q2642_Design_Graph_With_Shortest_Path_Calculator(int n, int[][] edges) {
        if (n <= 0) {
            return;
        }

        graph = new HashMap<>();
        nodeCount = n;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new HashMap<>()).put(edge[1], edge[2]);
        }
    }
    
    public void addEdge(int[] edge) {
        if (edge == null || edge.length != 3) {
            return;
        }

        if (graph.containsKey(edge[0]) && graph.get(edge[0]).containsKey(edge[1])) {
            if (graph.get(edge[0]).get(edge[1]) > edge[2]) {
                graph.get(edge[0]).put(edge[1], edge[2]);
            }
        } else {
            graph.computeIfAbsent(edge[0], x -> new HashMap<>()).put(edge[1], edge[2]);
        }
    }
    
    // Dijkstra's algorithm to find shortest path
    public int shortestPath(int node1, int node2) {
    	if (node1 == node2) {
            return 0;
        } else if (!graph.containsKey(node1)) {
            return -1;
        }
    	
        int[] distances = new int[nodeCount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[node1] = 0;

        Queue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        priorityQueue.offer(new int[] {node1, 0});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[0], currentCost = current[1];
            
            if (currentNode == node2) {
                return currentCost;
            }

            // Skip if a shorter path has already been found
            if (currentCost > distances[currentNode] || !graph.containsKey(currentNode)) {
                continue;
            }

            // Explore neighbors and update distances
            for (Map.Entry<Integer, Integer> entry : graph.get(currentNode).entrySet()) {
                int neighbor = entry.getKey(), edgeCost = entry.getValue();
                int newRouteCost = edgeCost + distances[currentNode];

                // Update distance if a shorter route is found
                if (distances[neighbor] > newRouteCost) {
                    distances[neighbor] = newRouteCost;
                    priorityQueue.offer(new int[] {neighbor, newRouteCost});
                }
            }
        }

        // Return the minimum distance or -1 if no path exists
        return ((distances[node2] == Integer.MAX_VALUE) ? -1 : distances[node2]);
    }
}
