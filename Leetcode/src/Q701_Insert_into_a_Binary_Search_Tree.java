/***
 * 
 * @author jackie
 * 
 * Given the root node of a binary search tree (BST) and a minValue to be inserted into the tree, insert the minValue into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new minValue does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the minValue to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
 */
public class Q701_Insert_into_a_Binary_Search_Tree {
	public TreeNode insertIntoBST(TreeNode root, int val) 
	{
        if (root == null)
        {
            return new TreeNode(val);
        }
        
        TreeNode prevNode = root;
        TreeNode currentNode = root;
        
        while (currentNode != null)
        {
            if (currentNode.val > val)
            {
                prevNode = currentNode;
                currentNode = currentNode.left;
            }
            else if (currentNode.val < val)
            {
                prevNode = currentNode;
                currentNode = currentNode.right;
            }
            else
            {
                break;
            }
        }
        
        if (currentNode == null)
        {
            if (prevNode.val > val)
            {
                prevNode.left = new TreeNode(val);
            }
            else
            {
                prevNode.right = new TreeNode(val);
            }
        }
        
        return root;
    }
}
