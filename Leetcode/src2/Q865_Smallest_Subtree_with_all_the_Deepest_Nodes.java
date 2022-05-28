/***
 * 
 * @author jackie
 * 
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with minValue 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with minValue 2.
Both the input and output have TreeNode type.
 

Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.
 */
public class Q865_Smallest_Subtree_with_all_the_Deepest_Nodes 
{
	public TreeNode subtreeWithAllDeepest(TreeNode root) 
	{
        if (root == null)
        {
             return root;
        }
        
        int treeDepth = findDepth(root);
        return dfs(root, 1, treeDepth);
    }
    
    private TreeNode dfs(TreeNode node, int curDepth, int treeDepth)
    {
        if (node == null)
        {
            return null;
        }
        else if (curDepth == treeDepth)
        {
            return node;
        }
        
        TreeNode left = dfs(node.left, curDepth+1, treeDepth);
        TreeNode right = dfs(node.right, curDepth+1, treeDepth);
        
        if (left != null && right != null)
        {
            return node;
        }
        else if (left != null)
        {
            return left;
        }
        else if (right != null)
        {
            return right;
        }
        else
        {
            return null;
        }
    }
    
    private int findDepth(TreeNode node)
    {
        if (node == null)
        {
            return 0;
        }
        
        return Math.max(findDepth(node.left), findDepth(node.right)) + 1;
    }
}
