import java.util.*;

/***
 * 
 * @author jackie
 *
 * We are given a binary tree (with root node root), a target node, and an integer minValue K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with minValue 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 */
public class Q863_All_Nodes_Distance_K_in_Binary_Tree {
	// solution 1: dfs + bfs
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new LinkedList<>();
        
        if (root == null || target == null || K < 0) {
            return result;
        }
        
        // use dfs to build graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfsBuildGraph(graph, root, null);
        
        if (!graph.containsKey(target)) {
            return result;
        }
        
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                
                if (distance == K) {
                    result.add(curNode.val);
                }
                
                if (distance < K) {
                    for (TreeNode nextNode : graph.get(curNode)) {
                        if (!visited.contains(nextNode)) {
                            queue.offer(nextNode);
                            visited.add(nextNode);
                        }
                    }    
                }
            }
            
            distance++;
        }
        
        return result;
    }
    
    private void dfsBuildGraph(Map<TreeNode, List<TreeNode>> graph, TreeNode curNode, TreeNode parent) {
        if (curNode == null) {
            return;
        }
        
        if (parent != null) {
            graph.computeIfAbsent(curNode, x -> new LinkedList<>()).add(parent);
            graph.computeIfAbsent(parent, x -> new LinkedList<>()).add(curNode);
        }
        
        dfsBuildGraph(graph, curNode.left, curNode);
        dfsBuildGraph(graph, curNode.right, curNode);
    }
	
    

    /***
	// solution 2:    
	private List<Integer> result;
    private TreeNode target;
    private int k;
    
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        result = new LinkedList<Integer>();
        
        if (root == null || target == null || k < 0)
        {
            return result;
        }
        
        this.target = target;
        this.k = k;
        dfs(root);
        return result;
    }

    // Return vertex distance from node to target if exists, else -1
    // Vertex distance: the number of vertices on the path from node to target
    public int dfs(TreeNode node) {
        if (node == null)
        {
            return -1;
        }
        else if (node == target) 
        {
            subtree_add(node, 0); // 向下走，查询子节点 children nodes
            return 1;             // 往上走，查询父节点 parent nodes
        } 
        else {
            int left = dfs(node.left);
            int right = dfs(node.right);
            
            if (left != -1) 
            {
                if (left == k) 
                {
                    result.add(node.val);
                }
                else 
                {
                	subtree_add(node.right, left + 1);
				}
                
                return left + 1;
            } 
            else if (right != -1) 
            {
                if (right == k) 
                {
                    result.add(node.val);
                }
                else
                {
                	subtree_add(node.left, right + 1);
                }
                
                return right + 1;
            } 
            else 
            {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist) {
        if (node == null)
        {
            return;
        }
        
        if (dist == k) 
        {
            result.add(node.val);
        }
        else 
        {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    }
    ***/
}
