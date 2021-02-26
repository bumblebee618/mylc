/******
 * 
 * @author jackie
 *  
 *  Given the root of a binary tree, find the maximum average minValue of any subtree of that tree.
(A subtree of a tree is any node of that tree plus all its descendants. The average minValue of a tree is the sum of its values, divided by the number of nodes.)
 
Example 1:

Input: [5,6,1]
Output: 6.00000
Explanation: 
For the node with minValue = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with minValue = 6 we have an average of 6 / 1 = 6.
For the node with minValue = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
 
Note:
	1. The number of nodes in the tree is between 1 and 5000.
	2. Each node will have a minValue between 0 and 100000.
	3. Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 */
public class Q1120_Maximum_Average_Subtree 
{
	private double maxAvg = Double.MIN_VALUE;
    
    public double maximumAverageSubtree(TreeNode root) 
    {
        if (root == null)
        {
            return 0;
        }
        
        dfs(root);
        return maxAvg;
    }
    
    private int[] dfs(TreeNode node)
    {
        if (node == null)
        {
            return new int[] {0, 0};
        }
        
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int[] current = new int[2];
        current[0] = left[0] + right[0] + node.val;
        current[1] = left[1] + right[1] + 1;
        maxAvg = Math.max(maxAvg, (double) current[0] / current[1]);
        return current;
    }
}
