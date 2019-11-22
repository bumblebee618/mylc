/**
 * 
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
 *
 */
public class Le_687_Longest_Univalue_Path {
private int maxLen = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        dfs(root);
        return maxLen;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftLen = dfs(node.left);
        int rightLen = dfs(node.right);
        int arrowLeft = 0, arrowRight = 0;
        
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += leftLen + 1;
        }
        
        if (node.right != null && node.right.val == node.val) {
            arrowRight += rightLen + 1;
        }
        
        maxLen = Math.max(maxLen, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
