import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given the root of a binary tree, each node in the tree has a distinct minValue.

After deleting all nodes with a minValue in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct minValue between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class Q1110_Delete_Nodes_And_Return_Forest {
	private List<TreeNode> result = new LinkedList<>();
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) 
    {
        if (root == null)
        {
            return result;
        }
        else if (to_delete == null || to_delete.length == 0)
        {
            result.add(root);
            return result;
        }
        
        Set<Integer> deleteNode = new HashSet<>();
        
        for (int node : to_delete)
        {
            deleteNode.add(node);
        }
        
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        dfs(root, dummy, deleteNode, true);
        return result;   
    }
    
    private void dfs(
        TreeNode node, 
        TreeNode parent,
        Set<Integer> deleteNode, 
        boolean isParentDeleted)
    {
        if (node == null)
        {
            return;
        }
        
        // only when parent is deleted then we add current node to result
        if (isParentDeleted && !deleteNode.contains(node.val))
        {
            result.add(node);
        }
        
        boolean isCurrentDeleted = deleteNode.contains(node.val);
        
        if (isCurrentDeleted)
        {
            if (parent.left == node)
            {
                parent.left = null;
            }
            else
            {
                parent.right = null;
            }
        }
        
        dfs(node.left, node, deleteNode, isCurrentDeleted);
        dfs(node.right, node, deleteNode, isCurrentDeleted);
    }
}
