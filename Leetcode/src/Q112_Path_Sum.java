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
	// Solution 1:
	public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
	
	
	// Solution 2:
	public boolean hasPathSum2(TreeNode root, int sum) 
	{
        if (root == null) 
        {
            return false;
        }
        
        return dfs(root, 0, sum);
    }
    
    private boolean dfs(TreeNode node, int curSum, int target) 
    {
        if (node == null) 
        {
            return false;
        }  
        
        if (node.left == null && node.right == null) 
        {
            return curSum + node.val == target;
        } 
        
        return dfs(node.left, curSum + node.val, target) || dfs(node.right, curSum + node.val, target);
    }
}
