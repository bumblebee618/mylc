/**
 * 
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 *
 */
public class Q572_Subtree_of_Another_Tree {
	// solution 1: time complexity is O(m*n)
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null)
		{
			return false;
		}
		
		return isSameTree(s,t) || isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    
    private boolean isSameTree(TreeNode x,TreeNode y) {
        if(x == null && y == null) 
        {
            return true;
        } 
        else if(x == null || y == null) 
        {
            return false;
        }
            
        return x.val == y.val && isSameTree(x.left, y.left) && isSameTree(x.right, y.right);
    }
    
    
    // solution 2: time complexity is O(m+n)
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        String sResult = preorder(s, true);
        String tResult = preorder(t, true);
        return sResult.indexOf(tResult) >= 0;
    } 
    
    private String preorder(TreeNode node, boolean isLeft) {
        if (node == null) {
        	return isLeft ? "lnull" : "rnull";
        }
        
        return "#" + node.val + "#" + preorder(node.left, true) + "#" + preorder(node.right, false);
    }
    
}
