/***
 * 
 * @author jackie
 * 
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values row and col of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values row and col are cousins.

 

Example 1:


Input: root = [1,2,3,4], row = 4, col = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], row = 5, col = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], row = 2, col = 3
Output: false
 

Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer minValue from 1 to 100.
 */
public class Q993_Cousins_in_Binary_Tree {
	public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
        {
            return false;
        }
        
        Tuple x_t = dfs(null, root, 0, x);
        Tuple y_t = dfs(null, root, 0, y);
        
        if (x_t == null || y_t == null)
        {
            return false;
        }
        else
        {
            return x_t.depth == y_t.depth && x_t.parent != y_t.parent;
        }
    }
    
    private Tuple dfs(TreeNode parent, TreeNode node, int curDepth, int target)
    {
        if (node == null)
        {
            return null;
        }
        
        if (node.val == target)
        {
            return new Tuple(parent, curDepth);
        }
        
        Tuple left = dfs(node, node.left, curDepth+1, target);
        Tuple right = dfs(node, node.right, curDepth+1, target);
        return left != null ? left : right;
    }
    
    class Tuple
    {
        public TreeNode parent;
        public int depth;
        
        public Tuple(TreeNode p, int d)
        {
            parent = p;
            depth = d;
        }
    }
}
