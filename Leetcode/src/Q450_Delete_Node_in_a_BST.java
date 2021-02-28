/*******
 * 
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with minValue 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
    
 * 
 * */

public class Q450_Delete_Node_in_a_BST 
{
	// time is O(logn)
	public TreeNode deleteNode(TreeNode root, int key) 
    {
        if (root == null)
        {
            return null;
        }
        
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode parent = findParent(root, dummy, key);
        
        if (parent != null)
        {
            TreeNode delete = (parent.left != null && parent.left.val == key) ? parent.left : parent.right;
        
            deleteNodeFromTree(delete, parent);
        }
        
        return dummy.left;
    }
    
	// time is O(logn)
    private TreeNode findParent(TreeNode node, TreeNode parent, int key)
    {
        while (node != null)
        {
            if (node.val > key)
            {
                parent = node;
                node = node.left;
            }
            else if (node.val < key)
            {
                parent = node;
                node = node.right;
            }
            else
            {
                return parent;
            }
        }
        
        return null;
    }
    
    // time is O(logn)
    private void deleteNodeFromTree(TreeNode delete, TreeNode parent)
    {
        if (delete.left == null)
        {
            if (parent.left == delete)
            {
                parent.left = delete.right;
            }
            else
            {
                parent.right = delete.right;
            }
        }
        else if (delete.right == null)
        {
            if (parent.left == delete)
            {
                parent.left = delete.left;
            }
            else
            {
                parent.right = delete.left;
            }
        }
        else
        {
            TreeNode father = delete;
            TreeNode node = delete.right;
            
            while (node.left != null)
            {
                father = node;
                node = node.left;
            }
            
            if (father.left == node)
            {
                father.left = node.right;
            }
            else
            {
                father.right = node.right;
            }
            
            if (parent.left == delete)
            {
                parent.left = node;
            }
            else
            {
                parent.right = node;
            }
            
            node.left = delete.left;
            node.right = delete.right;
        }
    }
}
