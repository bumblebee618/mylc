/**
 * 
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new minValue of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
 *
 */
public class Q617_Merge_Two_Binary_Trees 
{
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) 
	{
        if (t1 == null || t2 == null)
        {
            if (t1 == null && t2 == null)
            {
                return null;
            }
            else
            {
                return t1 == null ? t2 : t1;
            }
        }
        
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
	
	
	

	
	public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) 
	{
        if (t1 == null) 
        {
            return t2;
        } 
        else if (t2 == null) 
        {
            return t1;
        }
        
        return helper(t1, t2);
    }
    
    private TreeNode helper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        } 
        
        TreeNode curNode = new TreeNode(0);
        
        if (node1 == null) {
            curNode.val = node2.val;
            curNode.left = helper(null, node2.left);
            curNode.right = helper(null, node2.right);
        } else if (node2 == null) {
            curNode.val = node1.val;
            curNode.left = helper(node1.left, null);
            curNode.right = helper(node1.right, null);
        } else {
            curNode.val = node1.val + node2.val;
            curNode.left = helper(node1.left, node2.left);
            curNode.right = helper(node1.right, node2.right);
        }
        
        return curNode;
    }
}
