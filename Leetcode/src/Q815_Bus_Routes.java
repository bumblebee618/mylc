import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/***
 * 
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

 

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 

Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 105
All the values of routes[i] are unique.
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
 *
 */
public class Q815_Bus_Routes {
	private Set<Integer>[] routeGraph;
    private Map<Integer, Set<Integer>> stopToRouteMap;
    private Set<Integer> visitedRoutes;
    private Set<Integer> visitedStops;
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (routes == null || routes.length == 0 || routes[0].length == 0 || source < 0 || target < 0) {
            return -1;
        } else if (source == target) {
            return 0;
        }
        
        routeGraph = new Set[routes.length];
        stopToRouteMap = new HashMap<>();
        visitedRoutes = new HashSet<>();
        visitedStops = new HashSet<>();
        
        for (int i = 0; i < routes.length; i++) {
            routeGraph[i] = new HashSet<>();
            
            for (int stop : routes[i]) {
                routeGraph[i].add(stop);
                stopToRouteMap.computeIfAbsent(stop, x -> new HashSet<>()).add(i);
            }
        }
        
        if (!stopToRouteMap.containsKey(source) || !stopToRouteMap.containsKey(target)) {
            return -1;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        visitedStops.add(source);
        int step = 0;
        
        for (int route : stopToRouteMap.get(source)) {
            queue.offer(route);
            visitedRoutes.add(route);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            
            for (int i = 0; i < size; i++) {
                int curRoute = queue.poll();
                
                if (routeGraph[curRoute].contains(target)) {
                    return step;
                }
                
                findNextRoute(queue, curRoute);
            }
        }
        
        return -1;   
    }
    
    private void findNextRoute(Queue<Integer> queue, int curRoute) {
        for (int stop : routeGraph[curRoute]) {
            if (visitedStops.contains(stop) || !stopToRouteMap.containsKey(stop)) {
                continue;
            }
            
            for (int nextRoute : stopToRouteMap.get(stop)) {
                if (!visitedRoutes.contains(nextRoute)) {
                    queue.offer(nextRoute);
                    visitedRoutes.add(nextRoute);
                }
            }
        }
    }
}
