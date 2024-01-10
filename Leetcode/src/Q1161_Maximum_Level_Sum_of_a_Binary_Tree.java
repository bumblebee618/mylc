import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

 

Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 *
 */
public class Q1161_Maximum_Level_Sum_of_a_Binary_Tree {
	public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = -1, maxSum = Integer.MIN_VALUE, sum = 0, size = 0, level = 1;

        while (!queue.isEmpty()) {
            size = queue.size();
            sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (maxSum < sum) {
                maxSum = sum;
                result = level;
            }

            level++;
        }
        
        return result;
    }
}
