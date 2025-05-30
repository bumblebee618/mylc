/***
 * 
 * @author jackie
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
 

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 

Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class Q549_Binary_Tree_Longest_Consecutive_Sequence_II {
private int maxLen = 0;
    
    public int longestConsecutive(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        
        dfs(root);
        return maxLen;
    }
    
    private int[] dfs(TreeNode node)
    {
        if (node == null)
        {
            return new int[] {0, 0};
        }

        int inc = 1, dec = 1;
        
        if (node.left != null)
        {
            int[] left = dfs(node.left);
            
            if (node.val+1 == node.left.val)
            {
                inc = left[0]+1;
            }
            
            if (node.val-1 == node.left.val)
            {
                dec = left[1]+1;
            }
        }
        
        if (node.right != null)
        {
            int[] right = dfs(node.right);
            
            if (node.val+1 == node.right.val)
            {
                inc = Math.max(inc, right[0]+1);
            }
            
            if (node.val-1 == node.right.val)
            {
                dec = Math.max(dec, right[1]+1);
            }
        }
        
        maxLen = Math.max(maxLen, inc+dec-1);
        return new int[] {inc, dec};
    }
}
