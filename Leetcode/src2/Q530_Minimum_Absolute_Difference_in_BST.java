import java.util.Stack;

/***
 * 
 * @author jackie
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

 

Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 104].
0 <= Node.val <= 105
 *
 */
public class Q530_Minimum_Absolute_Difference_in_BST {
	public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        int result = Integer.MAX_VALUE;
        TreeNode prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();

            if (prev != null) {
                result = Math.min(result, Math.abs(prev.val - root.val));
            }

            prev = root;
            root = root.right;
        }

        return result;
    }
}
