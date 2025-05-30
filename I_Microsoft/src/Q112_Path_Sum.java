import java.util.*;
/*****
 * 
Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 * 
 * */

public class Q112_Path_Sum {
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        
        return DFS(root, 0, sum);
    }
    
    public boolean DFS(TreeNode node, int solution, int target) {
        if(node == null) {
            return false;
        } else if(node.left == null && node.right == null) {
            return solution + node.val == target;
        } 
        
        return DFS(node.left, solution + node.val, target) || DFS(node.right, solution + node.val, target);
    }
}
