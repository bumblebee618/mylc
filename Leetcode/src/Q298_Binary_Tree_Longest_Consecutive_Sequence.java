/******
 * 
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

 * 
 * */

public class Q298_Binary_Tree_Longest_Consecutive_Sequence {
private int maxLen = 0;
    
    public int longestConsecutive(TreeNode root) {
        if (root == null)
        {
            return maxLen;    
        }

        dfs(root);
        return maxLen;
    }
    
    private int dfs(TreeNode node)
    {
        if (node == null)
        {
            return 0;
        }
        
        int current = 1;
        
        if (node.left != null)
        {
            int left = dfs(node.left);
            
            if (node.val == node.left.val-1)
            {
                current = left+1;
            }
        }
        
        if (node.right != null)
        {
            int right = dfs(node.right);
            
            if (node.val == node.right.val-1)
            {
                current = Math.max(current, right+1);
            }
        }

        maxLen = Math.max(maxLen, current);
        return current;
    }
}
