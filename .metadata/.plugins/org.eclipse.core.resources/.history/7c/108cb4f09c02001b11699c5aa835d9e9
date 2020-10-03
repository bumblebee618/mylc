/***
 * 
 * @author jackie
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

 

Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 

Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
 */
public class Q1026_Maximum_Difference_Between_Node_and_Ancestor {
private int maxDiff = 0;
    
    public int maxAncestorDiff(TreeNode root) {
        if (root == null)
        {
            return maxDiff;
        }
        
        dfs(root);
        return maxDiff;
    }
    
    private Integer[] dfs(TreeNode root)
    {
        if (root == null)
        {
            return new Integer[] {null, null};
        }
        
        Integer[] left = dfs(root.left);
        Integer[] right = dfs(root.right);
        Integer[] result = new Integer[] {root.val, root.val};
        
        if (left[0] != null && right[0] != null)
        {
            result[0] = Math.max(result[0], Math.max(left[0], right[0]));    
        }
        else if (left[0] != null && right[0] == null)
        {
            result[0] = Math.max(result[0], left[0]);  
        }
        else if (left[0] == null && right[0] != null)
        {
            result[0] = Math.max(result[0], right[0]);  
        }
        
        if (left[1] != null && right[1] != null)
        {
            result[1] = Math.min(result[1], Math.min(left[1], right[1]));    
        }
        else if (left[1] != null && right[1] == null)
        {
            result[1] = Math.min(result[1], left[1]);  
        }
        else if (left[1] == null && right[1] != null)
        {
            result[1] = Math.min(result[1], right[1]);  
        }
        
        maxDiff = Math.max(maxDiff, Math.max(Math.abs(root.val-result[0]), Math.abs(root.val-result[1])));
        
        return result;
    }
}
