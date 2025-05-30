import java.util.Stack;
/*********
 * 
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null.

 * 
 * */

public class Q285_Inorder_Successor_in_BST {
	// solution 1, use the feature of BST, time complexity is O(logn), space is O(1)
		public TreeNode inorderSuccessor(TreeNode root, TreeNode target) 
		{
	        if (root == null || target == null) 
	        {
	            return null;
	        }
	        
	        TreeNode nextNode = null;
	        
	        while (root != null) 
	        {
	            if (root.val < target.val) 
	            {
	                root = root.right;
	            } 
	            else if (root.val > target.val) 
	            {
	                nextNode = root;
	                root = root.left;
	            } 
	            else 
	            {
	                if (root.right == null) 
	                {
	                    return nextNode;
	                } 
	                else 
	                {
	                    TreeNode curNode = root.right;
	                    
	                    while (curNode.left != null) 
	                    {
	                        curNode = curNode.left;
	                    }
	                    
	                    return curNode;
	                }
	            }
	        }
	        
	        return null;
	    }
		
		
	
	// solution 2, use inorder traverse, time complexity is O(n), space is O(n)
	public TreeNode inorderSuccessor2(TreeNode root, TreeNode target) 
	{
        if (root == null || target == null) 
        {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        boolean foundNode = false; 
        
        while (root != null || !stack.isEmpty()) 
        {
            while (root != null) 
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            
            if (foundNode) 
            {
                return root;
            } 
            else if (root == target) 
            {
                foundNode = true;
            }
            
            root = root.right;
        }
        
        return null;
    }
	
	
	
	
	
	
//	
//	
//	// solution 1: using recursive
//	public TreeNode successor(TreeNode root, TreeNode p) {
//		if (root == null) {
//			return null;
//		}
//
//		if (root.val <= p.val) {
//			return successor(root.right, p);
//		} else {
//			TreeNode left = successor(root.left, p);
//			return (left != null) ? left : root;
//		}
//	}
//
//	// Predecessor, using recursive
//	public TreeNode predecessor(TreeNode root, TreeNode p) {
//		if (root == null) {
//			return null;
//		}
//
//		if (root.val >= p.val) {
//			return predecessor(root.left, p);
//		} else {
//			TreeNode right = predecessor(root.right, p);
//			return (right != null) ? right : root;
//		}
//	}
//
//	
//	
//	// solution 2: using iterator
//	public TreeNode successor2(TreeNode root, TreeNode p) {
//		if (root == null || p == null) {
//			return p;
//		}
//
//		boolean isFound = false;
//		Stack<TreeNode> stack = new Stack<TreeNode>();
//
//		while (!stack.isEmpty() || root != null) {
//			while (root != null) {
//				stack.push(root);
//				root = root.left;
//			}
//			root = stack.pop();
//
//			if (isFound == true) {
//				return root;
//			}
//			if (p == root) {
//				isFound = true;
//			}
//
//			root = root.right;
//		}
//
//		return null;
//	}
}
