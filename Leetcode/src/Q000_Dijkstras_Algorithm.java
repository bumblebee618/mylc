import java.util.*;

public class Q000_Dijkstras_Algorithm {
	public void findCheapestPrice(int n, int[][] flights, int src) {
		int[][] graph = new int[n][n];
		
		for (int[] flight : flights) {
			graph[flight[0]][flight[1]] = flight[2];
		}
		
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(src);
		Set<Integer> visited = new HashSet<>();
		visited.add(src);
		printCost(cost);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int minIndex = -1;
			int[] nexts = graph[cur];
			
			for (int i = 0; i < n; i++) {
				if (nexts[i] > 0 && !visited.contains(i)) {
					if (minIndex == -1 || nexts[i] < nexts[minIndex]) {
						minIndex = i;
					}
				}
			}
			
			if (minIndex != -1) {
				cost[minIndex] = cost[cur] + graph[cur][minIndex];
				queue.offer(minIndex);
				visited.add(minIndex);
			}
		}
		
		printCost(cost);
	}

	private void printGraph(int[][] graph) {
		for (int[] row : graph) {
			for (int elem : row) {
				System.out.print(elem + "   ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void printCost(int[] costs) {
		for (int cost : costs) {
			System.out.print(cost + "   ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Q000_Dijkstras_Algorithm test = new Q000_Dijkstras_Algorithm();
		int n = 3, src = 0;
		int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};
		test.findCheapestPrice(n, flights, src);
	}

}
