import java.util.*;

/***
 * 
 * @author jackie
 * Given a binary tree where every node has a unique minValue, and a target key k, find the minValue of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with minValue 3 (and not the leaf node with minValue 6) is nearest to the node with minValue 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
 * 
 */
public class Q742_Closest_Leaf_in_a_Binary_Tree {
	public int findClosestLeaf(TreeNode root, int k) {
        if (root == null) {
            return -1;
        } else if (root.left == null && root.right == null && root.val == k) {
            return root.val;
        }
        
        // use dfs to build graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfsBuildGraph(graph, root, null);
        
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        
        for (Map.Entry<TreeNode, List<TreeNode>> entry : graph.entrySet()) {
            if (entry.getKey().val == k) {
                queue.offer(entry.getKey());
                visited.add(entry.getKey());
                break;
            }
        }
        
        // bfs
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node.left == null && node.right == null) {
                return node.val;
            }
            
            for (TreeNode next : graph.get(node)) {
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        
        return -1;
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
}
