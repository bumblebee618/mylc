import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.

 

Example 1:



Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
 */
public class Q1382_Balance_a_Binary_Search_Tree {
	public TreeNode balanceBST(TreeNode root) 
    {
        if (root == null)
        {
            return root;
        }
        
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        return buildTree(list, 0, list.size()-1);
    }
    
    private void dfs(TreeNode root, List<TreeNode> list)
    {
        Stack<TreeNode> stack = new Stack();
        
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            list.add(root);
            root = root.right;
        }
    }
    
    private TreeNode buildTree(List<TreeNode> list, int left, int right)
    {
        if (left > right)
        {
            return null;
        }
        else if (left == right)
        {
            TreeNode node = list.get(left);
            node.left = node.right = null;
            return node;
        }
        
        int mid = left + (right - left) / 2;
        TreeNode root = list.get(mid);
        root.left = buildTree(list, left, mid-1);
        root.right = buildTree(list, mid+1, right);
        return root;
    }
}
