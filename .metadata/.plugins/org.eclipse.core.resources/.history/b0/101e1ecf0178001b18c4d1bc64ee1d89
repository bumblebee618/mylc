import java.util.LinkedList;
import java.util.Queue;
/******
 * 
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node 
down to the nearest leaf node.

 * 
 * */

public class Q111_Minimum_Depth_of_Binary_Tree {
	public int minDepth(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        else if (root.left == null)
        {
            return minDepth(root.right) + 1;
        }
        else if (root.right == null)
        {
            return minDepth(root.left) + 1;
        }
        else 
        {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
	
	// solution 2: using recursive
	private int depth = Integer.MAX_VALUE;
    
    public int minDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        getDepth(root, 1);
        return depth;
    }
    
    public void getDepth(TreeNode node, int level) {
        if(node == null) {
            return;
        } else if(node.left == null && node.right == null) {
            depth = Math.min(depth, level);
            return;
        }
        
        getDepth(node.left, level + 1);
        getDepth(node.right, level + 1);
    }
    
    
    
	// solution 3: using iterator
    public int minDepth3(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int level = 1;
        int size = 1;
        
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if(node.left == null && node.right == null) {
                break;
            }
            
            if(node.left != null) {
                queue.offer(node.left);
            }
            
            if(node.right != null) {
                queue.offer(node.right);
            }
            
            if(--size == 0) {
                size = queue.size();
                level++;
            }
        }
        
        return level;
    }
}
