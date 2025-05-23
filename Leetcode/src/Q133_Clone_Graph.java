import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/******
 * 
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/

 * 
 * */

public class Q133_Clone_Graph {
	/*******************************************************************
	 * 此题为深拷贝，考虑dfs来遍历原图；
	 * 	(1). 用两个队列，一个用于原图dfs，另一个用于纪录对应的新图的结构；
	 *  (2). 用一个hashmap纪录已经建了的新图中的node；当访问old graph中的邻居时，
	 *  	 如果此邻居结点已在map中，则直接取出来；否则就需要新建此结点。
	 *  
	 *******************************************************************/
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) 
	{
		if (node == null)
        {
            return node;
        }
        
		UndirectedGraphNode copyRoot = new UndirectedGraphNode(node.label);
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, copyRoot);
        
        Queue<UndirectedGraphNode> oldQueue = new LinkedList<>();
        oldQueue.offer(node);
        
        Queue<UndirectedGraphNode> newQueue = new LinkedList<>();
        newQueue.offer(copyRoot);
        
        while (!oldQueue.isEmpty())
        {
        	UndirectedGraphNode oldNode = oldQueue.poll();
        	UndirectedGraphNode newNode = newQueue.poll();
            
            for (UndirectedGraphNode n : oldNode.neighbors)
            {
                if (map.containsKey(n))      // 已经建立的新图中的结点，则无需继续建立
                {
                    newNode.neighbors.add(map.get(n));
                } 
                else 
                {
                	UndirectedGraphNode copyNeighbor = new UndirectedGraphNode(n.label);
                    newNode.neighbors.add(copyNeighbor);
                    map.put(n, copyNeighbor);     // map.put的操作放与此处，即当结点建立了，就立即放入map
                    newQueue.offer(copyNeighbor);
                    oldQueue.offer(n);
                }
            }
        }
        
        return copyRoot;
    }
}
