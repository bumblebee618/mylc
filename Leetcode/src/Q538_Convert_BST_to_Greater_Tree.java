import java.util.Stack;

/**
 * 
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 *
 */
public class Q538_Convert_BST_to_Greater_Tree 
{
	public TreeNode convertBST(TreeNode root) 
	{
        if (root == null) 
        {
            return root;
        }    
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int sum = 0;
        
        while (node != null || !stack.isEmpty()) 
        {
            while (node != null) 
            {
                stack.push(node);
                node = node.right;
            }
            
            node = stack.pop();
            node.val += sum;
            sum = node.val;
            node = node.left;
        }
        
        return root;
    }
}
