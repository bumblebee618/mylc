import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 

Example 1:


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
Example 2:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
 */

public class Q1302_Deepest_Leaves_Sum 
{
	public int deepestLeavesSum(TreeNode root) 
    {
        if (root == null)
        {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            sum = 0;
            
            for (int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                sum += node.val;
                
                if (node.left != null)
                {
                    queue.offer(node.left);
                }
                
                if (node.right != null)
                {
                    queue.offer(node.right);
                }
            }
        }
        
        return sum;
    }
}
