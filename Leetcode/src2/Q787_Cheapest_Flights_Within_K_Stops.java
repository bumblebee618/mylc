import java.util.*;

/**
 * 
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
 *
 */

public class Q787_Cheapest_Flights_Within_K_Stops 
{
	// solution 1:
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0 || flights[0].length != 3) {
            return 0;
        } else if (n <= 0 || src < 0 || src >= n || dst < 0 || dst >= n || K < 0 || K > n) {
            return 0;
        }

        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[src] = 0;
        
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        
        for (int[] flight : flights) {
            prices.computeIfAbsent(flight[0], x -> new HashMap<>()).put(flight[1], flight[2]);
        }
        
        Queue<Node> heap = new LinkedList<>();
        
        // cost, city and steps remained
        heap.add(new Node(src, 0));
        int stop = 0;
        
        while (!heap.isEmpty()) {
            if (stop++ >= K+1) {
                break;
            }

            int size = heap.size();

            for (int i = 0; i < size; i++) {
                Node node = heap.poll();    
                Map<Integer, Integer> priceMap = prices.getOrDefault(node.city, new HashMap<>());
                
                // bfs
                for (Map.Entry<Integer, Integer> entry : priceMap.entrySet()) {
                    if (node.cost + entry.getValue() < memo[entry.getKey()]) {
                        memo[entry.getKey()] = node.cost + entry.getValue();
                        heap.add(new Node(entry.getKey(), memo[entry.getKey()]));
                    }
                }
            }
        }
        
        return memo[dst] == Integer.MAX_VALUE ? -1 : memo[dst];
    }
    
    class Node {
    	public int city;
        public int cost;
        
        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
	
    
    
    /***
	// solution 1:
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) 
    {
        if (flights == null || flights.length == 0 || flights[0].length != 3)
        {
            return 0;
        }
        else if (n <= 0 || src < 0 || src >= n || dst < 0 || dst >= n || K < 0 || K > n)
        {
            return 0;
        }
        
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        
        for (int[] flight : flights) 
        {
            prices.computeIfAbsent(flight[0], x -> new HashMap<>()).put(flight[1], flight[2]);
        }
        
        Queue<Node> pq = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
        
        // cost, city and steps remained
        pq.add(new Node(src, 0, K+1));
        
        while (!pq.isEmpty()) 
        {
        	Node node = pq.poll();
            
            if (node.city == dst) 
            {
                return node.cost;
            }
            
            if (node.stopRemained > 0) 
            {
                Map<Integer, Integer> priceMap = prices.getOrDefault(node.city, new HashMap<>());
                
                // bfs
                for (int nextCity : priceMap.keySet()) 
                {
                    pq.add(new Node(nextCity, node.cost + priceMap.get(nextCity), node.stopRemained - 1));
                }
            }
        }
        
        return -1;
    }
    
    class Node
    {
    	public int city;
        public int cost;
        public int stopRemained;
        
        public Node(int city, int cost, int stopRemained)
        {
            this.city = city;
            this.cost = cost;
            this.stopRemained = stopRemained;
        }
    }
	
	
	// solution 2:
	public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) 
    {
        if (flights == null || flights.length == 0 || flights[0].length != 3)
        {
            return 0;
        }
        else if (n <= 0 || src < 0 || src >= n || dst < 0 || dst >= n || K < 0 || K > n)
        {
            return 0;
        }
        
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        
        for (int[] flight : flights) 
        {
            prices.computeIfAbsent(flight[0], x -> new HashMap<>()).put(flight[1], flight[2]);
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        
        // price, city and steps remained
        pq.add(new int[] {0, src, K + 1});
        
        while (!pq.isEmpty()) 
        {
            int[] top = pq.poll();
            int totalPrice = top[0];
            int city = top[1];
            int stops = top[2];
            
            if (city == dst) 
            {
                return totalPrice;
            }
            
            if (stops > 0) 
            {
                Map<Integer, Integer> map = prices.getOrDefault(city, new HashMap<>());
                
                // bfs
                for (int nextCity : map.keySet()) 
                {
                    pq.add(new int[] {totalPrice + map.get(nextCity), nextCity, stops - 1});
                }
            }
        }
        
        return -1;
    }
    
    

	// solution 3:
	public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        if (n <= 0 || flights == null || flights.length == 0 || flights[0].length == 0 || src == dst) {
            return 0;
        }
        
        int[][] srcToDst = new int[n][n];
        
        for (int[] flight : flights) {
            srcToDst[flight[0]][flight[1]] = flight[2]; 
        }
						
        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src, 0, 0));
				
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;
				
        while (!minHeap.isEmpty()) {
            City curCity = minHeap.poll();
            
            if(curCity.id == dst || curCity.stopFromSrc > K) {
                continue;
            }

            int[] nexts = srcToDst[curCity.id];
            
            for (int i = 0; i < n; i++) {
                if (nexts[i] != 0) {
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    
                    if (newCost < cost[i] || newStop < stop[i]) {
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = Math.min(cost[i], newCost);
                        stop[i] = Math.min(stop[i], newStop);
                    }
                }
            }
        }
        
        return cost[dst] == Integer.MAX_VALUE? -1 : cost[dst];
    }
    
    class City implements Comparable<City> {
        int id;
        int costFromSrc;
        int stopFromSrc;
        
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
       
        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }
    ***/
    
    
    
    public static void main(String[] args)
    {
    	Q787_Cheapest_Flights_Within_K_Stops test = new Q787_Cheapest_Flights_Within_K_Stops();
    	int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
    	System.out.println(test.findCheapestPrice(3, flights, 0, 2, 1));
    }
}
