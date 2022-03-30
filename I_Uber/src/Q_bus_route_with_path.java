import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q_bus_route_with_path {
	private Set<Integer>[] routeGraph;
	private Map<Integer, Set<Integer>> stopToRouteMap;
	private Set<Integer> visitedStops;
	private Set<Integer> visitedRoutes;
	
	public List<String> numBusesToDestination(int[][] routes, int source, int target) {
		List<String> result = new LinkedList<>();
		
        if (routes == null || routes.length == 0 || routes[0].length == 0 || source < 0 || target < 0) {
            return result;
        } else if (source == target) {
            return result;
        }
        
        int routeSize = routes.length;
        routeGraph = new Set[routeSize];
        stopToRouteMap = new HashMap<>();
        visitedRoutes = new HashSet<>();
        visitedStops = new HashSet<>();
        
        for (int i = 0; i < routeSize; i++) {
        	routeGraph[i] = new HashSet<>();
        	
        	for (int stop : routes[i]) {
				routeGraph[i].add(stop);
        		stopToRouteMap.computeIfAbsent(stop, x -> new HashSet<>()).add(i);
        	}
        }
        
        if (!stopToRouteMap.containsKey(source) || !stopToRouteMap.containsKey(target)) {
            return result;
        }
        
        Queue<Node> queue = new LinkedList<>();
        visitedStops.add(source);
        
        for (int route : stopToRouteMap.get(source)) {
        	queue.offer(new Node(route, source, null));
        	visitedRoutes.add(route);
        }
        
        int step = 0;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	step++;
        	
        	for (int i = 0; i < size; i++) {
        		Node curNode = queue.poll();
        		
        		if (routeGraph[curNode.route].contains(target)) {
        			while (curNode != null) {
        				result.add(0, curNode.route + "(" + curNode.stop + ")" );
        				curNode = curNode.lastNode;
        			}
        			
        			System.out.println("take " + step + " steps");
        			return result;
        		}
        		
        		findNextRoute(queue, curNode);
        	}
        }
        
        return result;
	}
	
	private void findNextRoute(Queue<Node> queue, Node curNode) {
		for (int stop : routeGraph[curNode.route]) {
			if (visitedStops.contains(stop) || !stopToRouteMap.containsKey(stop)) {
				continue;
			}
			
			for (int nextRoute : stopToRouteMap.get(stop)) {
				if (!visitedRoutes.contains(nextRoute)) {
					queue.offer(new Node(nextRoute, stop, curNode));
					visitedRoutes.add(nextRoute);
				}
			}
		}
	}
	
	class Node {
		public int route;
		public int stop;
		public Node lastNode;
		
		public Node(int r, int s, Node l) {
			route = r;
			stop = s;
			lastNode = l;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		Q_bus_route_with_path test = new Q_bus_route_with_path();
		int[][] routes1 = {{1,2,7}, {3,6,7}};
		int source1 = 1, target1 = 6;
		
		int[][] routes2 = {{7,12}, {4,5,15}, {6}, {15,19}, {9,12,13}};
		int source2 = 15, target2 = 12;
		
		System.out.println(test.numBusesToDestination(routes1, source1, target1));
		System.out.println(test.numBusesToDestination(routes2, source2, target2));
	}
}
