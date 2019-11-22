import java.util.*;
/**
 * 
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
 *
 */
public class Q513_Find_Bottom_Left_Tree_Value {
	// solution1: use queue
	public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int result = root.val;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
            
            if (--size == 0) {
                size = queue.size();
                
                if (size > 0) {
                    result = queue.peek().val;
                }
            }
        }
        
        return result;
    }
	
	
	// solution2: use recursion
	private int maxDepth = -1;
    private int result = -1;
    
    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        findBottomLeftValue(root, 0);
        return result;
    }
    
    private void findBottomLeftValue(TreeNode node, int depth) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            if (maxDepth < depth) {
                maxDepth = depth;
                result = node.val;
            }
            
            return;
        }
        
        findBottomLeftValue(node.left, depth + 1);
        findBottomLeftValue(node.right, depth + 1);
    }
}
