/***
 * 
 * @author jackie
 * 
 * A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

 

Example 1:


Input: [1,1,1,1,1,null,1]
Output: true
Example 2:


Input: [2,2,2,5,2]
Output: false
 

Note:

The number of nodes in the given tree will be in the range [1, 100].
Each node's value will be an integer in the range [0, 99].
 */
public class Q965_Univalued_Binary_Tree {
	public boolean isUnivalTree(TreeNode root) {
        if (root == null)
        {
            return true;
        }
        
        return dfs(root, root.val);
    }
    
    private boolean dfs(TreeNode node, int target)
    {
        if (node == null)
        {
            return true;
        }
        else if (node.val != target)
        {
            return false;
        }
        else
        {
            return dfs(node.left, target) && dfs(node.right, target);
        }
    }
}
