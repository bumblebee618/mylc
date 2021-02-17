/***
 * 
 * @author jackie
 * Given the root node of a binary search tree (BST) and a minValue. You need to find the node in the BST that the node's minValue equals the given minValue. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the minValue to search: 2
You should return this subtree:

      2     
     / \   
    1   3
In the example above, if we want to search the minValue 5, since there is no node with minValue 5, we should return NULL.

Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
 */
public class Q700_Search_in_a_Binary_Search_Tree {
	public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
        {
            return null;
        } 
        else if (root.val == val)
        {
            return root;
        }
        
        if (root.val < val)
        {
            return searchBST(root.right, val);
        }
        else
        {
            return searchBST(root.left, val);
        }
    }
}
