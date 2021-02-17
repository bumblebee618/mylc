/***
 * 
 * @author jackie
 * Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.
 */
public class Q1008_Construct_Binary_Search_Tree_from_Preorder_Traversal 
{
	public TreeNode bstFromPreorder(int[] preorder) 
    {
        if (preorder == null || preorder.length == 0)
        {
            return null;
        }
        
        return builder(preorder, 0, preorder.length-1);
    }
    
    private TreeNode builder(int[] preorder, int start, int end)
    {
        if (start > end)
        {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[start]);
        int rightStart = start+1;
        
        while (rightStart <= end && preorder[start] > preorder[rightStart])
        {
            rightStart++;
        }
        
        root.left = builder(preorder, start+1, rightStart-1);
        root.right = builder(preorder, rightStart, end);
        return root;
    }
}
