import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 
 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

 *
 */
public class Le_653_Two_Sum_IV_Input_is_a_BST {
	// solution 1: use hashset, time is O(n), space is O(n)
	public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack();
        
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            
            if (set.contains(k - root.val)) {
                return true;
            } else {
                set.add(root.val);
            }
            
            root = root.right;
        }
        
        return false;
    }
}
