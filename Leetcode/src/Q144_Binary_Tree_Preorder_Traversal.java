import java.util.*;


public class Q144_Binary_Tree_Preorder_Traversal {
	//solution 1: use stack
	public List<Integer> preorderTraversal(TreeNode root) 
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
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            root = root.right;
        }
        
        return result;
    }
	
	
	//solution 2: recursive
	List<Integer> result = new LinkedList<>();
    
    public List<Integer> preorderTraversal2(TreeNode root) 
    {
        perorder(root);
        return result;
    }
    
    private void perorder(TreeNode root)
    {
        if (root == null)
        {
            return;
        }
        
        result.add(root.val);
        perorder(root.left);
        perorder(root.right);
    }
}


//class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//	TreeNode(int row) { val = row; }
//}
