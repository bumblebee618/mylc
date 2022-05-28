/***
 * 
 * @author jackie
 * 
 * Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

 

Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.
 */
public class Q968_Binary_Tree_Cameras 
{
	public int minCameraCover(TreeNode root) 
    {
        if (root == null)
        {
            return 0;   
        }
        
        int[] result = dfs(root);  
        return Math.min(result[1], result[2]);
    }
    
    // 0: Strict ST; All nodes below this are covered, but not this one
    // 1: Normal ST; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    private int[] dfs(TreeNode node)
    {
        if (node == null)
        {
            return new int[] {0, 0, 99999};
        }
        
        int[] result = new int[3];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        int mL12 = Math.min(left[1], left[2]);
        int mR12 = Math.min(right[1], right[2]);

        int d0 = left[1] + right[1];
        int d1 = Math.min(left[2] + mR12, right[2] + mL12);
        int d2 = Math.min(left[0], mL12) + Math.min(right[0], mR12) + 1;
        return new int[] {d0, d1, d2};
    }
}
