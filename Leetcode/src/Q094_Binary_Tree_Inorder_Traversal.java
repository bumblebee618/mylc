import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class Q094_Binary_Tree_Inorder_Traversal 
{
	//solution 1: stack
	public List<Integer> inorderTraversal(TreeNode root) 
	{
		List<Integer> result = new LinkedList<>();
		
		if (root == null)
		{
			return result;
		}
		
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
            	stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        
        return result;
    }
	
	
	
	
	
	//solution 2: recursive
	private List<Integer> result = new LinkedList<>();
    
    public List<Integer> inorderTraversal2(TreeNode root) 
    {
        inorderTraverse(root);
        return result;
    }
    
    private void inorderTraverse(TreeNode node)
    {
        if (node == null)
        {
            return;
        }
        
        inorderTraverse(node.left);
        result.add(node.val);
        inorderTraverse(node.right);
    }
}
