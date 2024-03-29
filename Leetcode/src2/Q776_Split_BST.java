/***
 * 
 * @author jackie
 * 
 * Given a Binary Search Tree (BST) with root node root, and a target minValue V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target minValue, while the other subtree has all nodes that are greater than the target minValue.  It's not necessarily the case that the tree contains a node with minValue V.

Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.

You should output the root TreeNode of both subtrees after splitting, in any order.

Example 1:

Input: root = [4,2,6,1,3,5,7], V = 2
Output: [[2,1],[4,3,6,null,null,5,7]]
Explanation:
Note that root, output[0], and output[1] are TreeNode objects, not arrays.

The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
Note:

The size of the BST will not exceed 50.
The BST is always valid and each node's minValue is different.
 */
public class Q776_Split_BST {
	public TreeNode[] splitBST(TreeNode root, int target) 
    {
        if (root == null)
        {
            return new TreeNode[] {null, null};
        }
        
        TreeNode[] result = new TreeNode[2];
        
        if (root.val <= target) // split point is in right subtree 
        {
            result = splitBST(root.right, target);
            
            // update the right subtree to only include node less than or equal to target
            root.right = result[0]; 
            result[0] = root;
        } 
        else   // split point is in left subtree 
        {
            result = splitBST(root.left, target);
            
            // update the left subtree to only include node greater than target
            root.left = result[1];
            result[1] = root;
            return result;
        }
        
        return result;
    }
}
