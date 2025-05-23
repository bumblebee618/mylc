import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 

Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 */
public class Q1192_Critical_Connections_in_a_Network {
    private List<List<Integer>> answers = new ArrayList<>(); // 返回结果
    private int[] depthArray;      // 节点深度数组
    private Set<Integer>[] graph;  // 结构图
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    	if (n <= 0 || connections == null || connections.size() == 0) {
            return answers;
    	}
    	
        depthArray = new int[n];     // 初始化深度数组
        Arrays.fill(depthArray, -1); // 所有节点初始深度为-1
        graph = new Set[n];          // 初始化结构图map[i]代表节点i可以连通哪些节点
            
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        
        for (List<Integer> connection : connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }
        
        dfs(0, 0, 0);
        return answers;
    }
    
    // current为当前节点
    // previous为前节点
    // depth为当前深度
    // 返回值为当前节点所有dfs路径终点的最小深度
    private int dfs(int curNode, int prevNode, int depth) {
        depthArray[curNode] = depth;       // 将当前深度存入深度数组
        int result = Integer.MAX_VALUE;    // 返回值
    
        for (int nextNode : graph[curNode]) { 
            // 不能走回头路
            if (nextNode == prevNode) { 
                continue;
            }
        
            int endDepth = 0; // dfs终点深度
        
            // 深度为-1的点没走过，可以dfs, 相当于visited
            if (depthArray[nextNode] == -1) { 
                endDepth = dfs(nextNode, curNode, depth+1);
                
                // 如果深度大于当前深度，说明当前点不在闭环上,因为在闭环上的点总是能回到起始点
                // 当前点与下一节点i之间的连线为答案之一
                if (endDepth > depth) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curNode);
                    list.add(nextNode);
                    answers.add(list);
                }
            } else {
                // i节点深度不为-1，说明已经走过，i节点为dfs终点
                endDepth = depthArray[nextNode];
            }
            
            // 更新最小深度
            result = Math.min(result, endDepth);
        }
    
        return result;
    }
    
    
    
    
    
    
    
    /*************************** main ***************************/
    
    public static void main(String[] args) {
    	Q1192_Critical_Connections_in_a_Network test = new Q1192_Critical_Connections_in_a_Network();
    	List<List<Integer>> connections = new LinkedList<>();
    	
    	List<Integer> connection1 = new LinkedList<>();
    	connection1.add(0);
    	connection1.add(1);
    	connections.add(connection1);
    	
    	List<Integer> connection2 = new LinkedList<>();
    	connection2.add(0);
    	connection2.add(2);
    	connections.add(connection2);
    	
    	List<Integer> connection3 = new LinkedList<>();
    	connection3.add(1);
    	connection3.add(3);
    	connections.add(connection3);
    	
    	List<Integer> connection4 = new LinkedList<>();
    	connection4.add(2);
    	connection4.add(4);
    	connections.add(connection4);
    	
    	List<Integer> connection5 = new LinkedList<>();
    	connection5.add(3);
    	connection5.add(5);
    	connections.add(connection5);
    	
    	List<Integer> connection6 = new LinkedList<>();
    	connection6.add(4);
    	connection6.add(5);
    	connections.add(connection6);
    	
    	test.criticalConnections(6, connections);
    }
    
}
