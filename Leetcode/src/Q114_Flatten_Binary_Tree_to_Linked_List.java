import java.util.*;
/*****
 * 
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

 * 
 * */

public class Q114_Flatten_Binary_Tree_to_Linked_List 
{
	// solution 1: using recursive	
	private TreeNode prev = null;

	public void flatten(TreeNode root) 
	{
        if (root == null)
        {
            return ;
        }
        
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
	
	
	
	// solution 2: using iterator
	public void flatten2(TreeNode root) 
	{   
		if (root == null)
		{
        	return;
        }
		
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
    
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                queue.add(root);
                root = root.left;
            }
            
            root = stack.pop();
            root = root.right;
        }
        
        root = queue.poll();
        
        while (!queue.isEmpty())
        {
            root.left = null;
            root.right = queue.poll();
            root = root.right;
        }
    }
}
