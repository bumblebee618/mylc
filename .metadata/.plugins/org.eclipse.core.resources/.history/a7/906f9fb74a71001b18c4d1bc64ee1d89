import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class Q1110_Delete_Nodes_And_Return_Forest {
private List<TreeNode> result = new LinkedList<>();
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null)
        {
            return result;
        }
        else if (to_delete == null || to_delete.length == 0)
        {
            result.add(root);
            return result;
        }
        
        Set<Integer> set = new HashSet<>();
        
        for (int delete : to_delete)
        {
            set.add(delete);
        }
        
        if (!set.contains(root.val))
        {
            result.add(root);
        }
        
        dfs(null, root, set);
        return result;
    }
    
    private void dfs(TreeNode parent, TreeNode node, Set<Integer> set)
    {
        if (node == null)
        {
            return;
        }
        
        if (set.contains(node.val))
        {
            if (node.left != null && !set.contains(node.left.val))
            {
                result.add(node.left);
            }
            
            if (node.right != null && !set.contains(node.right.val))
            {
                result.add(node.right);
            }
            
            if (parent != null && parent.left == node)
            {
                parent.left = null;
            }
            
            if (parent != null && parent.right == node)
            {
                parent.right = null;
            }
        }
        
        dfs(node, node.left, set);
        dfs(node, node.right, set);
    }
}
