/***
 * 
 * @author jackie
 * Given the root of a binary tree, find the maximum minValue V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

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
Among all possible differences, the maximum minValue of 7 is obtained by |8 - 1| = 7.
 

Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have minValue between 0 and 100000.
 */
public class Q1026_Maximum_Difference_Between_Node_and_Ancestor 
{
	private int maxDiff = Integer.MIN_VALUE;
    
    public int maxAncestorDiff(TreeNode root) 
    {
        if (root == null)
        {
            return 0;
        }
        
        dfs(root);
        return maxDiff;
    }
    
    private Integer[] dfs(TreeNode node)
    {
    	// array[0]: maxValue, array[1]: minValue
        Integer[] current = new Integer[] {null, null}; 
        
        if (node == null)
        {
            return current; 
        }
        
        Integer[] left = dfs(node.left);
        Integer[] right = dfs(node.right);
        
        if (left[0] != null && right[0] != null)
        {
            current[0] = Math.max(left[0], right[0]);
            current[1] = Math.min(left[1], right[1]);
        }
        else if (left[0] != null)
        {
            current[0] = left[0];
            current[1] = left[1];
        }
        else if (right[0] != null)
        {
            current[0] = right[0];
            current[1] = right[1];
        }
        else
        {
            current[0] = current[1] = node.val;
        }
        
        int localMaxDiff = Math.max(Math.abs(node.val-current[0]), Math.abs(node.val-current[1]));
        maxDiff = Math.max(maxDiff, localMaxDiff);
        current[0] = Math.max(current[0], node.val);
        current[1] = Math.min(current[1], node.val);
        return current;
    }
}
