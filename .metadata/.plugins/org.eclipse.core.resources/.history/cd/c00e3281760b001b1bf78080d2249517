import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*******
 * 
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first 
take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
Read more about how a graph is represented.

 * 
 * */

public class Q210_Course_Schedule_II {
	/*******************************************************************
	 * 此题为207题的follow up， 需要记录上课的顺序
	 *  
	 *******************************************************************/
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0) {
            return new int[0];
        } 
        
        int[] result = new int[numCourses];
        
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
        
        Set<Integer>[] graph = new Set[numCourses];
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int[] prerequisite : prerequisites) {
            if (graph[prerequisite[1]] == null) {
                graph[prerequisite[1]] = new HashSet<Integer>();
            }
            if (!graph[prerequisite[1]].contains(prerequisite[0])) {
                graph[prerequisite[1]].add(prerequisite[0]);
                map.put(prerequisite[0], map.getOrDefault(prerequisite[0], 0) + 1);
            }
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
        }
        
        if (queue.isEmpty()) {
            return new int[0];
        }
        
        int index = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index++] = node;
            
            if (graph[node] == null) {
                continue;
            }
            
            for (int next : graph[node]) {
                int count = map.get(next);
                
                if (count == 1) {
                    map.remove(next);
                    queue.offer(next);
                } else {
                    map.put(next, count - 1);
                }
            }
        }
        
        return map.size() == 0 ? result : new int[0];
    }
	
	
	
	
	
	
	
	
	
	/****************************************************/
	// by other, faster
	public int[] findOrder2(int numCourses, int[][] prerequisites) {
	    // Create an adjacency list
		ArrayList<Integer>[] edges = new ArrayList[numCourses];

	    // array to track visited nodes and detect cycles, 0 = white, 1 = gray, 2 = black
	    int[] visited = new int[numCourses];

	    // output array
	    int[] res = new int[numCourses];

	    // initialize adjacency list
	    for (int i = 0; i < edges.length; i++) 
	        edges[i] = new ArrayList<Integer>();
	    for (int[] edge : prerequisites) 
	        edges[edge[0]].add(edge[1]); 

	    // run topological sort
	    for (int i = 0, j = 0; i < numCourses; i++) {
	        j = dfs(i, edges, visited, res, j);
	        if (j == -1) return new int[0]; // cycle, return empty array
	    }

	    return res;
	}
	
	private int dfs(int v, ArrayList<Integer>[] edges, int[] visited, int[] res, int i) {
	    if (visited[v] == 2) return i;           // black node
	    if (visited[v] == 1) return -1;          // gray node -> cycle
	    visited[v] = 1;
	    for(int j : edges[v]) {
	        i = dfs(j, edges, visited, res, i);  // variable "i" is used to record the order storing in array res
	        if (i == -1) return -1;              // if there is a cycle, propagate the error
	    }
	    visited[v] = 2;
	    res[i] = v;
	    return i+1;
	}
	
	
	
	/****************************************************/
	// by Jackie
	private LinkedList<Integer>[] course;
	private int[] visited;
	private LinkedList<Integer> res = new LinkedList<Integer>();
	
	public int[] findOrder3(int numCourses, int[][] prerequisites) {
		visited = new int[numCourses];    
		course = new LinkedList[numCourses];
		for(int i = 0; i < numCourses; ++i)
			course[i] = new LinkedList<Integer>();
		
		for(int i = 0, len = prerequisites.length; i < len; ++i)
			course[prerequisites[i][0]].add(prerequisites[i][1]);
		
		int[] result = new int[numCourses];
		for(int i = 0; i < numCourses; ++i){
			if(!finish(i))
				return new int[0];
		}

		for(int i = 0, len = res.size(); i < len; ++i)
			result[i] = res.get(i);

		return result;
	}
	
	public boolean finish(int vertex){
		if(visited[vertex] == 1) return true;
		if(visited[vertex] == -1) return false;
		
		visited[vertex] = -1;
		for(int i : course[vertex]){
			if(!finish(i))
				return false;
		}
		res.add(vertex);
		visited[vertex] = 1;   // 通过
		return true;
	}	
	
	public static void main(String[] args){
		Q210_Course_Schedule_II t = new Q210_Course_Schedule_II();
		int[][] prerequisites = {{0,1},{1,3},{3,2}};
		int[] res = t.findOrder(4, prerequisites);
		for(int i = 0; i < res.length; ++i)
			System.out.print(res[i] + ", ");
		System.out.println();
		int[] res2 = t.findOrder2(4, prerequisites);
		for(int i = 0; i < res.length; ++i)
			System.out.print(res2[i] + ", ");
	}
}
