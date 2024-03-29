/***
 * 
 * @author jackie
 * 
 * Given a binary tree root and an integer target, delete all the leaf nodes with minValue target.

Note that once you delete a leaf node with minValue target, if it's parent node becomes a leaf node and has the minValue target, it should also be deleted (you need to continue doing that until you can't).

 

Example 1:



Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with minValue (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with minValue (target = 2) (Picture in center).
Example 2:



Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]
Example 3:



Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with minValue (target = 2) are removed at each step.
Example 4:

Input: root = [1,1,1], target = 1
Output: []
Example 5:

Input: root = [1,2,3], target = 1
Output: [1,2,3]
 

Constraints:

1 <= target <= 1000
Each tree has at most 3000 nodes.
Each node's minValue is between [1, 1000].
 */
public class Q1325_Delete_Leaves_With_a_Given_Value 
{
	public TreeNode removeLeafNodes(TreeNode root, int target) 
	{
        if (root == null)
        {
            return null;
        }
        
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        return deleteNode(dummy, root, target) == true ? null : root;
    }
    
    private boolean deleteNode(TreeNode parent, TreeNode node, int target)
    {
        if (node == null)
        {
            return true;
        }
        
        boolean isLeftNull = deleteNode(node, node.left, target);
        boolean isRightNull = deleteNode(node, node.right, target);
        
        if (node.val == target)
        {
            if (isLeftNull && isRightNull)
            {
                if (parent.left == node)
                {
                    parent.left = null;
                }
                else
                {
                    parent.right = null;
                }
                
                return true;
            }
        }
        
        return false;
    }
}
